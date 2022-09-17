package dev.mariocares.montesanto2.service.implementation;

import dev.mariocares.montesanto2.entity.Articulo;
import dev.mariocares.montesanto2.entity.ArticuloComentario;
import dev.mariocares.montesanto2.repository.ArticuloComentarioRepository;
import dev.mariocares.montesanto2.service.ArticuloComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticuloComentarioServiceImpl implements ArticuloComentarioService {

    @Autowired
    ArticuloComentarioRepository articuloComentarioRepository;

    @Override
    public ArticuloComentario guardar(ArticuloComentario articuloComentario) throws Exception {
        return articuloComentarioRepository.save(articuloComentario);
    }

    @Override
    public List<ArticuloComentario> obtener(Articulo articulo, Boolean estaAprobado) throws Exception {
        return articuloComentarioRepository.findAllByArticuloAndEstaAprobadoOrderByComentarioAtDesc(articulo, estaAprobado);
    }
}
