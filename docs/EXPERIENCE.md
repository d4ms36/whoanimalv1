# EXPERIENCE — Core Loop y Arquitectura Emocional

> **WHO Animal v1**  
> `STATUS: FOUNDATION / DRAFT`

---

## 1. El Core Loop Formalizado

```text
EXPLORAR → ENCONTRAR → IDENTIFICAR → CONSEGUIR → COLECCIONAR
```

### Significado Preciso de Cada Verbo:

* **EXPLORAR:**
  * **Qué hace el usuario:** Camina por su entorno cotidiano (calle, jardín, parque, campo), prestando atención a sonidos, movimientos y ramas. La app permanece en su bolsillo o en la mano como compañera silenciosa, lista para abrirse en un instante.
  * **Qué intenta conseguir:** Agudizar la mirada y conectar con indicios de vida.
  * **Qué significa en la app:** La Home actúa como campamento base sereno, transmitiendo la atmósfera del día e invitando a la acción con una única pregunta: *¿Qué encontrarás hoy?*.

* **ENCONTRAR:**
  * **Qué constituye un encuentro:** El usuario divisa un animal en el mundo real (un pájaro posado, una ardilla, un insecto curioso, un perro paseando).
  * **Información en este instante:** Únicamente la realidad biológica del momento: luz natural, distancia, encuadre óptico y silencio. Cero interferencia digital artificial.

* **IDENTIFICAR:**
  * **Qué significa:** Apuntar la cámara de forma respetuosa, encuadrar y formular una pregunta a la naturaleza: *¿Quién eres?*.
  * **Distinción ontológica crítica:**
    * `Observation`: La fotografía y el contexto ambiental capturados por el explorador.
    * `IdentificationResult`: La hipótesis zoológica emitida por el motor de identificación, acompañada de un nivel cualitativo de certeza y posibles alternativas.
    * `Decision`: El acto consciente y voluntario del explorador de ratificar la especie sugerida, escoger una alternativa o registrar el avistamiento como pendiente de confirmación.
  * **Filosofía de Honestidad:** "La aplicación puede equivocarse". Nunca presentar una inferencia como dogma infalible.

* **CONSEGUIR:**
  * **Qué significa realmente:** **No significa capturar físicamente al animal ni retirarlo de su hábitat.** Significa **consagrar el encuentro** en una pieza digital perenne. El explorador "consigue" la constancia testimonial de haber compartido el espacio-tiempo con esa criatura mediante una Card personalizada.

* **COLECCIONAR:**
  * **Qué significa tener una colección:** No es un inventario contable ni un CRUD frío. Es la **Mochila del Explorador**: una galería viva y táctil que narra la historia de las expediciones del usuario, permitiéndole contemplar sus cartas, aprender de sus fichas biológicas y maravillarse con la biodiversidad acumulada.

---

## 2. Arquitectura Emocional de la Home (Campamento Base)

La Home **NO es un dashboard de métricas** ni un panel de administración. Es el campamento base al aire libre desde el cual se contempla el día y se inicia la expedición.

### La Metáfora Conceptual:
```text
TECHO     (Atmósfera / Cielo / Entorno)
  ↓
PECHO     (Corazón / Visor / Acción DESCUBRIR)
  ↓
ABDOMEN   (Expedición / Hallazgos Recientes)
  ↓
PELVIS    (Mochila / Colección de Piezas)
  ↓
PIES      (Herramientas secundarias y ajustes)
```

> **Regla de oro:** Esta metáfora es exclusivamente una guía de jerarquía visual y emocional. No debe traducirse de forma literal en cinco rectángulos simétricos, ni en un dibujo anatómico humano.

### Desglose de Zonas:
1. **TECHO — Atmósfera:**
   * Transmite el clima, la hora del día (amanecer, día, atardecer, noche) y la serenidad del entorno exterior.
   * Elementos: Tono lumínico armónico, saludo naturalista sutil.
2. **PECHO — El Corazón del Descubrimiento:**
   * La zona visual principal de la pantalla.
   * Pregunta motivadora: *¿Qué encontrarás hoy?*.
   * Visor de campo estilizado y botón de acción primario indiscutible: **DESCUBRIR**.
