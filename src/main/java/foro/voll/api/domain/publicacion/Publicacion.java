package foro.voll.api.domain.publicacion;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "publicaciones")
@Getter
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String contenido;

    private String etiqueta;

    private String fecha_creacion;

    private Boolean estado;

    public Publicacion(){
    }
    public Publicacion(DatosPublicacion datosPublicacion) {
        this.titulo = datosPublicacion.titulo();
        this.contenido = datosPublicacion.contenido();
        this.etiqueta = datosPublicacion.etiqueta();
        this.fecha_creacion = datosPublicacion.fecha_creacion();
        this.estado = true;
    }

    public void actualizarDatos(DatosActualizarPublicacion datosActualizarPublicacion) {
        if(datosActualizarPublicacion.titulo()!=null) {
            this.titulo= datosActualizarPublicacion.titulo();
        }
        if(datosActualizarPublicacion.contenido()!=null){
            this.contenido= datosActualizarPublicacion.contenido();
        }
        if(datosActualizarPublicacion.etiqueta()!=null){
            this.etiqueta= datosActualizarPublicacion.etiqueta();
        }
    }

    public void desactivarPublicacion(Publicacion publicacion) {
        this.estado=false;
    }
}