# COLOR SYSTEM — Sistema Cromático Oficial

> **WHO Animal v1**  
> `STATUS: FOUNDATION / DRAFT`

---

## 1. Filosofía Cromática: Los Tonos del Entorno Vivo

El sistema de color de Who Animal v1 rinde tributo a la paleta del mundo natural sereno.  
**Se descarta formal y definitivamente el naranja como color dominante en la aplicación.**

---

## 2. La Paleta Nuclear Oficial

```text
┌────────────┬─────────────┬─────────────────┬──────────────────────────────────────────┐
│ Nombre     │ Hex         │ HSL             │ Evocación Natural                        │
├────────────┼─────────────┼─────────────────┼──────────────────────────────────────────┤
│ Mist       │ #EBF0ED     │ 140°, 12%, 93%  │ Bruma matutina, aire fresco, fondo base  │
│ Sage       │ #8EA89D     │ 156°, 14%, 61%  │ Verde salvia, follaje suave, equilibrio  │
│ Deep Teal  │ #1A4240     │ 176°, 43%, 18%  │ Profundidad de agua en bosque, elegancia │
│ Forest     │ #233D34     │ 162°, 27%, 19%  │ Corteza y pinar sombrío, botón hero CTA  │
│ Stone      │ #7B857F     │ 146°, 4%, 50%   │ Roca mineral cálida, filetes, metadatos  │
│ Cream      │ #F7F5F0     │ 45°, 24%, 96%   │ Cartulina de libreta de campo, láminas   │
└────────────┴─────────────┴─────────────────┴──────────────────────────────────────────┘
```

---

## 3. Mapeo Semántico de la Interfaz

### Superficies y Fondos:
* `surface.background.app`: **Mist** (`#EBF0ED`) — Fondo general del Campamento Base.
* `surface.card.base`: **Cream** (`#F7F5F0`) — Cartulina física de las cartas y libretas.
* `surface.container.subtle`: **Sage** al 15% sobre Cream — Fondos de chips taxonómicos y citas de campo.
* `surface.modal.sheet`: **Cream** cálido (`#FBF9F5`) — Hojas desplegables de la Mochila y filtros.

### Acciones y Énfasis:
* `action.primary.fill`: **Forest** (`#233D34`) — Botón hero de exploración (**DESCUBRIR**).
* `action.primary.text`: **Cream** (`#F7F5F0`) — Tipografía de alto contraste sobre el botón principal.
* `action.secondary.stroke`: **Sage** (`#8EA89D`) — Bordes de botones secundarios y selectores.
* `action.accent.focus`: **Deep Teal** (`#1A4240`) — Estados de selección activa y marcadores de posición.

### Tipografía y Legibilidad:
* `text.primary`: **Forest Oscuro** (`#14221D`) — Contraste óptimo (WCAG AAA) sobre fondos claros, sin la frialdad del negro puro (#000000).
* `text.secondary`: **Stone Oscuro** (`#4F5752`) — Nombres científicos, familias taxonómicas y fechas.
* `text.tertiary`: **Stone** (`#7B857F`) — Metadatos complementarios y marcas de agua sutiles.
* `text.link`: **Deep Teal** (`#1A4240`) — Enlaces informativos y acciones secundarias.

### Avisos de Campo Responsables (100% Pet Friendly):
* `feedback.caution.fill`: **Ocre Mineral Apagado** (`#B8860B` al 12% sobre Cream) — Avisos de distanciamiento o fauna defensiva.
* `feedback.caution.text`: **Ámbar Tierra Oscuro** (`#6B4E03`) — Texto educativo preventivo, sin alarmismo ni saturación agresiva.
* *Nota:* Queda prohibido el uso de rojo chillón o naranja fluorescente para avisos zoológicos.

---

## 4. Adaptaciones Atmosféricas Circadianas

El color del `TECHO` de la Home se adapta suavemente a la hora del día para reforzar la presencia exterior:

* **Amanecer (Dawn - 06:00 a 09:00):** Gradiente suave de Mist con veladura ocre y rosácea tenue (`#F3ECE6`).
* **Día Abierto (Day - 09:00 a 18:00):** Mist puro (`#EBF0ED`) con luminosidad clara y descansada.
* **Atardecer (Dusk - 18:00 a 21:00):** Salvia dorado cálido (`#ECE4D8`), evocando la hora dorada de campo.
* **Noche Serena (Night - 21:00 a 06:00):** Pizarra de bosque profundo (`#121917`), con superficies Cream atenuadas a gris piedra suave (`#E2DFD8`). **Sin negros OLED puros ni neones.**
