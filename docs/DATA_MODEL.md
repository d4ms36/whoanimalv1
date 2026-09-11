# DATA MODEL — Ontología de Dominio y Flujo de Estados

> **WHO Animal v1**  
> `STATUS: FOUNDATION / DRAFT`

---

## 1. Ontología Fundamental

Who Animal v1 se estructura sobre seis conceptos de dominio netamente diferenciados:

```text
Observation ≠ IdentificationResult
IdentificationResult ≠ Decision
Capture ≠ Card
Animal ≠ Capture
Animal ≠ Card
```

### Definiciones Conceptuales:

1. **Animal:** Representa la especie biológica. Atributos: identificador taxonómico, nombre común, nombre científico binomial, familia, reino, descripción biológica, estado de conservación (UICN).
2. **Observation:** Representa el acto material del avistamiento. Atributos: imagen capturada en bruto, marca de tiempo (timestamp), datos ambientales contextuales (luz, momento del día).
3. **IdentificationResult:** Representa la hipótesis emitida por el motor de visión/identificación. Atributos: especie propuesta, grado cualitativo de certidumbre (*Alta / Moderada / Incierta*), lista de alternativas probables, metadatos de diagnóstico.
4. **Decision:** Representa el acto de ratificación del usuario. Atributos: elección de la especie validada, confirmación de alternativa o archivado como avistamiento pendiente.
5. **Capture:** El registro validado del avistamiento histórico en la libreta del usuario. Atributos: identificador único de captura, referencia al animal validado, referencia a la observación, notas de campo del usuario.
6. **Card:** El artefacto coleccionable resultante. Atributos: identificador de carta, referencia a la captura, acabado estético, marco visual, bloque científico, bloque de experiencia, bloque de lore lúdico.
7. **Collection:** El conjunto ordenado de cartas del usuario en su almacenamiento local.

---

## 2. Ciclo de Vida y Transición de Estados

```text
[ OBSERVATION ]
       │
       │ (Procesamiento por motor de identificación)
       ▼
[ IDENTIFICATION RESULT ]
       │
       │ (Interacción y validación del explorador)
       ▼
[ DECISION ]
       │
       │ (Ratificación y consagración del encuentro)
       ▼
[ CAPTURE ]
       │
       │ (Materialización estética)
       ▼
[ CARD ]
       │
       │ (Almacenamiento persistente)
       ▼
[ COLLECTION ]
```

---

## 3. Manejo de Estados Especiales e Incertidumbre

* **Identificación Plena (Alta confianza):** Se muestra la especie primaria con claridad, permitiendo al usuario avanzar al descubrimiento de inmediato.
* **Identificación Difusa (Confianza moderada / Múltiples candidatos):** Se muestra la especie más probable junto a una o dos alternativas coherentes para que el usuario elija según lo observado en vivo.
* **Identificación No Concluyente (Baja confianza):** Se explica con serenidad la imposibilidad de certificar la especie (foto lejana, iluminación insuficiente). Se ofrece guardar la captura como *"Avistamiento No Catalogado"* o reintentar el disparo.
* **No Animal:** Se informa cordialmente que no se reconocen animales en la escena y se invita a una nueva observación.
