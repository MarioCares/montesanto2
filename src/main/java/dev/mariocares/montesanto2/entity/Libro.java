package dev.mariocares.montesanto2.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String testamento;

    @Column(nullable = false)
    private Integer capitulos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "libro_id")
    private Set<Abreviatura> abreviaturas;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "libro_id")
    private Set<Versiculo> versiculos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTestamento() {
        return testamento;
    }

    public void setTestamento(String testamento) {
        this.testamento = testamento;
    }

    public Integer getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(Integer capitulos) {
        this.capitulos = capitulos;
    }

    public Set<Abreviatura> getAbreviaturas() {
        return abreviaturas;
    }

    public void setAbreviaturas(Set<Abreviatura> abreviaturas) {
        this.abreviaturas = abreviaturas;
    }

    public Set<Versiculo> getVersiculos() {
        return versiculos;
    }

    public void setVersiculos(Set<Versiculo> versiculos) {
        this.versiculos = versiculos;
    }
}