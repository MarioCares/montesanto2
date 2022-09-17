package dev.mariocares.montesanto2.service;

import dev.mariocares.montesanto2.entity.Articulo;
import dev.mariocares.montesanto2.entity.ArticuloTipo;

import java.util.List;
import java.util.Optional;

public interface ArticuloService {

    Articulo guardar(Articulo articulo) throws Exception;

    List<Articulo> buscarPorTexto(String texto) throws Exception;

    List<Articulo> buscarPrimeros30Desc() throws Exception;

    List<Articulo> buscarPorEtiqueta(String etiqueta) throws Exception;

    List<Articulo> buscarPorAutor(String autor) throws Exception;

    Articulo buscarPorId(Long id) throws Exception;

    void asociarEtiqueta(String etiqueta, Long articulo) throws Exception;

    List<ArticuloTipo> obtenerTipos() throws Exception;

    ArticuloTipo obtenerTipo(Long id) throws Exception;
}
