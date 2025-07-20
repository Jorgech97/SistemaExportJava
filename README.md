# Sistema de Gestión de Exportaciones

Este proyecto es una aplicación desarrollada en **Java con NetBeans** que implementa conceptos intermedios de programación orientada a objetos, manejo de archivos y diseño de interfaces gráficas. Permite administrar exportaciones internacionales mediante clases especializadas, cálculos automáticos de costos y almacenamiento persistente de datos.

## 📌 Objetivo

Demostrar el dominio de los siguientes temas:
- Herencia y polimorfismo.
- Interfaces gráficas de usuario (GUI).
- Manipulación de archivos (`Exportaciones.dat`).
- Validaciones y excepciones en tiempo de ejecución.
- Estructuras de datos y lógica condicional para procesos empresariales.

## 🚀 Funcionalidades Principales

- Registro de exportaciones con validación de datos:
  - **ID Cliente:** Formato nacional (ej. `1-1234-5678`).
  - **Nombre completo:** Mínimo 7 caracteres.
  - **Tipo de exportación:** `ECP` (Carga Pesada) o `ECS` (Carga Suelta).
  - **Zona de envío (país)** y **tipo de servicio:** Barco o avión.
  - **Peso en kilogramos.**
- Fecha de exportación automática (basada en la fecha actual del sistema).
- Cálculo automático del costo de exportación:
  - **ECP (Carga Pesada):** Basado en tipo de carga:
    - Contenedor Refrigerado: $950/kg (solo Barco).
    - Contenedor No Refrigerado: $550/kg.
    - Carga Embalada: $450/kg.
  - **ECS (Carga Suelta):**
    - Avión: $450/kg + $100 si >18 pies.
    - Barco: $150/kg + $50 si >18 pies.
- Gestión de registros en archivo binario (`Exportaciones.dat`):
  - Agregar
  - Actualizar
  - Eliminar
- Generación de reportes filtrables por:
  - **ID de cliente.**
  - **Zona de envío.**
  - Muestra: ID, nombre, zona, fecha y costo total.
  
## 🧩 Arquitectura de Clases

- `Exportacion` (clase base)
- `ExportacionCargaPesada` (hereda de `Exportacion`)
- `ExportacionCargaSuelta` (hereda de `Exportacion`)
- `ControladorArchivo` (lectura/escritura binaria)
- `VentanaPrincipal` (GUI principal con componentes Swing)
- `PanelReportes` (módulo de reportes)
  
## 🖼️ Interfaz Gráfica

Se incluye GUI con mínimo 3 componentes:
- Formularios con `JTextField`, `JComboBox`, `JRadioButton`.
- Botones para registrar, actualizar, eliminar exportaciones.
- Tabla de reportes (`JTable`) con filtros dinámicos.

## 🧪 Validaciones y Excepciones

- Validación de formatos y restricciones de entrada.
- Manejadores `try-catch` para errores de conversión, acceso a archivos y formatos inválidos.
  
## 🛠️ Requisitos Técnicos

- Java JDK 17+
- NetBeans IDE 17+
- Sistema operativo compatible con Java (Windows/Linux/MacOS)

## 📂 Estructura del Proyecto
