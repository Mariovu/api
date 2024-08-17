package foro.voll.api.controller;

import foro.voll.api.domain.comentarios.*;
import foro.voll.api.domain.etiquetas.DatosEtiqueta;
import foro.voll.api.domain.publicacion.*;
import foro.voll.api.domain.usuarios.Usuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("publicaciones/{idPublicacion}/comentarios")
@SecurityRequirement(name = "bearer-key")
public class ComentarioController {
    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private PublicacionRepository publicacionRepository;
    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaComentario> crearPublicacion(@PathVariable Long idPublicacion,
                                                                      @RequestBody @Valid DatosComentario datosComentario,
                                                                      UriComponentsBuilder uriComponentsBuilder){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) authentication.getPrincipal();

        Optional<Publicacion> publicacionOptional = publicacionRepository.findById(idPublicacion);
        if (!publicacionOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Publicacion publicacion = publicacionOptional.get();

        Comentario comentario = new Comentario(datosComentario,usuario,publicacion);
        comentario = comentarioRepository.save(comentario);
        DatosRespuestaComentario datosRespuestaComentario = new DatosRespuestaComentario(
                comentario.getId(),
                comentario.getTexto(),
                comentario.getFechaCreacion(),
                comentario.getIdUsuario(),
                comentario.getIdPublicacion());
        URI url = uriComponentsBuilder.path("/publicaciones/{idPublicacion}/comentarios/{idComentario}")
                .buildAndExpand(idPublicacion, comentario.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaComentario);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarComentario(@RequestBody @Valid DatosActualizarComentario datosActualizarComentario){
        Comentario comentario=comentarioRepository.getReferenceById(datosActualizarComentario.id());
        comentario.actualizarDatos(datosActualizarComentario);
        return ResponseEntity.ok(new DatosRespuestaComentario(
                comentario.getId(),
                comentario.getTexto(),
                comentario.getFechaCreacion(),
                comentario.getIdUsuario(),
                comentario.getIdPublicacion()));
    }

    @GetMapping
    public ResponseEntity<Page<Comentario>> listarComentarios(@PathVariable Long idPublicacion, @PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(comentarioRepository.findByEstadoTrueAndPublicacionId(idPublicacion, paginacion));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaComentario> retornaDatosComentario(@PathVariable Long id ){

        Comentario comentario=comentarioRepository.getReferenceById(id);


        var datosComentario=new DatosRespuestaComentario(
                comentario.getId(),
                comentario.getTexto(),
                comentario.getFechaCreacion(),
                comentario.getIdUsuario(),
                comentario.getIdPublicacion());
        return ResponseEntity.ok(datosComentario);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarComentario(@PathVariable Long id ){
        Comentario comentario=comentarioRepository.getReferenceById(id);
        comentario.desactivarComentario(comentario);
        return ResponseEntity.noContent().build();
    }
}
