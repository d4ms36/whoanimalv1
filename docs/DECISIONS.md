# DECISIONS — Registro de Decisiones de Arquitectura y Producto (ADR)

> **WHO Animal v1**  
> `STATUS: FOUNDATION / DRAFT`

Este documento registra formalmente las decisiones de arquitectura, producto y experiencia de Who Animal v1.

---

## DEC-V1-001: Independencia del Proyecto
* **Estado:** APPROVED (Foundation)
* **Contexto:** Existía un prototipo previo (`whoanimal`) con código experimental y deuda técnica acumulada.
* **Decisión:** Who Animal v1 es un proyecto completamente nuevo e independiente, alojado en su propio repositorio `whoanimalv1`.

---

## DEC-V1-002: Prototipo Anterior como Fuente de Conocimiento
* **Estado:** APPROVED (Foundation)
* **Contexto:** Se requiere aprovechar los aciertos conceptuales sin arrastrar dependencias ni errores de código del pasado.
* **Decisión:** El prototipo anterior se utiliza exclusivamente como fuente de conocimiento, lecciones aprendidas y decisiones validadas; queda terminantemente prohibido copiar código fuente o assets directamente.

---

## DEC-V1-003: Experiencia Central (Core Loop)
* **Estado:** APPROVED (Foundation)
* **Contexto:** El producto debe tener un bucle principal de interacción claro y emocionante.
* **Decisión:** La experiencia central se define formalmente como:
  ```text
  EXPLORAR → ENCONTRAR → IDENTIFICAR → CONSEGUIR → COLECCIONAR
  ```

---

## DEC-V1-004: Home como Expedición
* **Estado:** APPROVED (Foundation)
* **Contexto:** Muchos productos caen en la trampa de parecer paneles de control o dashboards bancarios.
* **Decisión:** La Home de Who Animal v1 se conceptualiza como el campamento base de una expedición natural al aire libre y no como un dashboard administrativo.

---

## DEC-V1-005: Metáfora Espacial de la Home
* **Estado:** APPROVED (Foundation)
* **Contexto:** Es necesario ordenar los elementos de la Home con coherencia emocional.
* **Decisión:** Se adopta la metáfora TECHO / PECHO / ABDOMEN / PELVIS / PIES como guía conceptual de jerarquía visual (atmósfera, corazón de descubrimiento, hallazgos recientes, mochila y herramientas secundarias). No debe interpretarse de manera literal como una figura anatómica humana en la UI.

---

## DEC-V1-006: Descarte del Naranja como Color Dominante
* **Estado:** APPROVED (Foundation)
* **Contexto:** La dirección visual busca evocar calma, naturaleza y exploración serena.
* **Decisión:** El color naranja queda explícitamente descartado como color dominante en la dirección cromática principal. Se adoptan tonos inspirados en Mist, Sage, Deep Teal, Forest, Stone y Cream.

---

## DEC-V1-007: RPG Ligero sin Saturación de HUD
* **Estado:** APPROVED (Foundation)
* **Contexto:** Los elementos de gamificación deben aportar valor emocional sin generar estrés cognitivo.
* **Decisión:** El componente RPG será ligero y residirá en el valor de la recompensa y en la belleza de la Card coleccionable, no en la saturación de barras de HP/MP, monedas o estadísticas en la Home.

---

## DEC-V1-008: Desacoplamiento Ontológico de Dominio
* **Estado:** PROPOSED (WHO-V1-001)
* **Contexto:** El prototipo anterior sufrió confusiones al mezclar la observación física, la hipótesis de IA y la carta resultante en modelos sobrecargados.
* **Decisión:** Se adopta la ontología formal `Observation ≠ IdentificationResult ≠ Decision ≠ Capture ≠ Card` y `Animal ≠ Capture ≠ Card`. Cada concepto modela una entidad y fase distinta del ciclo de vida.
* **Impacto:** Claridad absoluta en el modelado de datos en Kotlin y desacoplamiento limpio entre cámara, visión por computadora y colección.

---

## DEC-V1-009: Principio de Falibilidad y Honestidad de la Identificación
* **Estado:** PROPOSED (WHO-V1-001)
* **Contexto:** Los modelos de visión artificial no son 100% infalibles en condiciones cambiantes de luz y campo. Presentar una inferencia incierta como dogma frustra al usuario y desinforma.
* **Decisión:** La aplicación asume formalmente el principio *"La aplicación puede equivocarse"*. La identificación se presenta como una hipótesis asistida con grados cualitativos de confianza y alternativas, permitiendo registrar avistamientos no catalogados.
* **Impacto:** Confianza duradera del usuario y rigor educativo en el producto.

