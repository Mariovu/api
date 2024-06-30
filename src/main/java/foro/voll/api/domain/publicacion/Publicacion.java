package foro.voll.api.domain.publicacion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import foro.voll.api.domain.usuarios.Usuario;
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

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonIgnore
    private Usuario usuario;

    @JsonProperty("id_usuario")
    public Long getIdUsuario() {
        return usuario!= null? usuario.getId() : null;
    }

    public Publicacion() {}

    public Publicacion(DatosPublicacion datosPublicacion, Usuario usuario) {
        this.titulo = datosPublicacion.titulo();
        this.contenido = datosPublicacion.contenido();
        this.etiqueta = datosPublicacion.etiqueta();
        this.fecha_creacion = datosPublicacion.fecha_creacion();
        this.estado = true;
        this.usuario=usuario;
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