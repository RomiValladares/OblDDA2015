package logica;

import java.util.Observable;

public abstract class JuegoCasino extends Observable {

    double ganancias;

    public enum EventosJuegoCasino {

        NUEVA_GANANCIA
    }
    /*
     * Texto que se muestra en el listado de juegos del casino
     */

    public abstract String getEtiqueta();

    //TODO estos metodos de abajo podrian reutilizarse mas con un coso template
	/*
     * Agrega un jugador a la partida que corresponda
     */
    public abstract PartidaJuegoCasino jugar(Jugador nuevoJugador) throws Exception;

    /*
     * validacion previa para ingresar al juego
     */
    public abstract boolean puedeJugar(Jugador nuevoJugador) throws Exception;

    /*
     * @return Codigo identificador del juego
     */
    public abstract int getCodigo();

    public abstract boolean tienePartidasActivas();

    protected double getGanancias() {
        return ganancias;
    }

    public void setGanancias(double ganancias) {
        this.ganancias = ganancias;
    }

}