---

## DEC-V1-010: Separación Estricta de los Tres Pilares en la Card
* **Estado:** PROPOSED (WHO-V1-001)
* **Contexto:** Añadir fantasía o narrativa lúdica puede confundir al usuario sobre la verdadera biología de la fauna.
* **Decisión:** Cada carta debe mantener contenedores estrictamente delimitados para:
  1. Información Científica (Factual).
  2. Experiencia del Encuentro (Personal).
  3. Lore Lúdico (Narrativa de ficción, obligatoriamente señalizada con advertencia visible).
* **Impacto:** Armonía entre rigor educativo y fascinación lúdica sin desinformar.

---

## DEC-V1-011: Arquitectura Local-First para el MVP
* **Estado:** PROPOSED (WHO-V1-001)
* **Contexto:** Obligar a crear cuentas, autenticarse y depender de un backend complejo en la primera versión retrasa la validación del bucle central y genera fricción de entrada.
* **Decisión:** El MVP será estrictamente Local-First. Las observaciones, capturas y cartas se almacenarán y gestionarán de forma local en el dispositivo del usuario.
* **Impacto:** Experiencia inmediata sin fricción de login y simplificación radical de la arquitectura inicial.

---

## DEC-V1-012: Adopción del Filtro "The Who Animal Test"
* **Estado:** PROPOSED (WHO-V1-001)
* **Contexto:** Es común en aplicaciones móviles acumular funciones secundarias (tiendas, monedas, rankings) que diluyen la propuesta de valor.
* **Decisión:** Toda funcionalidad futura debe superar el decálogo de control *The Who Animal Test* para ser autorizada e implementada.
* **Impacto:** Blindaje del producto contra el feature-creep y mantenimiento de la pureza de la experiencia.

---

## DEC-V1-013: Protocolo de Privacidad Geográfica en Especies Vulnerables
* **Estado:** PROPOSED (WHO-V1-001)
* **Contexto:** Publicar o almacenar ubicaciones exactas de especies en peligro de extinción puede facilitar la caza furtiva o el acoso de hábitats sensibles.
* **Decisión:** En cumplimiento del principio 100% Pet Friendly, cualquier especie categorizada como vulnerable o amenazada en la Lista Roja de la UICN omitirá coordenadas GPS de alta resolución en sus metadatos y exportaciones.
* **Impacto:** Protección efectiva de la fauna silvestre y coherencia ética total del producto.

---

## DEC-V1-014: Identidad Visual del Explorador Naturalista y Campamento Base
* **Estado:** PROPOSED (WHO-V1-002)
* **Contexto:** Se requiere definir el tono visual e identitario del producto para evitar interfaces genéricas o de videojuego arcade.
* **Decisión:** Who Animal v1 adopta la identidad visual del "Explorador Naturalista", conceptualizando la Home como el "Campamento base de un explorador de fauna" bajo la jerarquía espacial TECHO, PECHO, ABDOMEN, PELVIS y PIES.
* **Impacto:** Establece una experiencia táctil, serena y de alta gama, descartando definitivamente dashboards corporativos o interfaces de combate.

---

