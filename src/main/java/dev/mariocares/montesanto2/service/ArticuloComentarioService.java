package dev.mariocares.montesanto2.service;

import dev.mariocares.montesanto2.entity.Articulo;
import dev.mariocares.montesanto2.entity.ArticuloComentario;

import java.util.List;

public interface ArticuloComentarioService {

    ArticuloComentario guardar(ArticuloComentario articuloComentario) throws Exception;

    List<ArticuloComentario> obtener(Articulo articulo, Boolean estaAprobado) throws Exception;
}
