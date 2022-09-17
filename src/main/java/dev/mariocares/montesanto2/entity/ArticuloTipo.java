package dev.mariocares.montesanto2.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "articulo_tipo")
public class ArticuloTipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tipo_id")
    private Set<Articulo> articulos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTag() {
        String tipo = switch (this.id.intValue()) {
            case 1 -> "info";
            case 2 -> "success";
            case 3 -> "warning";
            case 4 -> "danger";
            default -> "light";
        };
        return "<span class='tag is-" + tipo + "'>" + this.descripcion + "</span>";
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(Set<Articulo> articulos) {
        this.articulos = articulos;
    }

    public ArticuloTipo() {
    }

    public ArticuloTipo(Long id) {
        this.id = id;
    }

    public ArticuloTipo(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "ArticuloTipo{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}