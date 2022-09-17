package dev.mariocares.montesanto2.formData;

import dev.mariocares.montesanto2.entity.Articulo;
import dev.mariocares.montesanto2.entity.ArticuloComentario;
import dev.mariocares.montesanto2.entity.Usuario;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ArticuloComentarioFormData {

    @NotNull(message = "El comentario debe tener contenido")
    @Size(min = 10, message = "El comentario debe tener al menos 10 caracteres")
    private String texto;

    private String comentarioAt;

    private Long comentador, articulo;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getComentarioAt() {
        return comentarioAt;
    }

    public void setComentarioAt(String comentarioAt) {
        this.comentarioAt = comentarioAt;
    }

    public Long getComentador() {
        return comentador;
    }

    public void setComentador(Long comentador) {
        this.comentador = comentador;
    }

    public Long getArticulo() {
        return articulo;
    }

    public void setArticulo(Long articulo) {
        this.articulo = articulo;
    }

    public ArticuloComentario toModel() throws ParseException {
        return new ArticuloComentario(
                this.texto,
                new Date(),
                false,
                new Usuario(this.comentador),
                new Articulo(this.articulo)
        );
    }

    @Override
    public String toString() {
        return "ArticuloComentarioFormData{" +
                "texto='" + texto + '\'' +
                ", comentarioAt='" + comentarioAt + '\'' +
                ", comentador=" + comentador +
                ", articulo=" + articulo +
                '}';
    }
}
