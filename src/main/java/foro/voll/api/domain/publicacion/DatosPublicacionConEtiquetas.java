package foro.voll.api.domain.publicacion;


import foro.voll.api.domain.etiquetas.DatosEtiqueta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record DatosPublicacionConEtiquetas(
        @NotBlank
        String titulo,
        @NotBlank
        String contenido,
        @NotBlank
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}", message = "El formato de fecha debe ser yyyy-MM-dd HH:mm:ss")
        String fecha_creacion,
        List<DatosEtiqueta> etiquetas
) {
}