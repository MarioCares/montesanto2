package dev.mariocares.montesanto2.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "articulo_comentario")
public class ArticuloComentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String texto;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date comentarioAt;

    @Column(nullable = false)
    private Boolean estaAprobado;

    @ManyToOne
    private Usuario comentador;

    @ManyToOne
    private Articulo articulo;

    public ArticuloComentario() {
    }

    public ArticuloComentario(String texto, Date comentarioAt, Boolean estaAprobado, Usuario comentador, Articulo articulo) {
        this.texto = texto;
        this.comentarioAt = comentarioAt;
        this.estaAprobado = estaAprobado;
        this.comentador = comentador;
        this.articulo = articulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getArticuloAt() {
        return comentarioAt;
    }

    public void setArticuloAt(Date comentarioAt) {
        this.comentarioAt = comentarioAt;
    }

    public Usuario getComentador() {
        return comentador;
    }

    public void setComentador(Usuario comentador) {
        this.comentador = comentador;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Boolean getEstaAprobado() {
        return estaAprobado;
    }

    public void setEstaAprobado(Boolean estaAprobado) {
        this.estaAprobado = estaAprobado;
    }

    @Override
    public String toString() {
        return "ArticuloComentario{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", comentarioAt=" + comentarioAt +
                ", estaAprobado=" + estaAprobado +
                ", comentador=" + comentador +
                ", articulo=" + articulo +
                '}';
    }

    public String getTextoSaltos(){
        return texto.replace("\n", "<br />");
    }
}