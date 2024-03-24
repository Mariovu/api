package foro.voll.api.controller;

import foro.voll.api.publicacion.DatosPublicacion;
import foro.voll.api.publicacion.Publicacion;
import foro.voll.api.publicacion.PublicacionRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    public void crearPublicacion(@RequestBody @Valid DatosPublicacion datosPublicacion){
        publicacionRepository.save(new Publicacion(datosPublicacion));
    }
}
