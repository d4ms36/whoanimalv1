# NAVIGATION — El MVP Journey y Mapa de Navegación

> **WHO Animal v1**  
> `STATUS: FOUNDATION / DRAFT`

---

## 1. El MVP Journey Formalizado

La experiencia se despliega en un recorrido continuo de 6 etapas caracterizadas en 8 dimensiones:

| Dimensión | 1. HOME / EXPLORER | 2. CAPTURE | 3. IDENTIFICATION | 4. DISCOVERY | 5. CARD | 6. COLLECTION |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Objetivo Usuario** | Sentir curiosidad y predisposición a explorar la fauna. | Encuadrar y capturar fotográficamente un animal avistado. | Conocer qué criatura se encuentra frente a él. | Vivir la emoción y revelación del hallazgo. | Atesorar y contemplar su nueva pieza coleccionable. | Revisar y disfrutar su catálogo acumulado de criaturas. |
| **Objetivo Producto** | Establecer un campamento base sereno que incite al descubrimiento sin fricción. | Proveer un visor de cámara limpio, rápido y 100% respetuoso con el animal. | Presentar hipótesis taxonómicas honestas comunicando certidumbre cualitativa. | Crear un momento cumbre memorable conectando la observación con la pieza. | Materializar el encuentro en un artefacto con identidad y tres pilares claros. | Generar orgullo de explorador mediante una galería táctil no administrativa. |
| **Entrada** | Apertura de la aplicación o regreso de otra sección. | Pulsación de **DESCUBRIR** desde la Home. | Disparo fotográfico confirmado en Capture. | Resolución positiva de la identificación o elección de especie. | Pulsación en *"Consagrar Carta"* o avance desde Discovery. | Pulsación en *"Mochila"* desde Home o cierre de Card. |
| **Salida** | Transición al visor de campo o a la mochila. | Envío de la `Observation` a análisis o cancelación de regreso. | Emisión de `IdentificationResult` hacia Discovery o aviso de reintento. | Transición hacia el renderizado de la `Card`. | Guardado automático en Colección y regreso a Home o Mochila. | Retorno a Home o inspección individual de carta. |
| **Emoción** | Curiosidad serena, anticipación de expedición. | Atención concentrada, sigilo, respeto por el animal. | Intriga contenida, expectativa de aprendizaje. | ¡Asombro, alegría del hallazgo, revelación (*"¡Lo encontré!"*). | Pertenencia, fascinación táctil (*"Es mi pieza única"*). | Nostalgia grata, orgullo de coleccionista naturalista. |
| **Información Principal** | Atmósfera del momento, pregunta del día, acceso a colección. | Imagen en vivo del entorno, avisos de distancia responsable. | Imagen capturada, estado de análisis, hipótesis zoológica sugerida. | Nombre común, nombre científico, imagen del animal validada. | Arte del encuentro, datos científicos, notas de campo y lore. | Cuadrícula o bitácora de cartas descubiertas, total de hallazgos. |
| **Acción Primaria** | Pulsar **DESCUBRIR**. | Pulsar **DISPARADOR**. | Confirmar identificación sugerida o seleccionar alternativa. | Pulsar **CREAR / VER CARTA**. | Inspeccionar los 3 pilares o guardar en mochila. | Seleccionar una carta para inspección detallada. |
| **Condición de Éxito** | El usuario sabe de inmediato cómo empezar a descubrir sin perderse en menús. | La toma se realiza sin lag, sin estridencias y con encuadre nítido. | El resultado se comunica con transparencia sin simular infalibilidad. | El usuario siente satisfacción genuina por haber reconocido la especie. | La carta se percibe como un objeto de valor coleccionable personal. | La colección transmite sensación de avance y vivencia de expedición. |

---

## 2. Diagrama de Transiciones de Navegación

```text
               ┌──────────────────────┐
               │    HOME / EXPLORER   │◄───────────────────────┐
               │  (Campamento Base)   │                        │
               └──────────┬───────────┘                        │
                          │                                    │
           [DESCUBRIR]    │    [MOCHILA]                       │
        ┌─────────────────┴─────────────────┐                  │
        ▼                                   ▼                  │
┌───────────────┐                  ┌─────────────────┐         │
│    CAPTURE    │                  │   COLLECTION    │         │
│(Visor Campo)  │                  │(Mochila Cartas) │         │
└───────┬───────┘                  └────────┬────────┘         │
        │ [DISPARAR]                        │                  │
        ▼                                   │ [SELECCIONAR]    │
┌───────────────┐                           │                  │
│IDENTIFICATION │                           │                  │
│(Análisis/Rta) │                           │                  │
└───────┬───────┘                           │                  │
        │ [CONFIRMAR DECISIÓN]              │                  │
        ▼                                   │                  │
┌───────────────┐                           │                  │
│   DISCOVERY   │                           │                  │
│(Momento Clímax)                           │                  │
└───────┬───────┘                           │                  │
        │ [CONSAGRAR PIEZA]                 ▼                  │
        ▼                          ┌─────────────────┐         │
┌───────────────┐                  │   CARD DETAIL   │         │
│     CARD      │─────────────────►│ (Inspección de  │─────────┘
│ (Nueva Pieza) │                  │    3 Pilares)   │  [VOLVER A HOME]
└───────────────┘                  └─────────────────┘
```
