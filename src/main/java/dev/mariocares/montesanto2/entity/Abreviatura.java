package dev.mariocares.montesanto2.entity;

import javax.persistence.*;

@Entity
public class Abreviatura {

    @Id
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String descripcion;

    @ManyToOne
    Libro libro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
