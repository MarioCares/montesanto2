package dev.mariocares.montesanto2.repository;

import dev.mariocares.montesanto2.entity.Articulo;
import dev.mariocares.montesanto2.entity.ArticuloTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {

    List<Articulo> findByTextoContainingIgnoreCase(String termino);

    List<Articulo> findFirst30ByOrderByArticuloAtDesc();

    @Query(value = "SELECT descripcion FROM articulo_etiqueta WHERE articulo_id = :articulo_id", nativeQuery = true)
    List<String> findEtiquetasByArticulo(@Param("articulo_id") Long id);

    @Query(value = "SELECT * FROM articulo WHERE id IN (SELECT articulo_id FROM articulo_etiqueta WHERE descripcion = :etiqueta)", nativeQuery = true)
    List<Articulo> findArticulosByEtiqueta(@Param("etiqueta") String etiqueta);

    List<Articulo> findAllByAutorOriginal(String autor);
}
