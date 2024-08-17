package foro.voll.api.domain.comentarios;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
        private Long id;

        private String texto;

        private String fechaCreacion;

        private Boolean estado;

        @ManyToOne
        @JoinColumn(name = "id_usuario")
        @JsonIgnore
        private Usuario usuario;

        @ManyToOne
        @JoinColumn(name = "id_publicacion")
        @JsonIgnore
        private Publicacion publicacion;

        @JsonProperty("id_usuario")
        public Long getIdUsuario() {
            return usuario!= null? usuario.getId() : null;
        }
        @JsonProperty("id_publicacion")
        public Long getIdPublicacion() {
            return publicacion!= null? publicacion.getId() : null;
        }
        public Comentario(){}
        public Comentario(DatosComentario datosComentario, Usuario usuario,Publicacion publicacion) {
            this.texto = datosComentario.texto();
            this.fechaCreacion = datosComentario .fechaCreacion();
            this.estado = true;
            this.usuario = usuario;
            this.publicacion=publicacion;
        }

        public void actualizarDatos(DatosActualizarComentario datosActualizarComentario) {
            if(datosActualizarComentario.texto()!=null) {
                this.texto= datosActualizarComentario.texto();
            }
        }

        public void desactivarComentario(Comentario comentario) {
            this.estado=false;
        }

    }
