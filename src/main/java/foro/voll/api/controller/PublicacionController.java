package foro.voll.api.controller;

import foro.voll.api.domain.etiquetas.DatosEtiqueta;
import foro.voll.api.domain.etiquetas.Etiqueta;
import foro.voll.api.domain.etiquetas.EtiquetaRepository;
import foro.voll.api.domain.publicacion.*;
import foro.voll.api.domain.usuarios.Usuario;
import foro.voll.api.domain.usuarios.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.net.URI;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/publicaciones")
@SecurityRequirement(name = "bearer-key")
public class PublicacionController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PublicacionRepository publicacionRepository;
    @Autowired
    private EtiquetaRepository etiquetaRepository;
    @Autowired
    public PublicacionController(PublicacionRepository publicacionRepository) {
        this.publicacionRepository = publicacionRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaPublicacion> crearPublicacion(@RequestBody @Valid DatosPublicacionConEtiquetas datosPublicacionConEtiquetas,
                                                                      UriComponentsBuilder uriComponentsBuilder){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) authentication.getPrincipal();

        Publicacion publicacion = new Publicacion(datosPublicacionConEtiquetas, usuario);

        List<Etiqueta> etiquetas = datosPublicacionConEtiquetas.etiquetas().stream()
                .map(e -> etiquetaRepository.findByNombre(e.nombre())
                        .orElseThrow(() -> new IllegalArgumentException("Etiqueta no encontrada: " + e.nombre())))
                .collect(Collectors.toList());


        publicacion.getEtiquetas().addAll(etiquetas);

        publicacion = publicacionRepository.save(publicacion);

        List<DatosEtiqueta> datosRespuestaEtiquetas = etiquetas.stream()
                .map(e -> new DatosEtiqueta(e.getNombre()))
                .collect(Collectors.toList());

        DatosRespuestaPublicacion datosRespuestaPublicacion = new DatosRespuestaPublicacion(
                publicacion.getId(),
                publicacion.getTitulo(),
                publicacion.getContenido(),
                publicacion.getFecha_creacion(),
                publicacion.getIdUsuario(),
                datosRespuestaEtiquetas);
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

        List<DatosEtiqueta> datosRespuestaEtiquetas = publicacion.getEtiquetas().stream()
                .map(e -> new DatosEtiqueta(e.getNombre()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new DatosRespuestaPublicacion(
                publicacion.getId(),
                publicacion.getTitulo(),
                publicacion.getContenido(),
                publicacion.getFecha_creacion(),
                publicacion.getIdUsuario(),
                datosRespuestaEtiquetas));
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

        List<DatosEtiqueta> datosRespuestaEtiquetas = publicacion.getEtiquetas().stream()
                .map(e -> new DatosEtiqueta(e.getNombre()))
                .collect(Collectors.toList());

        var datosPublicacion=new DatosRespuestaPublicacion(
                publicacion.getId(),
                publicacion.getTitulo(),
                publicacion.getContenido(),
                publicacion.getFecha_creacion(),
                publicacion.getIdUsuario(),
                datosRespuestaEtiquetas);
        return ResponseEntity.ok(datosPublicacion);
    }
}
