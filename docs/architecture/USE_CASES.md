# USE CASES — Casos de Uso del Dominio (Interactors)

> **WHO Animal v1**  
> `STATUS: FOUNDATION / DRAFT`

---

## 1. Catálogo Formal de Casos de Uso del Núcleo

Cada caso de uso encapsula una única operación de negocio pura, implementando el patrón ejecutor (`operator fun invoke`):

---

### `ObserveAnimalUseCase`
* **Propósito:** Recibe el stream de bytes de la cámara o archivo temporal, genera el identificador único de observación, captura los metadatos ambientales (hora, luz) y persiste la `Observation` en el almacenamiento local.
* **Entrada:** `ImageRawPayload`, `EnvironmentalMetadata`.
* **Salida:** `Result<Observation>`.
* **Invariantes:** La imagen se conserva en su resolución original de campo sin deformaciones.

---

### `IdentifyObservationUseCase`
* **Propósito:** Envía la `Observation` al motor de visión zoológica y transforma la inferencia técnica en un `IdentificationResult` con nivel cualitativo de certeza y alternativas.
* **Entrada:** `ObservationId`.
* **Salida:** `Result<IdentificationResult>`.
* **Invariantes:** Nunca afirma infalibilidad; ante baja confianza califica el resultado como `UNCERTAIN`.

---

### `ConfirmIdentificationDecisionUseCase`
* **Propósito:** Registra la ratificación consciente del usuario ante la hipótesis zoológica presentada.
* **Entrada:** `ObservationId`, `SelectedAnimalId?`, `DecisionType`.
* **Salida:** `Result<IdentificationDecision>`.
* **Invariantes:** Requiere acción voluntaria del usuario.

---

### `ConsagrateCaptureAndCardUseCase`
* **Propósito:** Caso de uso maestro del descubrimiento. Concatena la decisión validada, crea la `Capture` histórica con protección de coordenadas para especies vulnerables, genera la `Card` con sus tres pilares (Científico, Experiencia y Lore) y la incorpora atómicamente a la `Collection`.
* **Entrada:** `IdentificationDecisionId`, `UserFieldNotes?`.
* **Salida:** `Result<Card>`.
* **Invariantes:** Atómico; genera exactamente una Card; snapshots biológicos fidedignos; Lore señalizado con aviso de ficción.

---

### `GetExplorerBackpackUseCase`
* **Propósito:** Recupera la colección completa de cartas del explorador, permitiendo ordenación por fecha de encuentro o agrupación por familias taxonómicas.
* **Entrada:** `CollectionFilterCriteria?` (familia, clase, período temporal).
* **Salida:** `Flow<List<Card>>`.
* **Invariantes:** Consulta local reactiva y fluida.

---

### `GetCardDetailUseCase`
* **Propósito:** Provee la vista expandida e inspección táctil de una carta específica, desplegando los tres pilares completos y el sello preventivo de seguridad si aplica.
* **Entrada:** `CardId`.
* **Salida:** `Result<CardDetailView>`.
* **Invariantes:** Proporciona datos científicos reales contrastados y advertencia explícita de ficción en el Lore.

---

### `GetRecentExpeditionFindingsUseCase`
* **Propósito:** Alimenta el estrato ABDOMEN de la Home con las últimas 2 o 3 capturas del explorador.
* **Entrada:** `limit: Int = 3`.
* **Salida:** `Flow<List<CaptureSummary>>`.
* **Invariantes:** Ligero, reactivo y enfocado en la continuidad de las salidas de campo.
