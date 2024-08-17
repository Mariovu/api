package foro.voll.api.controller;

import foro.voll.api.domain.comentarios.Comentario;
import foro.voll.api.domain.etiquetas.*;
import foro.voll.api.domain.publicacion.DatosActualizarPublicacion;
import foro.voll.api.domain.publicacion.DatosRespuestaPublicacion;
import foro.voll.api.domain.publicacion.Publicacion;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/etiquetas")
@SecurityRequirement(name = "bearer-key")
public class EtiquetaController {
    @Autowired
    EtiquetaRepository etiquetaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaEtiqueta> crearEtiqueta(@RequestBody @Valid DatosEtiqueta datosEtiqueta,
                                                                   UriComponentsBuilder uriComponentsBuilder){
        Etiqueta etiqueta = new Etiqueta(datosEtiqueta);
        etiqueta = etiquetaRepository.save(etiqueta);
        DatosRespuestaEtiqueta datosRespuestaEtiqueta = new DatosRespuestaEtiqueta(
                etiqueta.getId(),
                etiqueta.getNombre());
        URI url = uriComponentsBuilder.path("/etiquetas/{id}").buildAndExpand(etiqueta.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaEtiqueta);
    }

    @GetMapping
    public ResponseEntity<Page<Etiqueta>> listarEtiqueta(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(etiquetaRepository.findByEstadoTrue(paginacion));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarEtiqueta(@RequestBody @Valid DatosActualizarEtiqueta datosActualizarEtiqueta){
        Etiqueta etiqueta=etiquetaRepository.getReferenceById(datosActualizarEtiqueta.id());
        etiqueta.actualizarDatos(datosActualizarEtiqueta);

        return ResponseEntity.ok(new DatosRespuestaEtiqueta(
                etiqueta.getId(),
                etiqueta.getNombre()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarEtiqueta(@PathVariable Long id ){
        Etiqueta etiqueta=etiquetaRepository.getReferenceById(id);
        etiqueta.desactivarPublicacion(etiqueta);
        return ResponseEntity.noContent().build();
    }




}
