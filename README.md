# API REST - Relatos de Papel

## Introducción al Diseño del API

Este documento lo hemos realizado para describir el diseño de nuestra **REST API**, para la gestión de libros. Está
orientado a desarrolladores que necesiten consumir los servicios del backend para generar peticiones al backend de *
*Relatos de Papel**, y a su vez su catálogo de libros.

Al ser una API RESTful cuenta con sus respectivas operaciones tales como búsqueda, creación, actualización o eliminación
de registros.

### Características Principales

La API sigue las **convenciones REST**, usando los métodos HTTP estandarizados:

- `GET` - Para consultar recursos
- `POST` - Para crear nuevos recursos
- `PUT` - Para actualizar recursos completamente
- `PATCH` - Para actualizar recursos parcialmente
- `DELETE` - Para eliminar recursos

### Formato de Respuestas

- Todas las respuestas están en formato **JSON**
- Permite el filtrado mediante parámetros de consulta
- Retorna códigos de estado HTTP adecuados (`200`, `201`, `400`, `404`, `500`) para indicar el resultado de cada
  operación

A continuación detallamos los endpoints disponibles, junto con su utilidad, parámetros, estructura y respuestas.

---

## Tabla de Métodos del API

| Método HTTP | URI           | Query Params                                                             | Cuerpo de la Petición                                                                                                                                                                                                                                    | Cuerpo de la Respuesta                                                                                                                                                                                                                                                                                                  | Códigos de Respuesta                                                            |
|-------------|----------------|--------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------|
| **GET**     | /libros      | `titulo`, `autor`, `fechaPublicacion`, `categoria`, `isbn`, `valoracion` | -                                                                                                                                                                                                                                                        |  `{"libros":[ {  "id": 1234, "titulo": "1986", "autor": "Jhon Doe", "fechaPublicacion": "1998-02-12", "categoria": "terror", "isbn": "12334", "valoracion": 3, "visibilidad": true, "cantidad": 12, "precio": 13.65 }]}` | `200 OK` <br> `400 Bad Request` <br> `500 Internal Server Error`                    |
| **GET**     | /libros/{id} | -                                                                        | -                                                                                                                                                                                                                                                        | ``` {   "id": 1234,   "titulo": "1986", "autor": "Jhon Doe", "fechaPublicacion": "1998-02-12", "categoria": "terror", "isbn": "12334", "valoracion": 3, "visibilidad": true, "cantidad": 12, "precio": 13.65 }```                                               | `200 OK`  <br>`400 Bad Request`  <br>`404 Not Found`  <br>`500 Internal Server Error` |
| **POST**    | /libros     | -                                                                        | ```{"titulo": "1986", "autor": "Jhon Doe", "fechaPublicacion": "1998-02-12","categoria": "terror", "isbn": "12334", "valoracion": 3, "visibilidad": true, "cantidad": 12, "precio": 13.65 }``` | ``` {  "id": 1234, "titulo": "1986", "autor": "Jhon Doe", "fechaPublicacion": "1998-02-12", "categoria": "terror", "isbn": "12334","valoracion": 3, "visibilidad": true, "cantidad": 12, "precio": 13.65 }```                                               | `201 Created` <br> `400 Bad Request`  <br>`500 Internal Server Error`               |
| **PUT**     | /libros/{id} | -                                                                        | ``` {   "titulo": "1986",   "autor": "Jhon Doe", "fechaPublicacion": "1998-02-12",   "categoria": "terror", "isbn": "12334", "valoracion": 3, "visibilidad": true,"cantidad": 12, "precio": 13.65 }``` | ``` { "id": 1234, "titulo": "1986","autor": "Jhon Doe", "fechaPublicacion": "1998-02-12", "categoria": "terror", "isbn": "12334", "valoracion": 3, "visibilidad": true, "cantidad": 12, "precio": 13.65 }```                                               | `200 OK` `400 Bad Request` <br> `404 Not Found` <br> `500 Internal Server Error` |
| **PATCH**   | /libros/{id} | -                                                                        | ``` {   "autor": "Jhon Doe", "fechaPublicacion": "1998-02-12", "categoria": "terror",   "isbn": "12334",   "valoracion": 3, "cantidad": 12 }```                                                                        | ``` { "id": 1234, "titulo": "1986", "autor": "Jhon Doe", "fechaPublicacion": "1998-02-12", "categoria": "terror", "isbn": "12334", "valoracion": 3, "visibilidad": true, "cantidad": 12, "precio": 13.65 }```                                               | `200 OK` <br> `400 Bad Request`  <br>`404 Not Found`  <br>`500 Internal Server Error` |
| **DELETE**  | /libros/{id} | -                                                                        | -                                                                                                                                                                                                                                                        | ``` { "id": 1234, "titulo": "1986", "autor": "Jhon Doe", "fechaPublicacion": "1998-02-12", "categoria": "terror", "isbn": "12334", "valoracion": 3, "visibilidad": true, "cantidad": 12, "precio": 13.65 }```                                               | `200 OK` `400 Bad Request`  <br>`404 Not Found`  <br>`500 Internal Server Error` |

---

## Notas Adicionales

### Filtrado de Libros

El endpoint `GET /libros` permite filtrar los resultados usando los siguientes parámetros de consulta:

- `titulo` - Filtra por título del libro
- `autor` - Filtra por autor
- `fechaPublicacion` - Filtra por fecha de publicación
- `categoria` - Filtra por categoría
- `isbn` - Filtra por ISBN
- `valoracion` - Filtra por valoración

### Campos del Modelo Libro

- `id` - Identificador único del libro
- `titulo` - Título del libro
- `autor` - Autor del libro
- `fechaPublicacion` - Fecha de publicación (formato: YYYY/MM/DD)
- `categoria` - Categoría del libro
- `isbn` - Número ISBN
- `valoracion` - Valoración del libro (1-5)
- `visibilidad` - Si el libro está visible en el catálogo
- `cantidad` - Cantidad disponible en inventario
- `precio` - Precio del libro
