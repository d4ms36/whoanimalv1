# ARCHITECTURE OVERVIEW — Fundamentos de Arquitectura de Software

> **WHO Animal v1**  
> `STATUS: FOUNDATION / DRAFT`

---

## 1. Visión y Principios Rectores

Who Animal v1 se diseña bajo los principios de **Clean Architecture** y **Domain-Driven Design (DDD)**. La arquitectura garantiza que la lógica zoológica, las reglas de cartas y la experiencia del explorador permanezcan totalmente desacopladas de frameworks de interfaz, motores concretos de inteligencia artificial o tecnologías de base de datos.

```text
┌─────────────────────────────────────────────────────────────┐
│                    PRESENTATION LAYER                       │
│    (Jetpack Compose UI, ViewModels, UI State, Navigation)   │
├──────────────────────────────┬──────────────────────────────┤
│                              │                              │
│              ▼               │                              │
│    ┌──────────────────┐      │                              │
│    │   DOMAIN LAYER   │◄─────┤ (Dependency Inversion)       │
│    │ (Entities, DDD,  │      │                              │
│    │   Use Cases,     │      │                              │
│    │  Repository Intf)│      │                              │
│    └─────────▲────────┘      │                              │
│              │               │                              │
│                              ▼                              │
├─────────────────────────────────────────────────────────────┤
│                       DATA LAYER                            │
│  (Local-First DB, File Storage, Vision Engine, Camera)      │
└─────────────────────────────────────────────────────────────┘
```

---

## 2. Los Cuatro Pilares Arquitectónicos

1. **Independencia Absoluta del Dominio (Clean Architecture):**  
   El núcleo de dominio es Kotlin puro (estándar JVM / Kotlin Multiplatform ready). No tiene dependencias de `android.*`, `androidx.*`, frameworks UI ni librerías de persistencia.
2. **Dirección Estricta de Dependencias:**  
   Las dependencias apuntan exclusivamente hacia adentro. La capa de presentación y la capa de datos dependen de las interfaces del dominio; el dominio no depende de nadie.
3. **Fundación Local-First:**  
   El dispositivo del explorador es la fuente única de verdad. Toda captura, fotografía y carta se persiste y procesa localmente sin exigir conectividad remota obligatoria ni cuentas en la nube para el MVP.
4. **Flujo de Datos Unidireccional e Irreversible (Controlled Pipeline):**  
   La progresión del descubrimiento es una máquina de estados estricta y transaccional: una `Card` solo puede nacer de una `Capture` validada, la cual requiere una `IdentificationDecision` explícita.
