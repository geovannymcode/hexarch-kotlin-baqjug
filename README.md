# Arquitectura Hexagonal en Kotlin

Esta guía acompaña la charla de BAQJUG "El dominio que no conoce a Spring", y se puede seguir sola, de principio a fin. Construimos desde cero un servicio de pagos con arquitectura hexagonal en Kotlin, y al final una interfaz en Vaadin.

Si nunca armaste un proyecto hexagonal, tranquilo: empezamos generando el proyecto con el asistente de Spring, agregando dependencias y creando las carpetas una por una. Y si nunca tocaste Kotlin, también: vamos explicando cada cosa del lenguaje a medida que aparece.

Kotlin no es solo para mobile — corre sobre la JVM, es ciudadano de primera en Spring Boot, y es una opción seria para backend.

---

## El problema que motiva todo

Un equipo migró de un monolito en PHP a microservicios en Kotlin, pero dentro de cada servicio copió la arquitectura en capas de siempre. El negocio conocía el framework y a los proveedores. Las consecuencias fueron concretas:

- Cambiar una pasarela rompía la lógica de negocio.
- Probar el negocio pedía levantar toda la infraestructura.
- Nadie sabía dónde vivía la regla de negocio.

La arquitectura hexagonal mueve el negocio al centro y deja afuera, como piezas reemplazables, todo lo técnico.

---

## Qué vamos a construir

Un servicio de pagos (`payments`) que:

1. Recibe una orden de pago por una API REST.
2. Cobra a través de una pasarela de pago (proveedor externo simulado).
3. Guarda el pago en PostgreSQL.
4. Al final expone una interfaz web en Vaadin.

```
[ REST / Vaadin ] → payments (hexágono) → [ JPA → PostgreSQL ]
                                         → [ Feign ] → pasarela de pago
```

El punto central del taller: cuando se cambie de proveedor de pago, no se toca una sola línea del negocio.

---

## Sesiones

| Sesión | Rama | Qué construye |
|--------|------|---------------|
| 0 | (sin rama) | El problema, los conceptos y los prerrequisitos |
| 1 | `sesion-1` | El proyecto con Spring Initializr y el dominio |
| 2 | `sesion-2` | Puertos, servicio y test sin Spring |
| 3 | `sesion-3` | Adaptadores REST, JPA y la pasarela por Feign |
| 4 | `sesion-4` | El momento "wow": cambiar de proveedor sin tocar el negocio |
| 5 | `sesion-5` = `main` | La interfaz en Vaadin |

Para seguir el taller paso a paso, haz `git checkout sesion-1` y avanza sesión por sesión. Cada rama deja el proyecto en el estado exacto de esa sesión. La rama `main` es la versión final.

---

## Stack

- Kotlin 2.4
- Spring Boot 4.0.6
- Java 21
- Spring Cloud (OpenFeign)
- PostgreSQL 17
- Vaadin 25

En start.spring.io las versiones cambian con frecuencia. Si no aparece Spring Boot 4.0.6, elige la versión 4.x estable más cercana.

---

## Estructura del proyecto

```
workshop-hexarc-baqjug/
├── payments/                          # Servicio principal
│   └── src/main/kotlin/com/baqjug/payments/
│       ├── PaymentsApplication.kt
│       ├── domain/
│       │   └── model/
│       │       ├── Money.kt
│       │       ├── Payment.kt
│       │       ├── PaymentId.kt
│       │       └── PaymentStatus.kt
│       ├── application/
│       │   ├── port/
│       │   │   ├── in/
│       │   │   │   └── ProcessPaymentUseCase.kt
│       │   │   └── out/
│       │   │       ├── PaymentGateway.kt
│       │   │       └── PaymentRepository.kt
│       │   └── service/
│       │       └── ProcessPaymentService.kt
│       └── infrastructure/
│           └── adapter/
│               ├── in/
│               │   ├── web/
│               │   │   ├── PaymentController.kt
│               │   │   └── dto/PaymentDtos.kt
│               │   └── ui/
│               │       └── PaymentView.kt
│               └── out/
│                   ├── gateway/
│                   │   ├── ProviderAAdapter.kt
│                   │   ├── ProviderAClient.kt
│                   │   ├── ProviderBAdapter.kt
│                   │   └── ProviderBClient.kt
│                   └── persistence/
│                       ├── PaymentJpaEntity.kt
│                       ├── PaymentPersistenceMapper.kt
│                       ├── PaymentRepositoryAdapter.kt
│                       └── SpringDataPaymentRepository.kt
├── provider-sim/                      # Simulador de pasarela de pago
│   └── src/main/kotlin/com/baqjug/providersim/
│       ├── ProviderSimApplication.kt
│       └── ChargeController.kt
└── test.http                          # Ejemplos de peticiones HTTP
```

---

## Cómo ejecutar el proyecto

El proyecto usa `spring-boot-docker-compose`: al arrancar el servicio principal, Spring Boot levanta automáticamente el `compose.yaml` (PostgreSQL). No hace falta correr `docker compose` a mano.

```bash
# Levantar el simulador de pasarela (en otra terminal)
cd provider-sim
./gradlew bootRun

# Levantar el servicio principal (levanta PostgreSQL solo)
cd payments
./gradlew bootRun
```

La app queda disponible en `http://localhost:8080`.
