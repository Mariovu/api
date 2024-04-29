package foro.voll.api.controller;

import foro.voll.api.domain.publicacion.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {

    private PublicacionRepository publicacionRepository;
    @Autowired
    public PublicacionController(PublicacionRepository publicacionRepository) {
        this.publicacionRepository = publicacionRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaPublicacion> crearPublicacion(@RequestBody @Valid DatosPublicacion datosPublicacion,
                                                                      UriComponentsBuilder uriComponentsBuilder){
       Publicacion publicacion = publicacionRepository.save(new Publicacion(datosPublicacion));
       DatosRespuestaPublicacion datosRespuestaPublicacion=new DatosRespuestaPublicacion(
               publicacion.getId(),
               publicacion.getTitulo(),
               publicacion.getContenido(),
               publicacion.getEtiqueta(),
               publicacion.getFecha_creacion());
       URI url=uriComponentsBuilder.path("/publicaciones/{id}").buildAndExpand(publicacion.getId()).toUri();
       return ResponseEntity.created(url).body(datosRespuestaPublicacion);

    }

    @GetMapping
    public ResponseEntity<Page<Publicacion>> listarPublicaciones(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(publicacionRepository.findByEstadoTrue(paginacion) );
    }

    @PutMapping
    @Transactional
    public ResponseEntity actulizarPublicacion(@RequestBody @Valid DatosActualizarPublicacion datosActualizarPublicacion){
        Publicacion publicacion=publicacionRepository.getReferenceById(datosActualizarPublicacion.id());
        publicacion.actualizarDatos(datosActualizarPublicacion);
        return ResponseEntity.ok(new DatosRespuestaPublicacion(
                publicacion.getId(),
                publicacion.getTitulo(),
                publicacion.getContenido(),
                publicacion.getEtiqueta(),
                publicacion.getFecha_creacion()));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarPublicacion(@PathVariable Long id ){
        Publicacion publicacion=publicacionRepository.getReferenceById(id);
        publicacion.desactivarPublicacion(publicacion);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaPublicacion> retornaDatosPublicacion(@PathVariable Long id ){
        Publicacion publicacion=publicacionRepository.getReferenceById(id);
        var datosPublicacion=new DatosRespuestaPublicacion(
                publicacion.getId(),
                publicacion.getTitulo(),
                publicacion.getContenido(),
                publicacion.getEtiqueta(),
                publicacion.getFecha_creacion());
        return ResponseEntity.ok(datosPublicacion);
    }
}
