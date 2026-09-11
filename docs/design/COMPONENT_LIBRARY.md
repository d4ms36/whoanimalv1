# COMPONENT LIBRARY — Catálogo Conceptual de Componentes

> **WHO Animal v1**  
> `STATUS: FOUNDATION / DRAFT`

Este catálogo define las especificaciones conceptuales de los componentes visuales e interactivos de Who Animal v1, garantizando que su tactilidad, ergonomía y fidelidad estética respondan al concepto de "cuaderno de explorador moderno".

---

## 1. Botón Hero de Acción Primaria: `[ DESCUBRIR ]`

* **Rol:** El catalizador central de la experiencia en la Home (PECHO).
* **Geometría:** Píldora orgánica de altura 56dp, radio 999dp.
* **Superficie y Color:** Relleno sólido en **Forest** (`#233D34`) con tipografía en **Cream** (`#F7F5F0`).
* **Elevación:** Sombra difusa suave (4dp en reposo, 2dp al pulsar).
* **Iconografía:** Lupa de naturalista o visor óptico de trazo fino (1.5dp) a la izquierda del texto.
* **Micro-interacción:** Respuesta háptica seca y reducción de escala elástica al 97% mediante resorte amortiguado.

---

## 2. La Carta de Naturalista: `NaturalistCard`

* **Rol:** El artefacto coleccionable central del producto.
* **Geometría y Proporción:** Relación de aspecto 5:7 (anchura/altura clásica de lámina botánica), esquinas redondeadas con radio de 16dp.
* **Materialidad:** Cartulina en tono **Cream** (`#F7F5F0`) con filete perimetral de 1dp en **Stone** (`#7B857F`) al 30% de opacidad.
* **Estructura Frontal:**
  1. **Encuadre Fotográfico:** Ventana con margen passe-partout de 8dp que encuadra la foto real del usuario.
  2. **Bloque de Especie:** Nombre Común destacado y Nombre Científico binomial en cursiva (*Genus species*).
  3. **Insignia de Familia:** Chip sutil en tono Sage con el orden o familia zoológica.
  4. **Metadatos de Campo:** Fecha, hora y entorno del avistamiento.
* **Estructura del Reverso / Detalle:**
  1. **Ficha Científica:** Datos taxonómicos oficiales y estado de conservación de la UICN.
  2. **Experiencia del Encuentro:** Notas de campo personales del explorador.
  3. **Contenedor de Lore:** Bloque con marco punteado y advertencia visible obligatoria de ficción narrativa.

---

## 3. Visor de Campo: `FieldViewfinder`

* **Rol:** Interfaz visual de la pantalla de captura.
* **Filosofía:** Ventana silenciosa a la naturaleza, no una mira táctica ni escáner militar.
* **Geometría:** Marco rectangular amplio con cuatro esquinas suaves redondeadas que delimitan el área de observación.
* **Trazos:** Línea sutil de 1dp en color Cream con ligera translucidez (60%).
* **Prohibido:** Retículas centrales de mira telescópica, coordenadas numéricas parpadeantes, lecturas láser o rejillas cibernéticas.

---

## 4. Chip Taxonómico: `TaxonomyChip`

* **Rol:** Clasificación biológica concisa (Aves, Mamíferos, Insectos, Familia).
* **Geometría:** Altura 28dp, radio 999dp, padding horizontal 12dp.
* **Estilo:** Fondo en **Sage** (`#8EA89D`) al 20% sobre Cream, con tipografía en Forest Oscuro (`#14221D`) de tamaño Caption (12sp).

---

## 5. Ficha de Bitácora Reciente: `JournalSnippet`

* **Rol:** Módulo de avistamientos recientes en el estrato ABDOMEN de la Home.
* **Geometría:** Tarjeta horizontal compacta (altura 72dp) con radio de 12dp.
* **Contenido:** Miniatura cuadrada con bordes redondeados de la foto del usuario, nombre común y fecha relativa (*"Hace 2 horas"*).
* **Tacto:** Superficie Cream integrada de forma fluida en la atmósfera del campamento base.

---

## 6. Disparador de la Mochila: `BackpackTrigger`

* **Rol:** Acceso táctil a la Colección en el estrato PELVIS.
* **Estilo:** Tarjeta contenedora que evoca la solapa de cuero o lona de una cartera de expedición, con contador discreto de cartas descubiertas.

---

## 7. Sello Preventivo Objetivo: `ObjectiveCautionBadge`

* **Rol:** Advertencia preventiva y educativa para especies con aguijón o defensas naturales.
* **Geometría:** Contenedor estilizado con icono de precaución sobrio.
* **Color:** Fondo en Ocre Mineral apagado (`#B8860B` al 12%) y texto en Ámbar Tierra (`#6B4E03`).
* **Tono:** Educativo, responsable y sereno; jamás alarmista ni demonizador de la fauna.
