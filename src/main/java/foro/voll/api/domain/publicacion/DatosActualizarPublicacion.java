package foro.voll.api.domain.publicacion;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarPublicacion(@NotNull Long id , String titulo ,String contenido,String etiqueta) {
}
