package foro.voll.api.domain.etiquetas;

import foro.voll.api.domain.publicacion.Publicacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EtiquetaRepository extends JpaRepository<Etiqueta,Long> {
    Optional<Etiqueta> findByNombre(String nombre);

    Page<Etiqueta> findByEstadoTrue(Pageable paginacion);
}
