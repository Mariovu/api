package foro.voll.api.controller;

import foro.voll.api.publicacion.DatosPublicacion;
import foro.voll.api.publicacion.Publicacion;
import foro.voll.api.publicacion.PublicacionRepository;
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
    public void crearPublicacion(@RequestBody DatosPublicacion datosPublicacion){
        System.out.println((new Publicacion(datosPublicacion)));
    }
}
