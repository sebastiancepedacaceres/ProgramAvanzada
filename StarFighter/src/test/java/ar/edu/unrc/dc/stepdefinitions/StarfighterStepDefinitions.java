package ar.edu.unrc.dc.stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.models.StarfighterGameEngine;
import ar.edu.unrc.dc.models.entities.Entity;
import ar.edu.unrc.dc.models.entities.Projectile;
import ar.edu.unrc.dc.models.entities.Starfighter;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StarfighterStepDefinitions {

    private StarfighterGameEngine engine;
    private GameState state;
    private String error;

    // =============================
    // GIVEN
    // =============================

    @Given("que la aplicacion ha sido iniciada")
    public void iniciarAplicacion() {
        engine = new StarfighterGameEngine();
    }

    @Given("el juego se inicializa con {int} filas y {int} columnas con m1={int} y m2={int}")
    public void inicializarJuego(int filas, int columnas, int m1, int m2) {
        engine.play(filas, columnas, m1, m2);
        state = engine.getCurrentGame();
    }


    @When("se inicializa el con {int} filas y {int} columnas con m1={int} y m2={int}")
    public void inicializar(int filas, int columnas, int m1, int m2) {
        engine.play(filas, columnas, m1, m2);
        state = engine.getCurrentGame();
    }

    @When("el jugador selecciona el comando fire")
    public void seleccionarComandoFire() {
        engine.fire();
        engine.ProjectilesAdvance();
        state = engine.getCurrentGame();
    }

    @Given("el starfighter se movio a la fila {int}, columna {int}")
    public void modificarPosicionDelStarfighter(int fila, int columna) {
        Board board = state.getBoard();
        Starfighter currentStarfighter = state.getStarfighter();

        board.clearCell(currentStarfighter.getRow(), currentStarfighter.getColumn());

        Starfighter newStarfighter = new Starfighter(fila, columna);
        state.setStarfighter(newStarfighter);

        board.placeEntity(fila, columna, Board.STARFIGHTER);
    }

    @Given("hay un proyectil en la fila {int}, columna {int}")
    public void hayUnProyectilEnPosicion(int fila, int columna) {
        Projectile proyectil = new Projectile(fila, columna);
        state.getProjectiles().add(proyectil);
        state.getBoard().placeEntity(fila, columna, Board.PROJECTILE);
    }

    @Given("no hay un juego activo")
    public void noHayJuegoActivo() {
        if (state != null && !state.isGameOver()) {
            engine.abort();
            state = engine.getCurrentGame();
        }

    }

    // =============================
    // WHEN
    // =============================

    @When("el jugador intenta iniciar un nuevo juego")
    public void intentarIniciarNuevoJuego() {
        try {
            engine.play(5, 5, 10, 2); // Intento de segundo juego
            error = null; // No se lanzó excepción
        } catch (IllegalStateException e) {
            error = e.getMessage(); // Guardamos el mensaje de error
        }
    }

    @When("el jugador selecciona el comando pass")
    public void seleccionarComandoPass() {
        engine.pass();
        engine.ProjectilesAdvance();
        state = engine.getCurrentGame();
    }

    @When("el jugador dispara {int} veces")
    public void jugadorDisparaTresVeces(int veces) {
        for (int i = 0; i < veces; i++) {
            engine.fire();
            engine.ProjectilesAdvance();
        }
        state = engine.getCurrentGame();
    }

    @When("el jugador se mueve con vertical = {int} y horizontal = {int}")
    public void seleccionarComandoMove(int vertical, int horizontal) {
        try {
            engine.move(vertical, horizontal);
            error = null; // No se lanzó excepción
            engine.ProjectilesAdvance();

        } catch (IllegalArgumentException e) {
            error = e.getMessage(); // Guardamos el mensaje de error
        }
        state = engine.getCurrentGame();
    }

    @When("el jugador selecciona el comando abort")
    public void seleccionarComandoAbort() {
        try {
            engine.abort();
            state = engine.getCurrentGame();
            error = null; // No hubo error
        } catch (IllegalStateException e) {
            error = e.getMessage();
        }
    }

    @When("ocurre la fase 1")
    public void ocurreFase1() {
        engine.ProjectilesAdvance();
        state = engine.getCurrentGame();
    }

    // =============================
    // THEN
    // =============================

    @Then("el starfighter deberia estar posicionado en la fila {int}, columna {int}")
    public void verificarPosicionStarfighter(int filaEsperada, int columnaEsperada) {
        int filaActual = state.getStarfighter().getRow();
        int columnaActual = state.getStarfighter().getColumn();
        assertEquals(filaEsperada, filaActual, "La fila del starfighter no es la esperada");
        assertEquals(columnaEsperada, columnaActual, "La columna del starfighter no es la esperada");
    }

    @Then("la cantidad de proyectiles es {int}")
    public void verificarCantidadProyectiles(int cantidadEsperada) {
        int cantidadActual = state.getProjectiles().size();
        assertEquals(cantidadEsperada, cantidadActual, "La cantidad de proyectiles no es la esperada");
    }

    @Then("deberia mostrarse un mensaje de error")
    public void verificarMensajeError() {
        assertNotNull(error, "Se esperaba un mensaje de error");
    }

    // Checks if the projectile is in the position on the board
    // and if it is on the list state.getProjectiles()
    @Then("verifica que hay un proyectil en la fila {int}, columna {int}")
    public void verificarPosicionProyectil(int filaEsperada, int columnaEsperada) {
        boolean encontrado = false;
        Board board = state.getBoard();
        char projectile = Board.PROJECTILE;

        for (Entity proyectil : state.getProjectiles()) {
            if (proyectil.getRow() == filaEsperada && proyectil.getColumn() == columnaEsperada) {
                encontrado = true;
                break;
            }
        }

        if (encontrado == true &&
                board.getCellContent(filaEsperada, columnaEsperada) != projectile) {
            encontrado = false;
        }

        char contenido = state.getBoard().getCellContent(filaEsperada, columnaEsperada);

        assertEquals(true, encontrado,
                "No se encontró el proyectil en la posición esperada (" +
                        filaEsperada + ", " + columnaEsperada + ")");
        assertEquals('*', contenido,
                "El contenido de la celda no es un proyectil en (" +
                        filaEsperada + ", " + columnaEsperada + ")");

    }

    @Then("en la grilla hay {int} proyectiles")
    public void verificarCantidadProyectilesEnGrilla(int cantidadEsperada) {
        int cantidadActual = 0;
        for (int r = 0; r < state.getBoard().getRows(); r++) {
            for (int c = 0; c < state.getBoard().getColumns(); c++) {
                if (state.getBoard().getCellContent(r, c) == '*') {
                    cantidadActual++;
                }
            }
        }
        assertEquals(cantidadEsperada, cantidadActual, "La cantidad de proyectiles en la grilla no es la esperada");
    }

    @Then("el juego termina")
    public void verificarTerminoJuegoo() {
        boolean result = state.isGameOver();
        assertEquals(true, result,
                "El juego debería haber terminado");
    }

    @Then("se puede iniciar un nuevo juego")
    public void verificarSePuedeIniciarNuevoJuego() {
        try {
            engine.play(5, 5, 10, 2);
            state = engine.getCurrentGame();
            error = null;
            assertEquals(false, state.isGameOver(),
                    "El nuevo juego debería estar activo");
        } catch (IllegalStateException e) {
            error = e.getMessage();
            assertEquals(null, error,
                    "No debería haber error al iniciar un nuevo juego después de abort");
        }
    }

    @Then("el starfighter deberia estar destruido")
    public void verificarStarfighterDestruido() {
        assertEquals(true, state.getStarfighter().isDestroyed(),
                "El starfighter debería estar destruido");
    }

    @Then("la colision deberia marcarse en la fila {int}, columna {int}")
    public void verificarColisionEnPosicion(int fila, int columna) {
        char contenido = state.getBoard().getCellContent(fila, columna);
        assertEquals(Board.COLLISION, contenido,
                "Debería haber una colisión marcada en la posición (" + fila + ", " + columna + ")");
    }

    
    @Then("no hay proyectil en la fila {int}, columna {int}")
    public void verificarNoHayProyectil(int fila, int columna) {
        Starfighter sf = state.getStarfighter();
        int filaStarfighter = sf.getRow();
        int colStarfighter = sf.getColumn();
    
        boolean hayProyectilNuevo = false;

        for (Entity proyectil : state.getProjectiles()) {
            if (proyectil.getRow() == filaStarfighter &&
                    proyectil.getColumn() == colStarfighter + 1) {
                hayProyectilNuevo = true;
                break;
            }
        }

        assertEquals(false, hayProyectilNuevo,
                "No debería haberse creado un nuevo proyectil después de la colisión");

        // Verificación adicional en el tablero
        if (colStarfighter + 1 < state.getBoard().getColumns()) {
            char contenido = state.getBoard().getCellContent(filaStarfighter, colStarfighter + 1);
            boolean hayProyectilEnBoard = (contenido == Board.PROJECTILE || contenido == '*');
            assertEquals(false, hayProyectilEnBoard,
                    "No debería haber proyectil en el tablero en la posición de disparo");
        }
    }
}
