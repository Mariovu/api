package foro.voll.api.domain.comentarios;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarComentario(@NotNull Long id , String texto) {
}
