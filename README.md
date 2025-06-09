# 🎓 Arquitectura de Microservicios - Plataforma de Academia

Bienvenido al repositorio del sistema backend para la gestión de una academia online, desarrollado con arquitectura de microservicios utilizando Spring Boot y tecnologías modernas para garantizar escalabilidad, resiliencia y mantenibilidad.

---

## 📖 Descripción General

Este proyecto representa una **plataforma educativa** donde se gestionan cursos, lecciones, inscripciones y pagos. Cada microservicio tiene su propia responsabilidad y se comunica con los demás mediante HTTP usando FeignClient. El sistema está protegido con Keycloak, lo cual permite autenticación y autorización robusta basada en roles.

---

## 🧩 Componentes Principales

### 🔐 Auth con Keycloak
- Keycloak gestiona el login, registro y emisión de tokens OAuth2.
- Todos los microservicios requieren autenticación.

### 🌐 API Gateway
- Enrutador principal de las peticiones.
- Aplica filtros, maneja la autorización y redirige al microservicio correspondiente.
- Conectado a Eureka y protegido por Keycloak.

### 🎓 `cursos-service`
- Maneja el ABM de cursos y lecciones.
- Solo los administradores pueden crear/eliminar/modificar cursos y lecciones.

### 📝 `inscripciones-service`
- Gestiona las inscripciones de usuarios a cursos.
- Valida cursos y pagos.

### 💰 `pagos-service`
- Registra y valida pagos de inscripciones.
- Integracion **API de Mercado Pago** para la gestión de pagos

### ⚙️ `config-server`
- Centraliza la configuración de todos los servicios.
- Lee propiedades desde un repositorio privado en GitHub.

### 🧭 `eureka-server`
- Descubrimiento de microservicios.
- Permite que los servicios se encuentren entre sí por nombre lógico.

### `common-dtos`
- Centraliza los Data Transfer Objects (DTOs) compartidos entre los microservicios.
- Facilita la reutilización y mantenimiento, evitando duplicación y asegurando consistencia en la comunicación entre servicios.

---

## ⚒️ Stack Tecnológico

`Java` • `Spring Boot` • `Spring Security` • `Spring Cloud` • `Feign` • `Keycloak` • `PostgreSQL` • `Docker` • `Jakarta Validation` • `MapStruct` • `Lombok` • `Mercado Pago API`

## 📂 Estructura del Proyecto

```text
academia-microservicios/
├── api-gateway/
├── config-server/
├── eureka-server/
├── cursos-service/
├── inscripciones-service/
├── pagos-service/
├── common-dtos/
└── docker-compose.yml

