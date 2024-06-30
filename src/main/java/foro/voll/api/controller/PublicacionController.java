package foro.voll.api.controller;

import foro.voll.api.domain.publicacion.*;
import foro.voll.api.domain.usuarios.Usuario;
import foro.voll.api.domain.usuarios.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.net.URI;


@RestController
@RequestMapping("/publicaciones")
@SecurityRequirement(name = "bearer-key")
public class PublicacionController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    private PublicacionRepository publicacionRepository;
    @Autowired
    public PublicacionController(PublicacionRepository publicacionRepository) {
        this.publicacionRepository = publicacionRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaPublicacion> crearPublicacion(@RequestBody @Valid DatosPublicacion datosPublicacion,
                                                                      UriComponentsBuilder uriComponentsBuilder){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) authentication.getPrincipal();

        Publicacion publicacion = new Publicacion(datosPublicacion, usuario);
        publicacion = publicacionRepository.save(publicacion);
        DatosRespuestaPublicacion datosRespuestaPublicacion = new DatosRespuestaPublicacion(
                publicacion.getId(),
                publicacion.getTitulo(),
                publicacion.getContenido(),
                publicacion.getEtiqueta(),
                publicacion.getFecha_creacion(),
                publicacion.getIdUsuario());
        URI url = uriComponentsBuilder.path("/publicaciones/{id}").buildAndExpand(publicacion.getId()).toUri();
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
                publicacion.getFecha_creacion(),
                publicacion.getIdUsuario()));
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
                publicacion.getFecha_creacion(),
                publicacion.getIdUsuario());
        return ResponseEntity.ok(datosPublicacion);
    }
}
