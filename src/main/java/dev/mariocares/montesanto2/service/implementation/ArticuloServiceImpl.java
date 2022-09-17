package dev.mariocares.montesanto2.service.implementation;

import dev.mariocares.montesanto2.entity.Articulo;
import dev.mariocares.montesanto2.entity.ArticuloTipo;
import dev.mariocares.montesanto2.entity.Usuario;
import dev.mariocares.montesanto2.repository.ArticuloRepository;
import dev.mariocares.montesanto2.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.*;

@Service
public class ArticuloServiceImpl implements ArticuloService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    ArticuloRepository articuloRepository;

    @Autowired
    UsuarioServiceImpl usuarioService;

    @Override
    public Articulo guardar(Articulo articulo) throws Exception {
        return articuloRepository.save(articulo);
    }

    @Override
    public List<Articulo> buscarPorTexto(String texto) throws Exception {
        return articuloRepository.findByTextoContainingIgnoreCase(texto);
    }

    @Override
    public List<Articulo> buscarPrimeros30Desc() throws Exception {
        List<Articulo> articulos = new ArrayList<>();
        for (Articulo articulo: articuloRepository.findFirst30ByOrderByArticuloAtDesc()) {
            articulo.setEtiquetas(articuloRepository.findEtiquetasByArticulo(articulo.getId()));
            articulos.add(articulo);
        }
        return articulos;
    }

    @Override
    public List<Articulo> buscarPorEtiqueta(String etiqueta) throws Exception {
        List<Articulo> articulos = new ArrayList<>();
        for (Articulo articulo: articuloRepository.findArticulosByEtiqueta(etiqueta)) {
            articulo.setEtiquetas(articuloRepository.findEtiquetasByArticulo(articulo.getId()));
            articulos.add(articulo);
        }
        return articulos;
    }

    @Override
    public List<Articulo> buscarPorAutor(String autor) throws Exception{
        return articuloRepository.findAllByAutorOriginal(autor);
    }

    @Override
    public Articulo buscarPorId(Long id) throws Exception {
        Optional<Articulo> tmp = articuloRepository.findById(id);
        if(tmp.isPresent()){
            tmp.get().setEtiquetas(articuloRepository.findEtiquetasByArticulo(tmp.get().getId()));
            return tmp.get();
        } else {
            return new Articulo();
        }
    }

    @Transactional
    @Override
    public void asociarEtiqueta(String etiqueta, Long articulo) throws Exception {
        String query = "INSERT INTO articulo_etiqueta (articulo_id, descripcion) VALUES (:articulo_id, :descripcion);";
        entityManager
                .createNativeQuery(query)
                .setParameter("articulo_id", articulo)
                .setParameter("descripcion", etiqueta)
                .executeUpdate();
    }

    @Transactional
    @Override
    public List<ArticuloTipo> obtenerTipos() throws Exception {
        List<ArticuloTipo> tipos = new ArrayList<>();
        String query = "SELECT DISTINCT at.id, at.descripcion FROM articulo_tipo AS at";
        List<Object[]> resultList = entityManager.createNativeQuery(query).getResultList();
        for(Object[] tmp : resultList){
            tipos.add(new ArticuloTipo(((BigInteger) tmp[0]).longValue(), (String) tmp[1]));
        }
        return tipos;
    }

    @Transactional
    public List<String> buscarAutores() throws Exception {
        List<String> autores = new ArrayList<>();
        String query = "SELECT DISTINCT autor_original FROM articulo GROUP BY autor_original";
        List resultList = entityManager.createNativeQuery(query).getResultList();
        resultList.forEach(autor -> autores.add(autor.toString()));
        return autores;
    }

    @Transactional
    public List<String> buscarEtiquetas() throws Exception {
        List<String> etiquetas = new ArrayList<>();
        String query = "SELECT descripcion FROM articulo_etiqueta GROUP BY descripcion";
        List resultList = entityManager.createNativeQuery(query).getResultList();
        resultList.forEach(etiqueta -> etiquetas.add(etiqueta.toString()));
        return etiquetas;
    }

    @Transactional
    public List<Articulo> buscar(String autor, String tipo, String etiqueta, String termino) throws Exception {
        String query = "SELECT a.id, a.publicador_id, a.titulo, a.articulo_at, a.introduccion, a.tipo_id" +
                " FROM articulo AS a WHERE 1=1";
        List<Articulo> articulos = new ArrayList<>();
        if(!Objects.equals(autor, "0")){
            query += String.format(" AND a.autor_original = '%s'", autor);
        }
        if(!Objects.equals(tipo, "0")){
            query += String.format(" AND a.tipo_id = %s", tipo);
        }
        if(!Objects.equals(etiqueta, "0")){
            query += String.format(" AND a.id in (SELECT articulo_id FROM articulo_etiqueta where descripcion = '%s')", etiqueta);
        }
        if(!termino.isEmpty()){
            termino = "%" + termino + "%";
            query += String.format(" AND (a.texto LIKE '%s' OR a.introduccion LIKE '%s')", termino, termino);
        }
        List<Object[]> resultList = entityManager.createNativeQuery(query).getResultList();
        for (Object[] tmp: resultList) {
            Usuario usuario = usuarioService.obtenerUsuarioPorId(((BigInteger) tmp[1]).longValue());
            Date articuloAt = (Date) tmp[3];
            ArticuloTipo articuloTipo = obtenerTipo(((BigInteger)tmp[5]).longValue());
            articulos.add(new Articulo(
                    ((BigInteger) tmp[0]).longValue(),
                    articuloAt,
                    (String) tmp[4],
                    (String) tmp[2],
                    usuario,
                    articuloTipo,
                    articuloRepository.findEtiquetasByArticulo(((BigInteger) tmp[0]).longValue())
            ));
        }
        return articulos;
    }

    @Override
    public ArticuloTipo obtenerTipo(Long id) throws Exception {
        String query = "SELECT id, descripcion FROM articulo_tipo WHERE id = :id";
        List<Object[]> resultList = entityManager
                .createNativeQuery(query)
                .setParameter("id", id)
                .getResultList();
        return new ArticuloTipo(((BigInteger) resultList.get(0)[0]).longValue(), (String) resultList.get(0)[1]);
    }
}
