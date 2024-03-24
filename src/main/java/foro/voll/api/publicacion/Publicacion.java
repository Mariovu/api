package foro.voll.api.publicacion;

import jakarta.persistence.*;

@Entity
@Table(name = "publicaciones")
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String contenido;

    private String etiqueta;

    private String fecha_creacion;

    private String estado;



    public Publicacion(DatosPublicacion datosPublicacion) {
        this.titulo = datosPublicacion.titulo();
        this.contenido = datosPublicacion.contenido();
        this.etiqueta = datosPublicacion.etiqueta();
        this.fecha_creacion = datosPublicacion.fecha_creacion();
        this.estado = datosPublicacion.estado();
    }

}