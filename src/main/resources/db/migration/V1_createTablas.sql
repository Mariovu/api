
CREATE TABLE publicaciones (
  id INT AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(255) NOT NULL,
  contenido TEXT NOT NULL,
  etiqueta VARCHAR(255) NOT NULL,
  fecha_creacion TIMESTAMP NOT NULL,
  estado VARCHAR(255) NOT NULL
);