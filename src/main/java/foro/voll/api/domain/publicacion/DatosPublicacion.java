        package foro.voll.api.domain.publicacion;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DatosPublicacion(
        @NotBlank
        String titulo,
        @NotBlank
        String contenido,
        @NotBlank
        String etiqueta,
        @NotBlank
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}", message = "El formato de fecha debe ser yyyy-MM-dd HH:mm:ss")
        String fecha_creacion,
        Long id_usuario
        ) {
}
