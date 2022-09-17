package dev.mariocares.montesanto2.service.implementation;

import dev.mariocares.montesanto2.entity.Rol;
import dev.mariocares.montesanto2.entity.Usuario;
import dev.mariocares.montesanto2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNombreUsuario(username);
        if(usuario == null){
            throw new UsernameNotFoundException("Usuario o contraseña incorrectas");
        }
        return new User(usuario.getNombreUsuario(), usuario.getContrasegna(), mapearRol(usuario.getRol()));
    }

    private Collection<? extends GrantedAuthority> mapearRol(Rol rol){
        return Collections.singleton(new SimpleGrantedAuthority(rol.getDescripcion()));
    }
}
