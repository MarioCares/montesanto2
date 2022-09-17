package dev.mariocares.montesanto2.repository;

import dev.mariocares.montesanto2.entity.Articulo;
import dev.mariocares.montesanto2.entity.ArticuloComentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloComentarioRepository extends JpaRepository<ArticuloComentario, Long> {

    List<ArticuloComentario> findAllByArticuloAndEstaAprobadoOrderByComentarioAtDesc(Articulo articulo, Boolean estaAprobado);
}
