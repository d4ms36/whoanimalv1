# DATA MODEL — Ontología de Dominio

> **WHO Animal v1**  
> `STATUS: FOUNDATION / DRAFT`

---

## 1. Distinciones Ontológicas Aprendidas

Uno de los aprendizajes clave del prototipo anterior es evitar modelos colapsados o identidades sobrecargadas. Se establecen las siguientes distinciones conceptuales fundamentales:

```text
Observation ≠ IdentificationResult
IdentificationResult ≠ Decision
Capture ≠ Card
Animal ≠ Capture
Animal ≠ Card
```

* **Animal:** La especie biológica en la naturaleza, con su taxonomía y datos zoológicos objetivos.
* **Observation:** El acto material y circunstancial de fotografiar o registrar un evento en un lugar y momento determinados.
* **IdentificationResult:** La hipótesis o veredicto emitido por el motor de identificación sobre qué animal corresponde a la observación.
* **Decision:** La confirmación o validación (automática o asistida) del resultado para su conversión en captura.
* **Capture:** El registro confirmado del encuentro en el historial del explorador.
* **Card:** La pieza coleccionable individualizada generada a partir de la captura, con su arte, atributos y lore.
* **Collection:** El conjunto estructurado de cartas pertenecientes al usuario.

---

## 2. Ciclo de Vida Conceptual

```text
Observation
     ↓
IdentificationResult
     ↓
Decision
     ↓
Capture
     ↓
Card
     ↓
Collection
```

> **Aclaración:** Estos principios guiarán el modelado de datos en Kotlin cuando se autorice la fase de dominio. No representan clases ni entidades de código en la fase actual.
