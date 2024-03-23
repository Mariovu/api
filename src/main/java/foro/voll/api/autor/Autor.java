package foro.voll.api.autor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name="autores")
@Entity(name="Autor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public Autor(DatosAutor autor) {
        this.id= autor.id();
        this.nombre=autor.nombre();
    }
}
