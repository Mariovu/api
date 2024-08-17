package foro.voll.api.domain.publicacion;

import foro.voll.api.domain.etiquetas.DatosEtiqueta;

import java.util.List;

public record DatosRespuestaPublicacion(Long id, String titulo, String contenido, String fecha_creacion, Long id_usuario,List<DatosEtiqueta> etiquetas ) {
}
