package foro.voll.api.controller;

import foro.voll.api.domain.comentarios.Comentario;
import foro.voll.api.domain.publicacion.DatosPublicacion;
import foro.voll.api.domain.publicacion.DatosRespuestaPublicacion;
import foro.voll.api.domain.publicacion.Publicacion;
import foro.voll.api.domain.usuarios.Usuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/comentarios")
@SecurityRequirement(name = "bearer-key")
public class ComentarioController {
    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaPublicacion> crearPublicacion(){
        return null;
    }
}
