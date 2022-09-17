package dev.mariocares.montesanto2.controller;

import dev.mariocares.montesanto2.entity.Usuario;
import dev.mariocares.montesanto2.formData.UsuarioFormData;
import dev.mariocares.montesanto2.service.implementation.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping("/")
    public String index(HttpSession session, Principal principal){
        try {
            if(principal != null){
                Usuario login = usuarioService.obtenerUsuarioPorNombreUsuario(principal.getName());
                session.setAttribute("loginUsuarioID", login.getId());
            }
        } catch (Exception ex) {
            System.out.println("/ -> " + ex.getMessage());
        }
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "usuario/login";
    }

    @GetMapping("/registro")
    public ModelAndView registro(){
        return new ModelAndView("usuario/registro")
                .addObject("formData", new UsuarioFormData());
    }

    @PostMapping("/registro")
    public String registro(@Valid @ModelAttribute("formData") UsuarioFormData formData, BindingResult bindingResult, Model model){
        if(!bindingResult.hasErrors()){
            try{
                usuarioService.guardar(formData.toModel());
                return "redirect:/login";
            } catch (Exception ex){
                System.out.println(ex.getMessage());
                bindingResult.rejectValue("nombreUsuario", "error.user", ex.getMessage());
            }
        }
        return "usuario/registro";
    }
}
