# 🎓 Academia - Arquitectura de Microservicios 
## 📖 Descripción General

Este proyecto representa una **plataforma educativa** donde se gestionan cursos, lecciones, inscripciones y pagos. Cada microservicio tiene su propia responsabilidad y se comunica con los demás mediante HTTP usando FeignClient. El sistema está protegido con Keycloak, lo cual permite autenticación y autorización robusta basada en roles.

## ⚒️ Stack Tecnológico

`Java` • `Spring Boot` • `Spring Security` • `Spring Cloud` • `Feign` • `Keycloak` • `PostgreSQL` • `Docker` • `Mercado Pago API` • `MapStruct` • `Jakarta Validation` • `Lombok`

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

### 📦 `common-dtos`
- Centraliza los Data Transfer Objects (DTOs) compartidos entre los microservicios.
- Facilita la reutilización y mantenimiento, evitando duplicación y asegurando consistencia en la comunicación entre servicios.

---

## 📂 Estructura del Proyecto

<img width="790" alt="diagrama microservicos" src="https://github.com/user-attachments/assets/8ad9dbdf-c4f8-4b37-a0c6-1b069ff070ff" />

## 🚀 Funcionalidades Planeadas

Estas son algunas funcionalidades en las que voy a estar trabajando para seguir mejorando el proyecto:

- [ ] 👤 **Servicio de Usuario-Perfil**  
  Crear un microservicio separado para gestionar información personalizada del perfil de usuario, incluyendo:
  - 🎂 Fecha de cumpleaños
  - 🖼️ Avatar
  - ✍️ Biografía (bio)

- [ ] 📢 **Integración con Event Listener**  
  Al momento de registrar un nuevo usuario, se emitirá un evento desde keycloak que será escuchado por el microservicio de perfil para crear automáticamente una entidad `UsuarioPerfil`.

- [ ] 🧪 **Testing Automatizado**  
  - Unit tests con JUnit y Mockito
  - Tests de integración entre microservicios
  - Tests de los endpoints (posiblemente con Testcontainers + Postman/Newman o RestAssured)

---

