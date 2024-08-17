package foro.voll.api.domain.etiquetas;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record DatosEtiqueta(@NotBlank String nombre) {
    @JsonCreator
    public static DatosEtiqueta fromString(@JsonProperty("nombre") String nombre) {
        return new DatosEtiqueta(nombre);
    }
}
