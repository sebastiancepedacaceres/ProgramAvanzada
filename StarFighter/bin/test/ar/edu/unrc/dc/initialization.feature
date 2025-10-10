Feature: Inicialización del juego Starfighter.

  Scenario: Starfighter correctamente posicionado al inicio con filas impares
    Given que la aplicacion ha sido iniciada
    When se inicializa el juego con 5 filas y 5 columnas con m1=10 y m2=2
    Then el starfighter deberia estar posicionado en la fila 2, columna 0
    And la cantidad de proyectiles es 0

  Scenario: No se puede iniciar un nuevo juego mientras otro está activo
    Given que la aplicacion ha sido iniciada
    And el juego se inicializa con 5 filas y 5 columnas con m1=10 y m2=2
    When el jugador intenta iniciar un nuevo juego
    Then deberia mostrarse un mensaje de error

  # Comando Pass
  Scenario: El comando Pass avanza el turno
    Given que la aplicacion ha sido iniciada
    And el juego se inicializa con 5 filas y 10 columnas con m1=10 y m2=2
    When el jugador selecciona el comando pass
    Then el starfighter deberia estar posicionado en la fila 2, columna 0

  # Comando Fire
  Scenario: El starfighter dispara un proyectil
    Given que la aplicacion ha sido iniciada
    And el juego se inicializa con 5 filas y 10 columnas con m1=10 y m2=2
    When el jugador selecciona el comando fire
    Then verifica que hay un proyectil en la fila 2, columna 1
    And la cantidad de proyectiles es 1

  Scenario: El starfighter dispara desde la última columna
    Given que la aplicacion ha sido iniciada
    And el juego se inicializa con 5 filas y 10 columnas con m1=10 y m2=2
    And el starfighter se movio a la fila 2, columna 9
    When el jugador selecciona el comando fire
    Then en la grilla hay 0 proyectiles
    And la cantidad de proyectiles es 0

  Scenario: Múltiples proyectiles en la grilla
    Given que la aplicacion ha sido iniciada
    And el juego se inicializa con 5 filas y 10 columnas con m1=10 y m2=2
    When el jugador dispara 3 veces
    Then en la grilla hay 3 proyectiles
    And la cantidad de proyectiles es 3

  # Comando Move
  Scenario: El starfighter se mueve verticalmente hacia abajo
    Given que la aplicacion ha sido iniciada
    And el juego se inicializa con 5 filas y 10 columnas con m1=10 y m2=2
    And el starfighter se movio a la fila 3, columna 1
    When el jugador se mueve con vertical = 2 y horizontal = 0
    Then el starfighter deberia estar posicionado en la fila 5, columna 1

  Scenario: El starfighter se mueve solo horizontalmente
    Given que la aplicacion ha sido iniciada
    And el juego se inicializa con 5 filas y 10 columnas con m1=10 y m2=2
    And el starfighter se movio a la fila 3, columna 1
    When el jugador se mueve con vertical = 0 y horizontal = 4
    Then el starfighter deberia estar posicionado en la fila 3, columna 5
    

  Scenario: El starfighter se mueve en diagonal (vertical y luego horizontal)
    Given que la aplicacion ha sido iniciada
    And el juego se inicializa con 5 filas y 10 columnas con m1=10 y m2=2
    And el starfighter se movio a la fila 3, columna 1
    When el jugador se mueve con vertical = -2 y horizontal = 3
    Then el starfighter deberia estar posicionado en la fila 1, columna 4

  Scenario: Movimiento excede la restricción de m1
    Given que la aplicacion ha sido iniciada
    And el juego se inicializa con 5 filas y 10 columnas con m1=5 y m2=2
    And el starfighter se movio a la fila 3, columna 1
    When el jugador se mueve con vertical = -2 y horizontal = 4
    Then deberia mostrarse un mensaje de error
    And el starfighter deberia estar posicionado en la fila 3, columna 1


  # Proyectiles
  Scenario: El proyectil se mueve a la derecha m2 espacios
    Given que la aplicacion ha sido iniciada
    And el juego se inicializa con 5 filas y 10 columnas con m1=10 y m2=3
    And hay un proyectil en la fila 3, columna 2
    When el jugador selecciona el comando pass
    Then verifica que hay un proyectil en la fila 3, columna 5
    And la cantidad de proyectiles es 1


  Scenario: El proyectil sale de la grilla
    Given que la aplicacion ha sido iniciada
    And el juego se inicializa con 5 filas y 10 columnas con m1=10 y m2=3
    And hay un proyectil en la fila 3, columna 9
    When el jugador selecciona el comando pass
    Then en la grilla hay 0 proyectiles
    And la cantidad de proyectiles es 0
    

    Scenario: Fase 1: Avanzan todos los proyectiles de la grilla
    Given que la aplicacion ha sido iniciada
    And el juego se inicializa con 5 filas y 10 columnas con m1=10 y m2=3
    And hay un proyectil en la fila 2, columna 3
    And hay un proyectil en la fila 1, columna 2
    And hay un proyectil en la fila 3, columna 4
    When ocurre la fase 1
    Then verifica que hay un proyectil en la fila 2, columna 6
    And verifica que hay un proyectil en la fila 1, columna 5
    And verifica que hay un proyectil en la fila 3, columna 7
    And la cantidad de proyectiles es 3


  Scenario: Múltiples proyectiles se mueven
    Given que la aplicacion ha sido iniciada
    And el juego se inicializa con 5 filas y 15 columnas con m1=10 y m2=2
    And el starfighter se movio a la fila 1, columna 3
    And hay un proyectil en la fila 3, columna 2
    And hay un proyectil en la fila 1, columna 4
    When el jugador selecciona el comando pass
    Then en la grilla hay 2 proyectiles
    And la cantidad de proyectiles es 2
    And verifica que hay un proyectil en la fila 3, columna 4
    And verifica que hay un proyectil en la fila 1, columna 6

  # Comando Abort
  Scenario: El jugador aborta un juego activo
    Given que la aplicacion ha sido iniciada
    And el juego se inicializa con 5 filas y 10 columnas con m1=10 y m2=2
    When el jugador selecciona el comando abort
    Then el juego termina 
    And se puede iniciar un nuevo juego

  Scenario: No se puede abortar cuando no hay un juego activo
    Given que la aplicacion ha sido iniciada
    And no hay un juego activo
    When el jugador selecciona el comando abort
    Then deberia mostrarse un mensaje de error

  # Colisiones
  Scenario: Un proyectil colisiona con el starfighter durante la fase de movimiento
    Given que la aplicacion ha sido iniciada
    And el juego se inicializa con 5 filas y 10 columnas con m1=10 y m2=2
    And el starfighter se movio a la fila 3, columna 5
    And hay un proyectil en la fila 3, columna 3
    When el jugador selecciona el comando pass
    Then el starfighter deberia estar destruido
    And la colision deberia marcarse en la fila 3, columna 5
    Then el juego termina 
    And la cantidad de proyectiles es 1


  Scenario: El juego termina inmediatamente con la colision de un proyectil
    Given que la aplicacion ha sido iniciada
    And el juego se inicializa con 5 filas y 10 columnas con m1=10 y m2=2
    And el starfighter se movio a la fila 3, columna 5
    And hay un proyectil en la fila 3, columna 3
    When el jugador selecciona el comando fire
    Then el starfighter deberia estar destruido
    And la colision deberia marcarse en la fila 3, columna 5
    And no hay proyectil en la fila 3, columna 6
    And la cantidad de proyectiles es 1
