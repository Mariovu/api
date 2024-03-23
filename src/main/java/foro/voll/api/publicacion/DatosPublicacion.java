package foro.voll.api.publicacion;


import foro.voll.api.autor.DatosAutor;

import java.util.List;

public record DatosPublicacion(String titulo, String contenido, String etiqueta, DatosAutor autor, String fecha_creacion, String estado) {
}
