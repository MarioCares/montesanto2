package dev.mariocares.montesanto2.service.implementation;

import dev.mariocares.montesanto2.entity.Rol;
import dev.mariocares.montesanto2.entity.Usuario;
import dev.mariocares.montesanto2.repository.UsuarioRepository;
import dev.mariocares.montesanto2.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario guardar(Usuario usuario) throws Exception {
        Usuario tmp = usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario());
        if(tmp != null){
            throw new Exception("El nombre de usuario ya Existe!");
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            usuario.setContrasegna(passwordEncoder.encode(usuario.getContrasegna()));
            usuario.setRol(new Rol(2L));
            tmp = usuarioRepository.save(usuario);
        }
        return tmp;
    }

    @Override
    public Usuario obtenerUsuarioPorEmail(String email) throws Exception {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Usuario obtenerUsuarioPorNombreUsuario(String nombreUsuario) throws Exception {
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) throws Exception {
        Optional<Usuario> tmp = usuarioRepository.findById(id);
        return tmp.orElseGet(Usuario::new);
    }

    @Override
    public List<Usuario> obtener() throws Exception {
        return usuarioRepository.findAll();
    }
}
