package foro.voll.api.domain.comentarios;

import foro.voll.api.domain.publicacion.Publicacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario,Long> {
    Page<Comentario> findByEstadoTrueAndPublicacionId(Long publicacionId, Pageable paginacion);
}
