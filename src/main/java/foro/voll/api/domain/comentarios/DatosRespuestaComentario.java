package foro.voll.api.domain.comentarios;

public record DatosRespuestaComentario(Long id,String texto,String fecha_creacion,Long id_usuario,Long id_publicacion) {
}
