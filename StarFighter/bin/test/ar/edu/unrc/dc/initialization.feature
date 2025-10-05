Feature: Starfighter game initialization.

  Scenario: Starfighter correctly positioned at the start of game, when grid has odd rows and columns
    Given the application has been started
    When the game is initialized with a grid of 5 rows and 5 columns
    Then the starfighter should be positioned at row 3, column 3

  Scenario: La nave dispara
    Given
    When el tablero esta en este estado
      |   |   |   |
      | S |   |   |
      |   |   |   |
    And el starship dispara
    Then el tablero esta en este estado
      |   |   |   |
      | S | * |   |
      |   |   |   |