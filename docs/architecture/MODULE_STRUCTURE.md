# MODULE STRUCTURE — Especificación Modular del Proyecto

> **WHO Animal v1**  
> `STATUS: FOUNDATION / DRAFT`

---

## 1. Arquitectura Modular Recomendada

Para asegurar la separación estricta de responsabilidades, la futura implementación en Android/Kotlin se estructurará en módulos desacoplados:

```text
whoanimalv1/
├── app/                        # Ensamblado final, Dependency Injection, Navigation Host
├── core/
│   ├── domain/                 # Entidades puras, Value Objects, Use Cases, Interfaces (0 Android deps)
│   ├── common/                 # Utilidades funcionales (Result, Dispatchers, Loggers)
│   └── designsystem/           # Tokens visuales Compose, Componentes base (HeroButton, Card, Typography)
├── data/
│   ├── local/                  # Persistencia Local-First (Room/SQLDelight, File Storage de fotos)
│   ├── identification/         # Adaptador del motor de visión zoológica (IA/ML offline/online)
│   └── camera/                 # Adaptador de hardware de cámara (CameraX wrapper desacoplado)
└── feature/
    ├── home/                   # Pantalla Campamento Base (TECHO, PECHO, ABDOMEN, PELVIS, PIES)
    ├── capture/                # Pantalla de visor de campo (FieldViewfinder)
    ├── identification/         # Pantalla de análisis e hipótesis asistida
    ├── discovery/              # Coreografía del momento cumbre de revelación
    ├── card/                   # Inspección detallada de los 3 pilares de la Card
    └── collection/             # Mochila del explorador y catálogo de cartas
```

---

## 2. Responsabilidad por Capa

### `:core:domain` (Kotlin Puro)
* **Dependencias:** Únicamente la librería estándar de Kotlin (`kotlin-stdlib`) y corrutinas base (`kotlinx-coroutines-core`).
* **Contenido:**
  * Modelos ontológicos: `Animal`, `Observation`, `IdentificationResult`, `IdentificationDecision`, `Capture`, `Card`, `Collection`.
  * Interfaces de Repositorio: `ObservationRepository`, `AnimalRepository`, `CaptureRepository`, `CardRepository`.
  * Interfaces de Servicios: `IdentificationEngineService`, `CameraCaptureService`.
  * Casos de Uso (Use Cases / Interactors).

### `:core:designsystem`
* **Dependencias:** Jetpack Compose Runtime, Foundation, UI.
* **Contenido:** Tokens oficiales (Mist, Sage, Forest, Deep Teal, Stone, Cream), tipografía editorial y componentes táctiles (`NaturalistCard`, `HeroDiscoverButton`).

### `:data:*` (Infraestructura y Persistencia)
* **Dependencias:** Room / SQLite, DataStore, CameraX, librerías de inferencia visual.
* **Contenido:** Implementación concreta de las interfaces del dominio mediante adaptadores.
