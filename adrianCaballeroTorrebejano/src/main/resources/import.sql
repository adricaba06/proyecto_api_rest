
INSERT INTO director (id, nombre, anio_nacimiento) VALUES (1, 'Christopher Nolan', 1970);
INSERT INTO director (id, nombre, anio_nacimiento) VALUES (2, 'Denis Villeneuve', 1967);
INSERT INTO director (id, nombre, anio_nacimiento) VALUES (3, 'M. Night Shyamalan', 1970);


INSERT INTO actor (id, nombre) VALUES (1, 'Matthew McConaughey');
INSERT INTO actor (id, nombre) VALUES (2, 'Anne Hathaway');
INSERT INTO actor (id, nombre) VALUES (3, 'Timothée Chalamet');
INSERT INTO actor (id, nombre) VALUES (4, 'Bruce Willis');


INSERT INTO pelicula (id, titulo, genero, fecha_estreno, director_id) VALUES (1, 'Interstellar', 'Ciencia Ficción', '2014-11-07', 1);

INSERT INTO pelicula (id, titulo, genero, fecha_estreno, director_id) VALUES (2, 'Dune', 'Ciencia Ficción', '2021-10-22', 2);

INSERT INTO pelicula (id, titulo, genero, fecha_estreno, director_id) VALUES (3, 'El Sexto Sentido', 'Thriller', '1999-08-06', 3);


INSERT INTO actores_peliculas (pelicula_id, actor_id) VALUES (1, 1);

INSERT INTO actores_peliculas (pelicula_id, actor_id) VALUES (1, 2);
INSERT INTO actores_peliculas (pelicula_id, actor_id) VALUES (2, 3);
INSERT INTO actores_peliculas (pelicula_id, actor_id) VALUES (3, 4);


ALTER TABLE pelicula ALTER COLUMN id RESTART WITH 4;
ALTER TABLE director ALTER COLUMN id RESTART WITH 4;
ALTER TABLE actor ALTER COLUMN id RESTART WITH 5;
