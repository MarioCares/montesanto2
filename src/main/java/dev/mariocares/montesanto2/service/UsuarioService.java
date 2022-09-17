package dev.mariocares.montesanto2.service;

import dev.mariocares.montesanto2.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario guardar(Usuario usuario) throws Exception;

    Usuario obtenerUsuarioPorEmail(String email) throws Exception;

    Usuario obtenerUsuarioPorNombreUsuario(String nombreUsuario) throws Exception;

    Usuario obtenerUsuarioPorId(Long id) throws Exception;

    List<Usuario> obtener() throws Exception;

}
