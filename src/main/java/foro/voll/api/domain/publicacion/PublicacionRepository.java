package foro.voll.api.domain.publicacion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {

    Page<Publicacion> findByEstadoTrue(Pageable paginacion);

    Optional<Publicacion> findById(Long id);
}
