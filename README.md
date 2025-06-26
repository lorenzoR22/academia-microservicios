# 🎓 Academia - Arquitectura de Microservicios 
## 📖 Descripción General

- **Plataforma educativa basada en microservicios** para gestionar cursos, lecciones, inscripciones, pagos y notificaciones.
- Los servicios se comunican principalmente vía HTTP con **FeignClient y usan Kafka** para mensajería asíncrona en eventos clave, como inscripciones.
- El sistema usa **Keycloak** para autenticación y autorización por roles, y **Resilience4j** para resiliencia con Circuit Breaker y fallback.
- La configuración está centralizada en un **Config Server** que lee propiedades desde un repositorio privado en **GitHub** y el descubrimiento de servicios se hace con **Eureka**.

## ⚒️ Stack Tecnológico

`Java` • `Spring Boot` • `Spring Security` • `Spring Cloud` • `Feign` • `Keycloak` • `PostgreSQL` • `Docker` • `Mercado Pago API` • `Resilience4j` • `Kafka` • `MapStruct` • `Jakarta Validation` • `Lombok`

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
- Consume eventos de **Kafka** provenientes de **inscripciones-service** para registrar las inscripciones realizadas.
- Almacena el userId y el cursoId en una tabla local que actúa como vista materializada o acceso optimizado, lo que permite:
  - Consultas más rápidas sobre qué usuarios están inscriptos a cada curso.
  - Evitar llamadas cruzadas innecesarias entre microservicios en tiempo de ejecución.
- Esta estrategia mejora la eficiencia en endpoints de tipo /api/cursos/{cursoId}/lecciones/{id}.

### 📝 `inscripciones-service`
- Gestiona las inscripciones de usuarios a cursos.
- Valida cursos y pagos.
- Publica eventos a **Kafka** cuando se registra una nueva inscripción.

### 💰 `pagos-service`
- Registra y valida pagos de inscripciones.
- Integracion **API de Mercado Pago** para la gestión de pagos
- Utiliza **Resilience4j** con **Circuit Breaker** para proteger dichas llamadas en caso de errores o demoras excesivas.
- Implementa lógica de **fallback** para garantizar estabilidad y tolerancia a fallos.
### 📧 `notificacion-service`
- Gestiona las notificaciones de la aplicacion.
- Envía correos electrónicos de confirmación de inscripción.
- Consume eventos de **Kafka** provenientes de **inscripciones-service** para enviar correos electronicos a los usuarios.

### ⚙️ `config-server`
- Centraliza la configuración de todos los servicios.
- Lee propiedades desde un repositorio privado en **GitHub**.

### 🧭 `eureka-server`
- Descubrimiento de microservicios.
- Permite que los servicios se encuentren entre sí por nombre lógico.

### 📦 `common`
- Centraliza los Data Transfer Objects (DTOs) compartidos entre los microservicios.
- Centraliza las excepciones compartidas entre los microservicios.
- Facilita la reutilización y mantenimiento, evitando duplicación y asegurando consistencia en la comunicación entre servicios.

### 📡 `Comunicación Asíncrona con Kafka`
- Se utiliza **Apache Kafka** como sistema de mensajería entre microservicios.
- El **inscripciones-service** publica eventos en un topic al registrar nuevas inscripciones.
- Estos eventos son consumidos por otros servicios, como cursos-service, que los utiliza para actualizar su base de datos local.
- Esto permite extender funcionalidades (como notificaciones, estadísticas o validaciones adicionales) sin acoplar directamente los servicios entre sí.
- **Kafka y Zookeeper** están configurados vía Docker y disponibles para uso local.

### 🛡️ `Tolerancia a Fallos`
- Gracias a **Resilience4j**, el sistema aplica políticas de resiliencia para llamadas HTTP entre servicios.
- El pagos-service implementa un **Circuit Breaker** para proteger las llamadas al inscripciones-service y cursos-service.
- En caso de caída o latencia excesiva, se activa un **fallback** que evita errores en cascada y mejora la experiencia del usuario.

---





