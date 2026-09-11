# ARCHITECTURE — Principios de Ingeniería

> **WHO Animal v1**  
> `STATUS: FOUNDATION / DRAFT`

---

## 1. Filosofía Arquitectónica

Who Animal v1 se construye bajo los principios de **Clean Architecture** y **Domain-Driven Design (DDD)**.

La premisa fundacional es:
> **Reutilizar conocimiento acumulado, no arrastrar deuda técnica.**

---

## 2. Principios de Diseño del Software

1. **Separación Estricta de Capas:**
   * **Domain:** Entidades puras, reglas biológicas, ontología de captura y cartas. Totalmente independiente de frameworks, UI, librerías de terceros o persistencia.
   * **Services / Use Cases:** Orquestación de casos de uso (identificación, resolución de descubrimientos, gestión de colección).
   * **Core:** Utilidades fundamentales, despacho de corrutinas, manejo funcional de errores.
   * **Presentation / UI:** Vistas, componentes e interacciones desacopladas de la lógica interna.
2. **Independencia de Frameworks y Proveedores:**
   * El motor de identificación no debe acoplarse directamente a un proveedor de IA concreto. Debe comunicarse a través de interfaces e invariantes de dominio.
   * La interfaz de usuario debe ser una proyección intercambiable de los estados de la aplicación.
3. **Cero Dependencias Innecesarias en Runtime:**
   * No añadir librerías sin una justificación técnica indispensable aprobada por el PM.
4. **Inmutabilidad y Predictibilidad:**
   * Modelos de datos inmutables y flujos de estado unidireccionales (UDF).
