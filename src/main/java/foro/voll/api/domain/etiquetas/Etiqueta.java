package foro.voll.api.domain.etiquetas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import foro.voll.api.domain.publicacion.DatosActualizarPublicacion;
import foro.voll.api.domain.publicacion.Publicacion;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "etiquetas")
@Getter
@Setter
public class Etiqueta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Boolean estado;

    @ManyToMany(mappedBy = "etiquetas")
    @JsonIgnore
    private List<Publicacion> publicaciones;

    public Etiqueta (){}
    public Etiqueta(DatosEtiqueta datosEtiqueta) {
        this.nombre= datosEtiqueta.nombre();
        this.estado=true;
    }
    public Etiqueta(String nombre){
        this.nombre=nombre;
        this.estado=true;
    }
    public void actualizarDatos(DatosActualizarEtiqueta datosActualizarEtiqueta) {
        if(datosActualizarEtiqueta.nombre()!=null) {
            this.nombre= datosActualizarEtiqueta.nombre();
        }
    }

    public void desactivarPublicacion(Etiqueta etiqueta) {this.estado=false;}
}