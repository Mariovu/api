package foro.voll.api.domain.etiquetas;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarEtiqueta(@NotNull Long id , String nombre) {
}
