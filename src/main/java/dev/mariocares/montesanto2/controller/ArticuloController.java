package dev.mariocares.montesanto2.controller;

import dev.mariocares.montesanto2.entity.Articulo;
import dev.mariocares.montesanto2.entity.ArticuloComentario;
import dev.mariocares.montesanto2.formData.ArticuloComentarioFormData;
import dev.mariocares.montesanto2.service.implementation.ArticuloComentarioServiceImpl;
import dev.mariocares.montesanto2.service.implementation.ArticuloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class ArticuloController {

    @Autowired
    ArticuloServiceImpl articuloService;

    @Autowired
    ArticuloComentarioServiceImpl comentarioService;

    @GetMapping(value = "/Articulo/Listado")
    public ModelAndView articulos(){
        try {
            return new ModelAndView("articulo/index")
                    .addObject("Articulos", articuloService.buscarPrimeros30Desc());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return new ModelAndView("error");
    }

    @GetMapping(value = "/Articulo/Etiqueta/{etiqueta}")
    public ModelAndView articulosPorEtiqueta(@PathVariable String etiqueta){
        try {
            return new ModelAndView("articulo/index")
                    .addObject("Articulos", articuloService.buscarPorEtiqueta(etiqueta));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return new ModelAndView("error");
    }

    @GetMapping(value = "/Articulo/Autor/{autor}")
    public ModelAndView articulosPorAutor(@PathVariable String autor){
        try {
            return new ModelAndView("articulo/index")
                    .addObject("Articulos", articuloService.buscarPorAutor(autor));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return new ModelAndView("error");
    }

    @GetMapping(value = "/Articulo/Busqueda")
    public ModelAndView busqueda(){
        try{
            return new ModelAndView("articulo/busqueda")
                    .addObject("Autores", articuloService.buscarAutores())
                    .addObject("Tipos", articuloService.obtenerTipos())
                    .addObject("Etiquetas", articuloService.buscarEtiquetas())
                    .addObject("Resultado", new ArrayList<Articulo>());
        } catch (Exception ex){
            System.out.println("GET /Articulo/Busqueda -> " + ex.getMessage());
            return new ModelAndView("error");
        }
    }

    @PostMapping(value = "/Articulo/Busqueda")
    public ModelAndView buscarArticulo(@RequestParam String autor, @RequestParam String tipo,
                                       @RequestParam String etiqueta, @RequestParam String termino){
        try{
            return new ModelAndView("articulo/busqueda")
                    .addObject("Autores", articuloService.buscarAutores())
                    .addObject("Tipos", articuloService.obtenerTipos())
                    .addObject("Etiquetas", articuloService.buscarEtiquetas())
                    .addObject("Resultado", articuloService.buscar(autor, tipo, etiqueta, termino));
        } catch (Exception ex){
            System.out.println("POST /Articulo/Busqueda -> " + ex.getMessage());
            return new ModelAndView("error");
        }
    }

    @PostMapping(value = "/Articulo/Comentario")
    public String guardarComentario(@Valid @ModelAttribute("formData")ArticuloComentarioFormData formData,
                                    BindingResult bindingResult, Model model){
        try{
            if(bindingResult.hasErrors()){
                Articulo articulo = articuloService.buscarPorId(formData.getArticulo());
                try {
                    model.addAttribute("Articulo", articulo)
                        .addAttribute("Comentador", formData.getComentador())
                        .addAttribute("Comentarios", comentarioService.obtener(
                                new Articulo(articulo.getId()),
                                true)
                        );
                } catch (Exception ex) {
                    System.out.println("ArticuloController.guardarComentario:57 -> " + ex.getMessage());
                }
                return "articulo/articulo";
            } else {
                ArticuloComentario comentario = formData.toModel();
                comentarioService.guardar(comentario);
            }
        } catch (Exception ex){
            System.out.println("/Articulo/Comentario -> " + ex.getMessage());
        }
        return "redirect:/Articulo/" + formData.getArticulo();
    }

    @GetMapping(value = "/Articulo/{id}")
    public ModelAndView articulo(@PathVariable Long id, HttpSession httpSession){
        try {
            Articulo articulo = articuloService.buscarPorId(id);
            try {
                return new ModelAndView("articulo/articulo")
                        .addObject("Articulo", articulo)
                        .addObject("formData", new ArticuloComentarioFormData())
                        .addObject("Comentador", httpSession.getAttribute("loginUsuarioID"))
                        .addObject("Comentarios", comentarioService.obtener(
                                new Articulo(articulo.getId()),
                                true)
                        );
            } catch (Exception ex) {
                System.out.println("/Articulo/{id}/map -> " + ex.getMessage());
                return new ModelAndView("articulo/error");
            }
        } catch (Exception ex) {
            System.out.println("/Articulo/{id}/" + ex.getMessage());
        }
        return new ModelAndView("error");
    }


}
