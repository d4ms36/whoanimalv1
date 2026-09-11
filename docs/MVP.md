# MVP — Alcance del Producto Mínimo Viable

> **WHO Animal v1**  
> `STATUS: FOUNDATION / DRAFT`

---

## 1. Propósito del MVP

El MVP de Who Animal v1 tiene una única misión innegociable:  
**Demostrar que el bucle fundamental de explorar el mundo real, identificar fauna con honestidad y atesorar cartas con identidad táctil produce asombro, gratitud y orgullo de colección.**

Cualquier funcionalidad que no contribuya directamente a validar este bucle queda excluida de la versión inicial.

---

## 2. Clasificación MoSCoW del MVP

```text
┌─────────────────────────────────────────────────────────┐
│                       ALCANCE MVP                       │
├──────────────────────────┬──────────────────────────────┤
│ 1. MUST HAVE (Crítico)   │ Indispensable para que sea   │
│                          │ Who Animal v1.               │
├──────────────────────────┼──────────────────────────────┤
│ 2. SHOULD HAVE           │ Importante pero no bloquea   │
│                          │ el primer lanzamiento.       │
├──────────────────────────┼──────────────────────────────┤
│ 3. COULD HAVE            │ Ideas valiosas si sobra      │
│                          │ capacidad de desarrollo.     │
├──────────────────────────┼──────────────────────────────┤
│ 4. FUTURE (Excluido)     │ Estrictamente fuera del MVP. │
└──────────────────────────┴──────────────────────────────┘
```

---

### 1. MUST HAVE (Indispensable para el MVP)

* **Campamento Base (Home / Explorer):**
  * Pantalla inicial serena y natural con jerarquía Techo/Pecho/Abdomen/Pelvis/Pies.
  * Botón principal e indiscutible: **DESCUBRIR**.
  * Acceso directo a la Mochila / Colección.
  * Cero HUDs de videojuego, cero barras de salud y cero economía virtual.
* **Visor de Captura de Campo:**
  * Vista de cámara limpia, sin elementos técnicos intrusivos.
  * Disparador táctil de respuesta inmediata.
* **Motor de Identificación con Manejo de Incertidumbre:**
  * Distinción clara entre `Observation`, `IdentificationResult` y `Decision`.
  * Grado de confianza cualitativo (p. ej., Alta coincidencia / Por confirmar).
  * Manejo digno y transparente de fallos y alternativas.
* **Momento de Descubrimiento:**
  * Revelación emocionante y pausada del animal identificado.
  * Conexión fluida con la generación de la carta.
* **Card Coleccionable con Identidad Propia:**
  * Renderizado táctil y estético de la pieza obtenida.
  * Separación estricta de tres pilares:
    1. Datos Científicos reales (Nombre común, nombre científico, hábitat general, dieta, estado de conservación).
    2. Datos de Experiencia (Foto del usuario, fecha, momento de avistamiento, notas de campo).
    3. Lore Narrativo en contenedor propio con advertencia explícita de ficción lúdica.
* **Mochila del Explorador (Colección Básica):**
  * Visualización de las cartas acumuladas por el usuario.
  * Vista de detalle de cada carta.
* **Persistencia Local (Local-First):**
  * Almacenamiento fiable en el dispositivo de las capturas y cartas sin exigir registro o login obligatorio.
* **Compromiso 100% Pet Friendly:**
  * Mensajes de respeto y seguridad de observación de fauna.
  * Protección de ubicación en especies vulnerables.

---

### 2. SHOULD HAVE (Importante pero secundario)

* **Filtros taxonómicos elementales en la Colección:** Aves, Mamíferos, Insectos, Reptiles, Anfibios.
* **Edición de notas de campo personales** por el usuario en sus cartas ya guardadas.
* **Atmósfera lumínica adaptativa en Home:** Variación sutil de iluminación según la hora del día del dispositivo.

---

### 3. COULD HAVE (Ideas enriquecedoras no críticas)

* **Insignias de Naturalista:** Reconocimientos discretos por hitos de observación responsable (p. ej., *Primer avistamiento de ave*, *Explorador del amanecer*).
* **Exportación de la Card:** Posibilidad de guardar la carta como imagen estética para compartir fuera de la app.
* **Ficha de hábitat sugerido:** Breve texto sobre dónde suele encontrarse la especie.

---

### 4. FUTURE (Terminantemente FUERA del MVP)

Quedan formalmente excluidos del MVP y no deben diseñarse ni implementarse en esta etapa:
* **Dynamic Rarity:** Algoritmos probabilísticos de rareza numérica o drops matemáticos.
* **Trading / Intercambio:** Mecánicas de trueque o intercambio de cartas entre usuarios.
* **PVP / Combates:** Cualquier mecánica competitiva o de batalla.
* **Economía Virtual:** Monedas, gemas, billeteras digitales, tiendas o pases de batalla.
* **Componente Social Complejo:** Feeds públicos, rankings mundiales o listas de amigos.
* **Sincronización en la Nube / Cuentas Obligatorias:** No obligar a crear cuenta ni gestionar infraestructura de backend multi-usuario en el MVP.
* **Marketplace:** Venta o monetización de cartas.
* **Catálogo Global Masivo:** Descarga innecesaria de decenas de miles de especies no relevantes para el ámbito de prueba.
* **Recompensas Complejas:** Árboles de habilidades o perks RPG que compliquen la experiencia básica.
