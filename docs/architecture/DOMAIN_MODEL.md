# DOMAIN MODEL — Modelo de Dominio y Ontología Formal

> **WHO Animal v1**  
> `STATUS: FOUNDATION / DRAFT`

---

## 1. La Ontología Formal de Who Animal v1

Para erradicar la deuda técnica del prototipo histórico y evitar modelos colapsados o sobrecargados, el dominio establece seis distinciones conceptuales inviolables:

```text
Observation            ≠  IdentificationResult
IdentificationResult   ≠  IdentificationDecision
IdentificationDecision ≠  Capture
Capture                ≠  Card
Animal                 ≠  Capture
Animal                 ≠  Card
```

---

## 2. Definición Formal de Entidades y Value Objects

### 1. `Animal` (Entidad Biológica Objetiva)
* **Naturaleza:** La especie biológica en el planeta Tierra. Existe independientemente de si un usuario la ha avistado o no.
* **Invariantes:** Universal, objetiva, no mutable por acciones del usuario.
* **Atributos:**
  * `id`: Identificador taxonómico canónico (UUID / slug unívoco).
  * `commonName`: Nombre común en el idioma del usuario (ej. *"Mirlo común"*).
  * `scientificName`: Nombre binomial binomial riguroso (ej. *"Turdus merula"*).
  * `taxonomy`: Reino, Filo, Clase, Orden, Familia, Género.
  * `iucnStatus`: Categoría oficial de conservación (LC, NT, VU, EN, CR, EW, EX).
  * `biologyProfile`: Descripción concisa de dieta, hábitat y comportamiento.
  * `cautionProfile`: Información de seguridad objetiva y recomendaciones de distancia (Pet Friendly).

---

### 2. `Observation` (Avistamiento en Bruto)
* **Naturaleza:** El acto material y físico de registrar visualmente una escena en el mundo real.
* **Invariantes:** Inmutable. Una vez tomada la fotografía, representa el hecho empírico ocurrido en un instante exacto.
* **Atributos:**
  * `id`: Identificador único de observación.
  * `imageReference`: Referencia URI/Path al archivo de imagen original almacenado localmente.
  * `timestamp`: Marca de tiempo ISO-8601 del instante del disparo.
  * `environmentalContext`: Momento lumínico (Dawn, Day, Dusk, Night) y notas sensoriales.

---

### 3. `IdentificationResult` (Hipótesis Zoológica de la Máquina)
* **Naturaleza:** El diagnóstico probabilístico emitido por el motor de visión zoológica ante una `Observation`.
* **Invariantes:** Es una hipótesis de campo, **nunca una verdad absoluta**. La aplicación asume formalmente que puede equivocarse.
* **Atributos:**
  * `observationId`: Identificador de la observación analizada.
  * `primaryCandidate`: Especie sugerida (`AnimalId`) con mayor coincidencia.
  * `certaintyLevel`: Grado cualitativo de confianza (`HIGH`, `MODERATE`, `UNCERTAIN`).
  * `alternatives`: Lista ordenada de hasta 3 especies candidatas secundarias plausibles.
  * `diagnostics`: Metadatos técnicos de análisis de silueta y características visibles.

---

### 4. `IdentificationDecision` (Ratificación Consciente del Explorador)
* **Naturaleza:** El acto deliberado y consciente del usuario que convierte la hipótesis en una decisión de campo.
* **Invariantes:** Exige la voluntad explícita del explorador. El sistema no auto-consagra cartas a espaldas del usuario.
* **Atributos:**
  * `observationId`: Referencia a la observación.
  * `chosenAnimalId`: El animal seleccionado (puede ser el candidato primario, una alternativa o nulo si no se cataloga).
  * `decisionType`: `CONFIRMED_PRIMARY`, `SELECTED_ALTERNATIVE`, `MARKED_UNCATALOGUED`, `DISCARDED`.
  * `decidedAt`: Marca de tiempo de la decisión.

---

### 5. `Capture` (Registro Histórico del Encuentro)
* **Naturaleza:** La inscripción formal del avistamiento en el historial del explorador.
* **Invariantes:** Solo se crea si existe una `IdentificationDecision` válida. Inmutable en su núcleo empírico.
* **Atributos:**
  * `id`: Identificador único de captura.
  * `animalId`: Referencia al animal ratificado.
  * `observationId`: Referencia a la observación original.
  * `fieldNotes`: Anotaciones personales del explorador sobre el encuentro (editables).
  * `locationContext`: Ubicación general protegida (con anonimización/redondeo en especies vulnerables para prevenir furtivismo).

---

### 6. `Card` (Artefacto Coleccionable Consagrado)
* **Naturaleza:** La pieza física/digital personalizada generada a partir de la captura.
* **Invariantes:** Una captura produce exactamente una Card. Múltiples capturas del mismo `Animal` generan múltiples cartas independientes con fotografías y contextos únicos.
* **Atributos:**
  * `id`: Identificador único de carta.
  * `captureId`: Referencia unívoca a la captura de origen.
  * `cardProportion`: Proporción formal 5:7.
  * **Pilar 1 (Científico):** Snapshot de datos biológicos del `Animal`.
  * **Pilar 2 (Experiencia):** Fotografía del usuario, fecha, notas de campo.
  * **Pilar 3 (Lore Lúdico):** Narrativa fantástica/mitológica con su advertencia obligatoria y visible de ficción.
  * `finishAttributes`: Sello heráldico y textura táctil de cartulina Cream.

---

### 7. `Collection` (Aggregate Root: La Mochila del Explorador)
* **Naturaleza:** El conjunto organizado de cartas y avistamientos pertenecientes al explorador.
* **Responsabilidades:**
  * Custodiar la integridad de las cartas acumuladas.
  * Proveer consultas ordenadas por taxonomía, cronología y familias biológicas.
  * Exponer estadísticas de campo serenas (total de especies avistadas, familias exploradas) sin métricas competitivas ni ansiosas.
