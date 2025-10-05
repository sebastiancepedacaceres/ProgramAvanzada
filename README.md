[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/KF87QXK9)


 Patrones de Diseño Elegidos
-----------------------------

1. Arquitectura General: MVC (Model-View-Controller)
*************************************************

El proyecto sigue el patrón arquitectónico MVC porque nos permite separar las responsabilidades de las clases, facilitar el mantenimiento y modificar la forma de renderizar la vista sin modificar lógica de negocio.

Model (Modelo):
- Responsabilidad: Contener los datos y la lógica de negocio
- Clases: 
GameState: Representa el estado completo del juego
Board: Gestiona el tablero de juego
Entity, Starfighter, Projectile: Entidades del juego

View (Vista):
- Responsabilidad: Presentar la información al usuario por consola
- Clase: 
ConsoleRenderer: Renderiza el estado del juego en consola

Controller (Controlador):
- Responsabilidad: Gestionar la interacción entre el modelo y la vista
- Clase:
StarfighterGameEngine: Coordina el flujo del juego


2. Arquitectura de creación de Projectiles: Abstract Factory
*********************************************************
Permite cambiar el tipo de objetos que a ser creados sin modificar el código, centraliza la lógica de creación en un único lugar y permite extender el código para agregar tipos de proyectiles y starfighters en un futuro.

- Clases:
EntityFactory: Factory para crear entidades genéricas
ProjectileFactory: Factory especializada para proyectiles
Entity: Clase Abstracta
Projectile: clase que extiende Entity

3. Arquitectura de comandos: Patrón Command
******************************************
Objetivo: Encapsular cada acción del juego como un objeto independiente y desacoplar el objeto que invoca la operación del que la ejecuta.

En un futuro si existen distintas formas de ejecutar los comandos 
se puede agregar un patrón strategy a cada comando.

- Clases: 
Command: Clase abstracta que define la interfaz común execute(state: GameState): GameState
Comandos concretos: Implementan acciones específicas (disparar, mover, pasar turno, abortar juego, movimiviento de projectiles)