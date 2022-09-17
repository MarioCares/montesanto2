package dev.mariocares.montesanto2.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "articulo")
public class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String introduccion;

    @Column(nullable = false)
    private String texto;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date articuloAt;

    @Column(nullable = false)
    private String publicadoOriginalmente;

    @Column(nullable = false)
    private String autorOriginal;

    @ManyToOne
    private ArticuloTipo tipo;

    @ManyToOne
    private Usuario publicador;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "articulo_id")
    private Set<ArticuloComentario> comentarios;

    @Transient
    private List<String> etiquetas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIntroduccion() {
        return introduccion;
    }

    public void setIntroduccion(String introduccion) {
        this.introduccion = introduccion;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getArticuloAt() {
        return articuloAt;
    }

    public void setArticuloAt(Date articuloAt) {
        this.articuloAt = articuloAt;
    }

    public String getPublicadoOriginalmente() {
        return publicadoOriginalmente;
    }

    public void setPublicadoOriginalmente(String publicadoOriginalmente) {
        this.publicadoOriginalmente = publicadoOriginalmente;
    }

    public String getAutorOriginal() {
        return autorOriginal;
    }

    public void setAutorOriginal(String autorOriginal) {
        this.autorOriginal = autorOriginal;
    }

    public ArticuloTipo getTipo() {
        return tipo;
    }

    public void setTipo(ArticuloTipo tipo) {
        this.tipo = tipo;
    }

    public Usuario getPublicador() {
        return publicador;
    }

    public void setPublicador(Usuario publicador) {
        this.publicador = publicador;
    }

    public List<String> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<String> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public Articulo() {
    }

    public Articulo(Long id) {
        this.id = id;
    }

    public Articulo(String titulo, String introduccion, String texto, Date articuloAt, String publicadoOriginalmente,
                    String autorOriginal, Long tipo, Long publicador) {
        this.titulo = titulo;
        this.introduccion = introduccion;
        this.texto = texto;
        this.articuloAt = articuloAt;
        this.publicadoOriginalmente = publicadoOriginalmente;
        this.autorOriginal = autorOriginal;
        this.tipo = new ArticuloTipo(tipo);
        this.publicador = new Usuario(publicador);
    }

    public Articulo(Long id, Date articuloAt, String introduccion, String titulo, Usuario publicador, ArticuloTipo tipo,
                    List<String> etiquetas){
        this.id = id;
        this.articuloAt = articuloAt;
        this.introduccion = introduccion;
        this.titulo = titulo;
        this.publicador = publicador;
        this.tipo = tipo;
        this.etiquetas = etiquetas;
    }

    public String getIntroduccionSaltos(){
        return this.introduccion.replace("\n", "<br />");
    }

    public String getTextoSaltos(){
        return texto.replace("\n", "<br />");
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", introduccion='" + introduccion + '\'' +
                ", texto='" + texto + '\'' +
                ", articuloAt=" + articuloAt +
                ", publicadoOriginalmente='" + publicadoOriginalmente + '\'' +
                ", autorOriginal='" + autorOriginal + '\'' +
                ", tipo=" + tipo +
                ", publicador=" + publicador.getNombreUsuario() +
                ", comentarios=" + comentarios +
                ", etiquetas=" + etiquetas +
                '}';
    }
}