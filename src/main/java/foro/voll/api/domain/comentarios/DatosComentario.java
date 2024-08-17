package foro.voll.api.domain.comentarios;

import jakarta.validation.constraints.NotBlank;

public record DatosComentario(
        @NotBlank
        String texto,
        @NotBlank
        String fechaCreacion
) {
}