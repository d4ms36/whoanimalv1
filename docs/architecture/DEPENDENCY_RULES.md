# DEPENDENCY RULES — Reglas de Dependencia e Inversión de Control

> **WHO Animal v1**  
> `STATUS: FOUNDATION / DRAFT`

---

## 1. La Regla de Oro de Dependencias (The Dependency Rule)

Las dependencias en Who Animal v1 apuntan **exclusivamente hacia adentro**, hacia el núcleo de dominio:

```text
┌─────────────────────────────────────────────────────────────┐
│                    CAPA DE PRESENTACIÓN                     │
│               (:feature:* , :core:designsystem)             │
│                              │                              │
│                              ▼                              │
│                    ┌──────────────────┐                     │
│                    │  CAPA DE DOMINIO │                     │
│                    │  (:core:domain)  │                     │
│                    └──────────────────┘                     │
│                              ▲                              │
│                              │                              │
│                     CAPA DE INFRAESTRUCTURA                 │
│                     Y DATOS (:data:*)                       │
└─────────────────────────────────────────────────────────────┘
```

---

## 2. Inviolabilidad del Dominio (`:core:domain`)

El módulo de dominio contiene las reglas de negocio puras, la ontología zoológica y los casos de uso.
* **PROHIBICIÓN ESTRICTA:** Queda terminantemente prohibido importar en el dominio cualquier paquete de:
  * `android.*` o `androidx.*` (incluyendo Context, Lifecycle, ViewModel, Compose).
  * Librerías de persistencia (Room, SQLite, DataStore).
  * Librerías de red o serialización ligadas a frameworks (Retrofit, OkHttp).
  * Librerías de hardware de cámara (CameraX).
  * Frameworks de inyección de dependencias invasivos en las entidades.
* **Permitido:** Lenguaje Kotlin estándar (`kotlin-stdlib`), corrutinas base puras (`kotlinx-coroutines-core`) y tipos funcionales de resultado.

---

## 3. Inversión de Dependencias (DIP)

Toda interacción del dominio con el exterior se realiza a través de interfaces declaradas en el propio dominio:

| Necesidad de Dominio | Interfaz Declarada en Dominio | Implementación en Capa de Datos |
| :--- | :--- | :--- |
| Almacenar y leer cartas | `CardRepository` | `LocalCardRepository` (:data:local) |
| Almacenar capturas | `CaptureRepository` | `LocalCaptureRepository` (:data:local) |
| Consultar especies biológicas | `AnimalRepository` | `LocalAnimalRepository` (:data:local) |
| Persistir fotos y observaciones | `ObservationRepository` | `DiskObservationRepository` (:data:local) |
| Inferencia visual zoológica | `IdentificationEngine` | `MLKitIdentificationEngine` (:data:identification) |
| Disparo de cámara de campo | `CameraCaptureService` | `CameraXService` (:data:camera) |

---

## 4. Reglas entre Presentación y Datos

* **Presentación NUNCA accede a Datos directamente:** Una pantalla o ViewModel en `:feature:*` jamás interactúa con Room DAOs, DataStore o servicios de cámara. Siempre se comunica mediante los Casos de Uso (`UseCases`) expuestos por `:core:domain`.
* **Mapeo de Modelos (DTO vs Domain vs UIState):**
  * Los modelos de base de datos (`Entity / DTO`) se mapean a modelos de dominio antes de salir de `:data:*`.
  * Los modelos de dominio se proyectan en `UIState` inmutables dentro del ViewModel de presentación.
