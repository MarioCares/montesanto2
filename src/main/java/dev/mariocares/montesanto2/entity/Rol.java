package dev.mariocares.montesanto2.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @Column(nullable = false)
    private Long id;

    @Column
    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id")
    private Set<Usuario> usuarios;

    public Rol() {
    }

    public Rol(Long id) {
        this.id = id;
    }

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

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        System.out.println(usuarios);
        return "Rol{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", usuarios=" + ((usuarios == null) ? "" : usuarios) +
                '}';
    }
}