# DESIGN TOKENS — Sistema de Tokens de Diseño Preliminares

> **WHO Animal v1**  
> `STATUS: FOUNDATION / DRAFT`

Este documento formaliza la especificación preliminar de tokens de diseño para Who Animal v1, estableciendo la coherencia visual antes de cualquier implementación en código.

---

## 1. Paleta Cromática Preliminar

La paleta se inspira en elementos botánicos, geológicos y atmosféricos del mundo real. Queda explícitamente descartado el naranja como color dominante.

```text
┌────────────┬─────────────┬─────────────┬──────────────────────────────────────────┐
│ Token      │ Hex Aprox.  │ HSL         │ Rol Conceptual                           │
├────────────┼─────────────┼─────────────┼──────────────────────────────────────────┤
│ Mist       │ #EBF0ED     │ 140°, 12%, 93%│ Claridad matutina, atmósfera de fondo    │
│ Sage       │ #8EA89D     │ 156°, 14%, 61%│ Verdes salvia, acentos suaves, equilibrio│
│ Forest     │ #233D34     │ 162°, 27%, 19%│ Verde profundo, anclaje visual, CTA hero │
│ Deep Teal  │ #1A4240     │ 176°, 43%, 18%│ Profundidad acuática, enfoque, elegancia │
│ Stone      │ #7B857F     │ 146°, 4%, 50% │ Grises minerales cálidos, líneas, bordes │
│ Cream      │ #F7F5F0     │ 45°, 24%, 96% │ Cartulina de campo, láminas, superficie  │
└────────────┴─────────────┴─────────────┴──────────────────────────────────────────┘
```

---

## 2. Asignaciones Semánticas

### Modo Diurno Estándar (Daylight Natural):
* `color.background.base`: **Mist** (`#EBF0ED`) — Suelo ambiental del campamento base.
* `color.surface.card`: **Cream** (`#F7F5F0`) — Cartulina mate de las cartas y paneles de lectura.
* `color.surface.elevated`: **#FFFFFF** con matiz cálido — Láminas destacadas o visores activos.
* `color.brand.primary`: **Forest** (`#233D34`) — Botón principal de descubrimiento (**DESCUBRIR**).
* `color.brand.secondary`: **Sage** (`#8EA89D`) — Acentos, chips taxonómicos y divisores orgánicos.
* `color.brand.accent`: **Deep Teal** (`#1A4240`) — Estados de selección activa y títulos heráldicos.
* `color.border.subtle`: **Stone** al 25% de opacidad — Filetes de grabado de 1dp.
* `color.text.primary`: **Forest** oscurecido (`#14221D`) — Máxima legibilidad, sin recurrir al negro puro artificial.
* `color.text.secondary`: **Stone** (`#555E59`) — Nombres científicos, metadatos y fechas.
* `color.text.inverse`: **Cream** (`#F7F5F0`) — Tipografía sobre el botón primario de bosque.
* `color.feedback.caution`: Tono ámbar terroso apagado (`#B8860B` / ocre mineral) — Avisos de precaución responsables, nunca estridentes.

---

## 3. Adaptaciones Atmosféricas de la Home (Modos de Luz)

La atmósfera de la Home adapta sutilmente el tono del `TECHO` según el ciclo circadiano:
* **Amanecer (Dawn):** Mist con veladura rosácea y dorada sutil (`#F2EBE5`).
* **Mediodía (Day):** Mist luminoso y despejado (`#EBF0ED`).
* **Atardecer (Dusk):** Ocre suave y salvia cálido (`#EADFD4`).
* **Noche (Night):** Azul pizarra profundo y verde noche (`#131B19`), preservando la calidez en las superficies de lectura Cream atenuadas, **sin recurrir a negros OLED puros ni neones**.

---

## 4. Sistema Tipográfico

El sistema tipográfico equilibra el rigor de la clasificación zoológica con la belleza de las láminas de naturalista:

### Familias Tipográficas Conceptuales:
1. **Display / Titulares Naturalistas (Editorial Serif):**
   * Estilo: Serif clásica refinada o Humanist Serif de alta legibilidad (evocando grabados de botánica).
   * Uso: Nombre de la aplicación, títulos de especies, pregunta central del día (*"¿Qué encontrarás hoy?"*).
2. **Cuerpo y Taxonomía (Clean Modern Sans-Serif):**
   * Estilo: Geométrica suave o grotesca humanista con excelente espaciado de caracteres.
   * Uso: Nombres binomiales científicos (siempre en cursiva), datos taxonómicos, descripciones biológicas.
3. **Notas de Campo y Lore (Editorial Italic):**
   * Estilo: Cursiva editorial serena o estilo plumilla caligráfica sutil.
   * Uso: Notas personales del explorador y relato narrativo del Lore lúdico.

### Escala Tipográfica Preliminar:
* `font.size.display`: 32sp / Line height: 40sp / Tracking: -0.5sp (Titulares hero).
* `font.size.headline`: 24sp / Line height: 32sp / Tracking: 0sp (Nombres de especies en Card).
* `font.size.title`: 18sp / Line height: 26sp / Tracking: 0.15sp (Subsecciones, familias taxonómicas).
* `font.size.body`: 14sp / Line height: 22sp / Tracking: 0.25sp (Fichas biológicas, textos educativos).
* `font.size.label`: 12sp / Line height: 16sp / Tracking: 0.5sp (Chips, botones secundarios, sellos).
* `font.size.caption`: 11sp / Line height: 14sp / Tracking: 0.4sp (Metadatos de fecha y créditos).

---

## 5. Espaciado y Cuadrícula

* **Unidad Base:** Sistema de 4dp / 8dp.
* `spacing.xxs`: 4dp
* `spacing.xs`: 8dp
* `spacing.sm`: 12dp
* `spacing.md`: 16dp (Margen estándar de pantalla y espaciado de componentes).
* `spacing.lg`: 24dp (Separación entre bloques conceptuales).
* `spacing.xl`: 32dp (Respiración amplia de secciones en el Campamento Base).
* `spacing.xxl`: 48dp (Aislamiento de la acción central **DESCUBRIR**).

---

## 6. Elevaciones y Sombras Orgánicas

* `elevation.flat`: 0dp — Contenedores integrados en la atmósfera.
* `elevation.card`: 2dp — Sombra suave y difusa (Offset Y: 3dp, Blur: 8dp, Color: `#233D34` al 8% de opacidad).
* `elevation.floating`: 6dp — Botón hero de descubrimiento y carta en modo inspección táctil (Offset Y: 6dp, Blur: 16dp, Color: `#233D34` al 12% de opacidad).

---

## 7. Formas y Bordes (Shapes)

* `shape.card`: Radio de 16dp — Cartulina con esquinas amables y sensación de corte físico.
* `shape.pill`: Radio de 999dp — Botón hero **DESCUBRIR** y chips taxonómicos.
* `shape.sheet`: Radio superior de 24dp — Mochila desplegable y paneles modales.
* `border.width.hairline`: 1dp — Filete sutil de delimitación estilo grabado.
