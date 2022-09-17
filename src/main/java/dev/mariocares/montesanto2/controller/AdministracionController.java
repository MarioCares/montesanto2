package dev.mariocares.montesanto2.controller;

import dev.mariocares.montesanto2.entity.Articulo;
import dev.mariocares.montesanto2.entity.ArticuloTipo;
import dev.mariocares.montesanto2.entity.Usuario;
import dev.mariocares.montesanto2.formData.ArticuloFormData;
import dev.mariocares.montesanto2.service.implementation.ArticuloServiceImpl;
import dev.mariocares.montesanto2.service.implementation.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
public class AdministracionController {

    @Autowired
    ArticuloServiceImpl articuloService;

    @Autowired
    UsuarioServiceImpl usuarioService;

    @GetMapping(value = "/Administracion/Articulo/Nuevo")
    public ModelAndView nuevoArticulo(){
        try {
            return new ModelAndView("articulo/nuevo")
                    .addObject("Tipos", articuloService.obtenerTipos())
                    .addObject("Publicadores", usuarioService.obtener())
                    .addObject("formData", new ArticuloFormData());
        } catch (Exception ex) {
            System.out.println("/Administracion/Articulo/Nuevo -> " + ex.getMessage());
        }
        return new ModelAndView("error");
    }

    @PostMapping(value = "/Administracion/Articulo/Nuevo")
    public String guardarArticulo(@Valid @ModelAttribute("formData")ArticuloFormData formData,
                                  BindingResult bindingResult,
                                  @RequestParam String etiquetas,
                                  Model model){
        try{
            if(bindingResult.hasErrors()){
                model.addAttribute("Tipos", articuloService.obtenerTipos())
                        .addAttribute("Publicadores", usuarioService.obtener());
                return "articulo/nuevo";
            } else {
                Articulo articulo = formData.toModel();
                Articulo finalArticulo = articuloService.guardar(articulo);
                Arrays.stream(etiquetas.split(",")).forEach(etiqueta -> {
                    try {
                        articuloService.asociarEtiqueta(etiqueta, finalArticulo.getId());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                });
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return "redirect:/Articulo/Listado";
    }
}
