package foro.voll.api.domain.comentarios;

import foro.voll.api.domain.publicacion.Publicacion;
import foro.voll.api.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


    @Entity
    @Table(name = "comentarios")
    @Getter
    @Setter
    public class Comentario {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private String texto;

        private String fechaCreacion;

        private Boolean estado;

        @ManyToOne
        @JoinColumn(name = "id_usuario")
        private Usuario usuario;

        @ManyToOne
        @JoinColumn(name = "id_publicaciones")
        private Publicacion publicacion;

        public Comentario(){}
    }
