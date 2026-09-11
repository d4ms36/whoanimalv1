# DECISIONS — Registro de Decisiones de Arquitectura y Producto (ADR)

> **WHO Animal v1**  
> `STATUS: FOUNDATION / DRAFT`

Este documento registra formalmente las decisiones fundacionales de Who Animal v1.

---

## DEC-V1-001: Independencia del Proyecto
* **Estado:** APROBADA (Foundation)
* **Contexto:** Existía un prototipo previo (`whoanimal`) con código experimental y deuda técnica acumulada.
* **Decisión:** Who Animal v1 es un proyecto completamente nuevo e independiente, alojado en su propio repositorio `whoanimalv1`.

---

## DEC-V1-002: Prototipo Anterior como Fuente de Conocimiento
* **Estado:** APROBADA (Foundation)
* **Contexto:** Se requiere aprovechar los aciertos conceptuales sin arrastrar dependencias ni errores de código del pasado.
* **Decisión:** El prototipo anterior se utiliza exclusivamente como fuente de conocimiento, lecciones aprendidas y decisiones validadas; queda terminantemente prohibido copiar código fuente o assets directamente.

---

## DEC-V1-003: Experiencia Central (Core Loop)
* **Estado:** APROBADA (Foundation)
* **Contexto:** El producto debe tener un bucle principal de interacción claro y emocionante.
* **Decisión:** La experiencia central se define formalmente como:
  ```text
  EXPLORAR → ENCONTRAR → IDENTIFICAR → CONSEGUIR → COLECCIONAR
  ```

---

## DEC-V1-004: Home como Expedición
* **Estado:** APROBADA (Foundation)
* **Contexto:** Muchos productos caen en la trampa de parecer paneles de control o dashboards bancarios.
* **Decisión:** La Home de Who Animal v1 se conceptualiza como el campamento base de una expedición natural al aire libre y no como un dashboard administrativo.

---

## DEC-V1-005: Metáfora Espacial de la Home
* **Estado:** APROBADA (Foundation)
* **Contexto:** Es necesario ordenar los elementos de la Home con coherencia emocional.
* **Decisión:** Se adopta la metáfora TECHO / PECHO / ABDOMEN / PELVIS / PIES como guía conceptual de jerarquía visual (atmósfera, corazón de descubrimiento, hallazgos recientes, mochila y herramientas secundarias). No debe interpretarse de manera literal como una figura anatómica humana en la UI.

---

## DEC-V1-006: Descarte del Naranja como Color Dominante
* **Estado:** APROBADA (Foundation)
* **Contexto:** La dirección visual busca evocar calma, naturaleza y exploración serena.
* **Decisión:** El color naranja queda explícitamente descartado como color dominante en la dirección cromática principal. Se adoptan tonos inspirados en Mist, Sage, Deep Teal, Forest, Stone y Cream.

---

## DEC-V1-007: RPG Ligero sin Saturación de HUD
* **Estado:** APROBADA (Foundation)
* **Contexto:** Los elementos de gamificación deben aportar valor emocional sin generar estrés cognitivo.
* **Decisión:** El componente RPG será ligero y residirá en el valor de la recompensa y en la belleza de la Card coleccionable, no en la saturación de barras de HP/MP, monedas o estadísticas en la Home.
