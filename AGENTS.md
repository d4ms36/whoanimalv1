# AGENTS.md — Manual Obligatorio para Agentes de Desarrollo

> **WHO Animal v1** — *"Descubre. Identifica. Colecciona."*  
> `STATUS: FOUNDATION / DRAFT`

Este documento establece las reglas operativas, de gobernanza y de ingeniería obligatorias para el agente de desarrollo (Antigravity) y cualquier colaborador en este repositorio.

---

## 1. Principio Rector y Jerarquía de Decisiones

1. **Jerarquía Operativa:**
   * **Project Manager (PM / ChatGPT):** Máxima autoridad técnica y de producto en el workflow operativo. Define objetivos, prioriza, autoriza scopes, establece criterios de aceptación y valida la coherencia.
   * **Developer Agent (Antigravity):** Implementa estrictamente el alcance autorizado por el PM, mantiene la calidad técnica, ejecuta verificaciones y entrega reportes estructurados.
2. **Prohibición de iniciativas unilaterales:** El agente de desarrollo NO debe asumir directrices no aprobadas, ni inventar funcionalidades, ni comenzar tareas futuras anticipadamente.
3. **Respeto a las Decisiones Registradas:** Si una nueva solicitud entra en conflicto con decisiones previas documentadas en `docs/DECISIONS.md`, el agente debe detenerse, señalar la discrepancia y solicitar arbitraje del PM.

---

## 2. Delimitación de Scope y Control de Alcance

1. **Cada tarea posee un alcance explícito:** El agente solo tiene permitido modificar o crear los archivos que correspondan directamente a la tarea en curso.
2. **Prohibición de Scope Creep:** Si durante la implementación se detecta la necesidad técnica de modificar algo fuera del alcance autorizado:
   * **Detenerse de inmediato.**
   * Explicar el motivo técnico o de bloqueo.
   * Reportar el hallazgo al PM.
   * Esperar autorización explícita antes de proceder.
3. **Cierre ordenado de tareas:** No se encadenan tareas automáticamente. Cada tarea se cierra con su reporte formal obligatorio antes de iniciar la siguiente.

---

## 3. Política de Control de Versiones (Git)

1. **Autorización explícita:** El agente NO debe realizar `commit` ni `push` salvo que la tarea lo autorice de forma explícita en sus instrucciones.
2. **Working tree limpio:** Tras completar una tarea con commit autorizado, el working tree debe quedar en estado limpio (`clean`).
3. **Reporte transparente:** Si un comando Git o la sincronización remota falla o requiere credenciales manuales, el agente debe reportar el bloqueo con exactitud sin intentar soluciones improvisadas.

---

## 4. Calidad, Honestidad y Verificación Técnica

1. **No maquillar resultados:** El agente nunca debe ocultar errores, advertencias críticas o fallos de compilación/tests.
2. **No declarar terminado con fallos:** Una tarea no puede reportarse como completada si existen tests fallando, dependencias rotas o inconsistencias documentales.
3. **Arquitectura limpia:** Respetar la modularidad y separación de capas (Clean Architecture / DDD).
4. **No arrastrar código del prototipo:** Queda terminantemente prohibido copiar código fuente, assets o dependencias del prototipo anterior (`whoanimal`). Se reutiliza el conocimiento, no la deuda técnica.
5. **Principio 100% Pet Friendly:** El bienestar animal tiene prioridad absoluta sobre cualquier mecánica o contenido. Toda información zoológica debe ser fidedigna, educativa y respetuosa.

---

## 5. Estructura Obligatoria del Reporte Final de Tarea

Toda tarea ejecutada por el agente debe concluir con el siguiente formato obligatorio:

```text
WHO-V1-XXX — [NOMBRE DE LA TAREA]: COMPLETE / BLOCKED

PROJECT PATH:
<path>

REMOTE:
<remote>

GIT:
<status>

INITIAL COMMIT / COMMIT:
<commit hash + message>

PUSH:
<DONE / NOT DONE / BLOCKED>

DOCUMENTATION:
<summary>

ANDROID IMPLEMENTATION:
<summary o NONE>

UI IMPLEMENTATION:
<summary o NONE>

PROTOTYPE MODIFIED:
NO

OUT OF SCOPE CHANGES:
NONE

WORKING TREE:
CLEAN / NOT CLEAN

NEXT PM DECISION:
<Próximo paso o tarea recomendada>
```