3. **ABDOMEN — La Expedición Reciente:**
   * Espacio discreto para evocar los últimos avistamientos logrados.
   * Refuerza la continuidad de las salidas de campo.
4. **PELVIS — La Mochila del Explorador:**
   * Acceso táctil y directo a la Colección de cartas atesoradas.
   * Representa la solidez de lo ya descubierto.
5. **PIES — Herramientas Secundarias:**
   * Ajustes de la aplicación, guía de observación responsable y créditos.
   * Discretas en la base inferior, sin competir jamás con el botón de descubrir.

### Elementos Terminantemente Prohibidos en Home:
* Barras de vida (HP) o maná (MP).
* Indicadores de nivel de jugador numérico o barras de XP agresivas.
* Marcadores de monedas, gemas o saldo virtual.
* Medidores de energía o "stamina" con tiempos de recarga.
* Etiquetas de "Rarity" o porcentajes de probabilidad.
* Iconos de tienda, carritos de compra o enlaces promocionales.
* Tablas analíticas o widgets de estadísticas financieras/deportivas.

---

## 3. La Experiencia de Captura (Capture Experience)

* **Sensación:** Mirar a través de unos prismáticos o la lente de una cámara réflex de campo.
* **Interfaz:** Despejada, limpia y natural. Queda prohibida la estética de visor militar, HUD de ciencia ficción o cuadrícula de escaneo de laboratorio.
* **Acción Principal:** Un disparador táctil sereno y silencioso.
* **Feedback:** Una pulsación suave y elegante que congela la observación con respeto.
* **Seguridad y Respeto:** Mensajes educativos flotantes ocasionales y discretos (p. ej., *"Mantén una distancia segura y observa en silencio"*).

---

## 4. La Experiencia de Identificación (Identification Experience)

* **Transición:** Tras registrar la `Observation`, la aplicación procesa la imagen sin fingir misterios mágicos ni demoras teatrales innecesarias.
* **Presentación del Resultado (`IdentificationResult`):**
  * Presenta la especie más probable con lenguaje honesto: *"Parece ser un..."* o *"Coincidencia probable con..."*.
  * Muestra una alternativa si el grado de certidumbre no es absoluto.
  * **Manejo de Incertidumbre:** Si la foto está borrosa, lejana o no permite diagnóstico confiable, la aplicación lo declara con transparencia: *"No estamos seguros de qué animal se trata. ¿Deseas guardarlo como avistamiento por clasificar o probar otro encuadre?"*.
  * **Casos No Animales:** Si la imagen no contiene fauna identificable, ofrece feedback amigable sin penalizaciones: *"No detectamos ningún animal en esta toma. Recuerda enfocar con claridad la silueta del espécimen."*.

---

## 5. El Momento del Descubrimiento (Discovery Moment)

Es el **clímax emocional** de Who Animal. El instante en que la incógnita se transforma en revelación y la experiencia se convierte en objeto coleccionable.

### Secuencia de Revelación:
1. **Pausa de Anticipación:** Breve transición donde el visor enfoca el ejemplar.
2. **Revelación de Identidad:** Aparición clara del nombre común y nombre binomial (científico) de la criatura.
3. **Materialización de la Card:** La observación se encapsula en el marco de la carta, como una pieza que cobra vida táctil.
4. **Resonancia Emocional:** El usuario experimenta el *"¡Lo encontré!"* y contempla la recompensa visual de su esfuerzo de campo.

---

## 6. La Experiencia de la Colección (Explorer's Backpack)

* **Sensación:** Abrir un archivador de expediciones encuadernado en cuero o lona, donde cada carta descansa en su compartimento.
* **Organización Orgánica:** No es una lista desordenada. Se agrupa con elegancia por familias biológicas o por orden cronológico de encuentros.
* **Detalle Táctil:** Al pulsar una carta, esta se eleva suavemente, permitiendo girarla o inspeccionar sus tres pilares de contenido: Ciencia, Experiencia del Encuentro y Lore narrativo.