## DEC-V1-015: Tokens Preliminares de Color y Tipografía Editorial
* **Estado:** PROPOSED (WHO-V1-002)
* **Contexto:** El diseño necesita una paleta cromática armónica y un sistema tipográfico que equilibre rigor zoológico y belleza artística.
* **Decisión:** Se formaliza la paleta preliminar: Mist (#EBF0ED), Sage (#8EA89D), Forest (#233D34), Deep Teal (#1A4240), Stone (#7B857F) y Cream (#F7F5F0), combinada con una jerarquía tipográfica que incluye Serif editorial para display y titulares, Sans-serif limpia para taxonomía y Editorial Italic para notas de campo.
* **Impacto:** Coherencia estética en todas las pantallas y rechazo explícito del naranja dominante.

---

## DEC-V1-016: Dirección Artística de la Card como Lámina de Naturalista
* **Estado:** PROPOSED (WHO-V1-002)
* **Contexto:** La carta debe sentirse como una pieza de colección personal y no como un componente genérico de software.
* **Decisión:** La Card se diseña en proporción 5:7 evocando una lámina de naturalista en cartulina mate de 300 g/m², encuadrando la foto real del usuario y delimitando con pulcritud los tres pilares (Científico, Experiencia y Lore señalizado).
* **Impacto:** Máximo valor emocional de la recompensa sin recurrir a neones, efectos gacha ni saturación visual.

---

## DEC-V1-017: Adopción del Sistema de Diseño Oficial "Cuaderno de Explorador Moderno"
* **Estado:** PROPOSED (WHO-V1-002)
* **Contexto:** Se requiere estructurar formalmente la dirección visual del producto en especificaciones modulares para guiar el futuro desarrollo UI.
* **Decisión:** Se adopta formalmente la suite documental `docs/design/` como el sistema de diseño oficial de Who Animal v1, bajo la premisa creativa de "Un cuaderno de explorador moderno que cobra vida".
* **Impacto:** Establece las directrices vinculantes para componentes, color, tipografía y movimiento.

---

## DEC-V1-018: Catálogo Oficial de Componentes Táctiles y Físicas de Resorte
* **Estado:** PROPOSED (WHO-V1-002)
* **Contexto:** La interfaz debe sentirse física, táctil y orgánica al tacto, evitando componentes genéricos o comportamientos rígidos.
* **Decisión:** Se especifican formalmente los componentes `HeroDiscoverButton`, `NaturalistCard`, `FieldViewfinder`, `TaxonomyChip`, `JournalSnippet`, `BackpackTrigger` y `ObjectiveCautionBadge`, regidos por físicas de resorte (Spring Physics) sin rebotes excesivos.
* **Impacto:** Experiencia de usuario consistente, serena y de alta fidelidad táctil.

---

## DEC-V1-019: Coreografía de Tres Tiempos en la Revelación de Especies
* **Estado:** PROPOSED (WHO-V1-002)
* **Contexto:** El descubrimiento es el momento cumbre emocional del producto y no debe vulgarizarse con animaciones de casino ni aparecer de golpe.
* **Decisión:** La revelación se coreografía en tres tiempos sucesivos: 1. Pausa de enfoque (200ms), 2. Revelación de identidad zoológica (300ms) y 3. Materialización táctil de la Card (350ms). Se prohíben ruletas, confeti y cofres temblorosos.
* **Impacto:** Respeto al ritmo naturalista y solemnidad en el momento del hallazgo.

---

## DEC-V1-020: Clean Architecture Foundation
* **Estado:** PROPOSED (WHO-V1-003)
* **Contexto:** Se requiere una arquitectura modular y desacoplada que evite la deuda técnica del prototipo histórico y permita evolucionar el producto de forma sostenible.
* **Decisión:** Who Animal v1 adopta formalmente Clean Architecture con estricta inversión de dependencias: Presentation y Data dependen de Domain; Domain no depende de ninguna capa externa.
* **Impacto:** Código desacoplado, alta testabilidad de la lógica zoológica y protección contra cambios de frameworks.

---

## DEC-V1-021: Dominio Independiente de Android
* **Estado:** PROPOSED (WHO-V1-003)
* **Contexto:** El núcleo biológico y las reglas de las cartas deben ser puras y no verse contaminadas por el ciclo de vida de Android o librerías de UI.
* **Decisión:** El módulo `:core:domain` será Kotlin puro (JVM / KMP ready) con cero dependencias de `android.*`, `androidx.*`, Compose o Room. Toda integración de infraestructura se realiza mediante interfaces e inversión de control.
* **Impacto:** Posibilidad de ejecutar pruebas unitarias ultrarrápidas sin emulador y preparación para un futuro multiplataforma.

---

## DEC-V1-022: Fundación de Producto Local-First
* **Estado:** PROPOSED (WHO-V1-003)
* **Contexto:** El acto de explorar la naturaleza ocurre con frecuencia en zonas con cobertura móvil deficiente (bosques, parques, senderos). Obligar a conectividad permanente arruina la experiencia de campo.
* **Decisión:** El almacenamiento y la experiencia de Who Animal v1 son estrictamente Local-First. Las observaciones, capturas y la mochila de cartas se persisten en el almacenamiento local del dispositivo como fuente única de verdad.
* **Impacto:** Funcionamiento sin fisuras fuera de línea, inmediatez de respuesta y cero fricción de autenticación en el MVP.

---

## DEC-V1-023: Flujo de Datos Controlado e Irreversible
* **Estado:** PROPOSED (WHO-V1-003)
* **Contexto:** En el prototipo previo se producían estados inconsistentes al intentar generar cartas sin capturas válidas o sin decisiones explícitas.
* **Decisión:** El ciclo de descubrimiento opera como un pipeline estricto de avance unidireccional: Observation → IdentificationResult → IdentificationDecision → Capture → Card → Collection. Ninguna entidad posterior puede crearse sin la existencia y validación de la anterior.
* **Impacto:** Integridad transaccional absoluta en el ciclo de vida del descubrimiento y prevención de estados corruptos.
