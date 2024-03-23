package foro.voll.api.publicacion;

import foro.voll.api.autor.Autor;

import jakarta.persistence.*;

@Entity
@Table(name = "publicaciones")
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String contenido;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @Column(name = "fecha_creacion")
    private String fechaCreacion;

    private String estado;

    private String etiquetas;

    public Publicacion(DatosPublicacion datosPublicacion) {
        this.titulo = datosPublicacion.titulo();
        this.contenido = datosPublicacion.contenido();
        this.etiquetas = datosPublicacion.etiqueta();
        this.autor = new Autor(datosPublicacion.autor());
        this.fechaCreacion = datosPublicacion.fecha_creacion();
        this.estado = datosPublicacion.estado();
    }

}