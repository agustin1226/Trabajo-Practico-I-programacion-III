# Threes! - Programación 3 (UNGS)

Trabajo Práctico desarrollado en Java utilizando el paradigma de Programación Orientada a Objetos y arquitectura **MVC (Modelo-Vista-Controlador)**, basado en el clásico juego de lógica **Threes!**.

---

## 🚀 Características Principales
* **Interfaz Gráfica (GUI):** Desarrollada con Java Swing y AWT, estructurada en múltiples ventanas modulares (`VentanaInicio`, `VentanaJuego`, `VentanaFinJuego` y `RankingPuntajes`).
* **Patrón Observer:** Utilizado para desacoplar la lógica del juego de la interfaz visual, notificando de manera automática al tablero y al contador de puntajes ante cada movimiento del usuario.
* **Persistencia de Datos (Patrón DAO):** Implementación de una interfaz `PuntajesDAO` junto a una clase `PuntajesArchivoDAO` para almacenar y recuperar los récords de los jugadores en un archivo de texto (`ranking.txt`), permitiendo ordenar el ranking de mayor a menor.
* **Gestión de Usuarios:** Creación de entidades `Usuario` para asociar las partidas y los puntajes históricos de manera formal.
* **Diseño Dinámico:** Tablero interactivo con representación visual personalizada de las fichas y sus respectivos colores según su valor numérico.

---

## 📂 Arquitectura del Proyecto (Paquetes)
El proyecto está estructurado respetando la separación de responsabilidades:
* `logica`: Contiene las reglas del negocio, la matriz del tablero, las fichas, el manejo de movimientos y el patrón Observer (`JuegoThrees`, `Tablero`, `Casilla`, `Usuario`, etc.).
* `presentacion`: Contiene todas las vistas y componentes visuales construidos con Swing y WindowBuilder (`Main`, `VentanaInicio`, `VentanaJuego`, `PanelTablero`, etc.).
* `datos`: Contiene la capa de acceso a datos y persistencia (`PuntajesDAO`, `PuntajesArchivoDAO`).

---

## 🛠️ Cómo Ejecutar el Proyecto
1. Asegurate de tener configurado el entorno de desarrollo (Eclipse IDE recomendado) con **Java SE (JDK 21 o superior)**.
2. Cloná o descargá el repositorio en tu máquina.
3. Importá el proyecto en Eclipse como un proyecto Java existente.
4. Localizá la clase principal de ejecución: `src/presentacion/Main.java`.
5. Hacé clic derecho sobre `Main.java` y seleccioná **Run As -> Java Application**.
