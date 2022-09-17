package dev.mariocares.montesanto2.entity;

import javax.persistence.*;

@Entity
@Table(name = "versiculo")
public class Versiculo {

    @Id
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    private Libro libro;

    @Column(nullable = false)
    private Integer capitulo;

    @Column(nullable = false)
    private Integer versiculo;

    @Column(nullable = false)
    private String texto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Integer getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(Integer capitulo) {
        this.capitulo = capitulo;
    }

    public Integer getVersiculo() {
        return versiculo;
    }

    public void setVersiculo(Integer versiculo) {
        this.versiculo = versiculo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}