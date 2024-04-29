package foro.voll.api.domain.publicacion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {

    Page<Publicacion> findByEstadoTrue(Pageable paginacion);
}
