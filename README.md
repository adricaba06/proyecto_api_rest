# Gestión de Películas - API REST con Spring Boot

## Introducción
Este proyecto es una **API REST** desarrollada con Spring Boot para gestionar información sobre películas, directores y actores.  

Permite realizar operaciones CRUD (crear, leer, actualizar y borrar) y también asignar actores a películas.  

La documentación incluye:
- Endpoints de la API  
- Documentación con OpenAPI y Swagger  
- Una colección de Postman para probar las peticiones  

---

## Modelo de Datos
El sistema se basa en tres entidades principales y una relación **muchos a muchos** entre películas y actores.

### Director
- id (Long, PK)  
- nombre (String)  
- anioNacimiento (Integer)  
- Relación: un director puede dirigir varias películas  

### Película
- id (Long, PK)  
- titulo (String, único)  
- genero (String)  
- fechaEstreno (LocalDate)  
- Relación: cada película tiene un director y puede tener varios actores  

### Actor
- id (Long, PK)  
- nombre (String)  
- Relación: un actor puede participar en varias películas y una película puede tener varios actores  

---

## Funcionalidades
- Directores: listar, crear, actualizar y eliminar  
- Películas: listar, crear, actualizar y eliminar  
- Actores: listar y crear  
- Asignación de actores a películas:  
  - `POST /api/v1/peliculas/{peliculaId}/actores/{actorId}` → asignar un actor a una película  
  - `GET /api/v1/peliculas/{peliculaId}` → obtener una película con su reparto de actores  

---

## Gestión de Errores
La API devuelve códigos de error estándar:

- **404 Not Found**  
  Cuando se intenta acceder a un recurso inexistente (película, director o actor).  

- **400 Bad Request**  
  Si se intenta asignar un director menor de 18 años a una película.  
  Si los datos no cumplen las reglas de validación.  

- **409 Conflict**  
  Al crear una película con un título duplicado.  
  Al asignar un actor que ya está en el reparto de esa película.  

---

## Diseño
- Uso de **DTOs** para organizar las respuestas  
- Ejemplo: `PeliculaResponseDTO` incluye un `DirectorSimpleDTO` y una lista de `ActorSimpleDTO`  
- Documentación completa de endpoints y errores en **Swagger/OpenAPI**  
