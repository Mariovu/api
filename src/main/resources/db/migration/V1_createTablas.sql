CREATE TABLE autores (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL
);

CREATE TABLE publicaciones (
  id INT AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(255) NOT NULL,
  contenido TEXT NOT NULL,
  etiquetas VARCHAR(255) NOT NULL,
  autor_id INT NOT NULL,
  fecha_creacion TIMESTAMP NOT NULL,
  estado VARCHAR(255) NOT NULL,
  FOREIGN KEY (autor_id) REFERENCES autores(id)
);