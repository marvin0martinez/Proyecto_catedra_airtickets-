# Proyecto_catedra_airtickets-

# AirTickets – Single App (Spring Boot + React)

Proyecto integrado: **Backend Spring Boot 3 + JWT + JPA + Swagger** y **Frontend React (Vite)**
empaquetado como **una sola app**. El frontend se construye con Maven y se copia a
`src/main/resources/static`.

## Distribucion
- Rama Marvin: Proyecto (codio Backend y Fronent).
- Rama Stanley Documento
- Rama Jonatham Manual tecnico y Base de Datos

## Requisitos
- Java 17
- Maven 3.9+
- MySQL en `localhost:3306` con schema `airtickets` (se crea automáticamente).

Configurar credenciales en `src/main/resources/application.yml` si es necesario.

## Ejecutar
mvn clean package
mvn spring-boot:run
# Abrir: http://localhost:8080
# Swagger: http://localhost:8080/swagger-ui.html


## Login
- **Correo:** admin@air.tix 
- **Password:** 123456
- **Rol:** ADMIN
-  **Correo:** user@air.tix
- **Password:** 123456
- **Rol:** User


Los menús de administración aparecen si el JWT contiene el rol `ADMIN`.

## Endpoints principales
- `POST /api/auth/login` – devuelve `accessToken` y roles.
- `POST /api/flights/search` – búsqueda de vuelos.
- `POST /api/reservations` – crear reservación.
- `POST /api/reservations/<built-in function id>/pay` – registrar pago.
- `GET /api/claims` / `POST /api/claims` – reclamos.
- `GET /api/admin/users` – administración de usuarios (solo ADMIN).

## Notas
- Validaciones vía Bean Validation (+ manejador global de errores).
- Seguridad: Spring Security stateless con JWT.
- Documentación: springdoc-openapi (`/swagger-ui.html`).
