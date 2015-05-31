package logica;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import logica.JuegoCasino.EventosJuegoCasino;

//Observer porque observa los juegos para saber cuando se produce una nueva ganancia
//Observable porque la fachada lo observa
public class SsJuegos extends Observable implements Observer {

    private static SsJuegos instancia;
    private ArrayList<JuegoCasino> juegos;
    private double ganancias;

    private SsJuegos() {
        juegos = FabricadorJuegosCasino.getJuegosCasino();
        for (JuegoCasino juego : juegos) {
            juego.addObserver(this);
        }
    }

    public static SsJuegos getInstancia() {
        if (instancia == null) {
            instancia = new SsJuegos();
        }
        return instancia;
    }

    public ArrayList<JuegoCasino> getJuegos() {
        return juegos;
    }

    protected double getGanancias() {
        return ganancias;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof EventosJuegoCasino) {
            actualizarGanancias();
        }
        setChanged();
        notifyObservers();
    }

    private void actualizarGanancias() {
        ganancias = 0;
        for (JuegoCasino juego : juegos) {
            ganancias += juego.getGanancias();
        }
    }

    boolean hayJuegoActivos() {
        for (JuegoCasino juego : juegos) {
            if (juego.tienePartidasActivas()) {
                return true;
            }
        }
        return false;
    }
}
