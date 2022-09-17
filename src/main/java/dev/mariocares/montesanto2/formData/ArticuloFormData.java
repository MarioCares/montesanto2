package dev.mariocares.montesanto2.formData;

import dev.mariocares.montesanto2.entity.Articulo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ArticuloFormData {

    @NotNull(message = "El artículo debe tener un título")
    @Size(min = 3, max = 255, message = "El título debe tener entre 3 y 255 caracteres")
    private String titulo;

    @NotNull(message = "El artículo debe tener contenido")
    @Size(min = 50, message = "El artículo debe tener al menos 50 caracteres")
    private String texto;

    private Long publicador, tipo;
    private String articuloAt, autorOriginal, introduccion, publicadoOriginalmente;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Long getPublicador() {
        return publicador;
    }

    public void setPublicador(Long publicador) {
        this.publicador = publicador;
    }

    public Long getTipo() {
        return tipo;
    }

    public void setTipo(Long tipo) {
        this.tipo = tipo;
    }

    public String getArticuloAt() {
        return articuloAt;
    }

    public void setArticuloAt(String articuloAt) {
        this.articuloAt = articuloAt;
    }

    public String getAutorOriginal() {
        return autorOriginal;
    }

    public void setAutorOriginal(String autorOriginal) {
        this.autorOriginal = autorOriginal;
    }

    public String getIntroduccion() {
        return introduccion;
    }

    public void setIntroduccion(String introduccion) {
        this.introduccion = introduccion;
    }

    public String getPublicadoOriginalmente() {
        return publicadoOriginalmente;
    }

    public void setPublicadoOriginalmente(String publicadoOriginalmente) {
        this.publicadoOriginalmente = publicadoOriginalmente;
    }

    public Articulo toModel() throws ParseException {
        return new Articulo(
                this.titulo,
                this.introduccion,
                this.texto,
                new SimpleDateFormat("yyyy-MM-dd").parse(this.articuloAt),
                this.publicadoOriginalmente,
                this.autorOriginal,
                this.tipo,
                this.publicador
        );
    }
}
