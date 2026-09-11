# DATA FLOW — Flujo de Datos Unidireccional y Pipeline Irreversible

> **WHO Animal v1**  
> `STATUS: FOUNDATION / DRAFT`

---

## 1. El Pipeline de Descubrimiento Irreversible

El ciclo central de Who Animal v1 opera como una máquina de estados estricta y de una sola dirección. No es posible forjar una carta sin cumplir previamente cada eslabón ontológico:

```text
[ DISPARO DE CÁMARA ]
         │
         ▼
[ 1. OBSERVATION ] ───────► Almacenamiento local de imagen y contexto ambiental
         │
         │ (Inferencia asistida por el motor de visión)
         ▼
[ 2. IDENTIFICATION RESULT ] ─► Hipótesis zoológica con certidumbre cualitativa
         │
         │ (Elección y ratificación consciente del explorador)
         ▼
[ 3. IDENTIFICATION DECISION ] ─► Ratificación de especie (o avistamiento no catalogado)
         │
         │ (Consagración transaccional del encuentro)
         ▼
[ 4. CAPTURE ] ──────────► Registro histórico en la bitácora del explorador
         │
         │ (Materialización estética de los 3 pilares)
         ▼
[ 5. CARD ] ─────────────► Pieza coleccionable física con identidad propia
         │
         │ (Persistencia atesorada)
         ▼
[ 6. COLLECTION ] ───────► Incorporación a la Mochila del Explorador
```

---

## 2. Invariantes de Transición y Reglas de Integridad

1. **Una `Card` no puede existir sin una `Capture`:** La carta es la materialización artística de un evento histórico concreto. No existen cartas "generadas en el vacío" ni compradas en sobres virtuales.
2. **Una `Capture` no puede existir sin una `IdentificationDecision`:** La aplicación jamás inscribe una captura sin el consentimiento o ratificación del explorador.
3. **Una `IdentificationDecision` no puede existir sin una `Observation`:** No se pueden ratificar especies sin una observación visual previa.
4. **Transaccionalidad Atómica en Local-First:** La escritura en el almacenamiento local de la `Capture` y la `Card` se realiza dentro de una única transacción atómica. Si falla la materialización de la carta, no se registra una captura huérfana.

---

## 3. Diagrama de Secuencia del Bucle Central

```text
Explorador          CaptureUI / VM       Domain UseCase       IdentificationEngine     Local Repository
    │                     │                    │                       │                      │
    │── Pulsa DISPARAR ──►│                    │                       │                      │
    │                     │── ObserveAnimal ──►│                       │                      │
    │                     │                    │── Guarda imagen ────────────────────────────►│
    │                     │                    │◄─ Retorna Observation ───────────────────────│
    │                     │                    │                                              │
    │                     │                    │── IdentifyObservation ──────►│               │
    │                     │                    │◄─ IdentificationResult ──────│               │
    │                     │◄─ Muestra Hipótesis│                                              │
    │                     │                    │                                              │
    │── Confirma especie ─►│                    │                                              │
    │   (Decision)        │── Consagrate ─────►│                                              │
    │                     │   CaptureAndCard   │── Transacción Atómica:                       │
    │                     │                    │   1. Guarda Capture  ───────────────────────►│
    │                     │                    │   2. Guarda Card     ───────────────────────►│
    │                     │                    │   3. Actualiza Collection ──────────────────►│
    │                     │◄─ Retorna Card ────│                                              │
    │◄─ Revela Card ──────│                    │                                              │
```
