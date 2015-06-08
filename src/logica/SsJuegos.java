package logica;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import logica.JuegoCasino.EventosJuegoCasino;
import logica.poker.PartidaPoker;

//Observer porque observa los juegos para saber cuando se produce una nueva ganancia
//Observable porque la fachada lo observa
public class SsJuegos extends Observable implements Observer {

    private ServiciosJuego servicios;

    private static SsJuegos instancia;
    private ArrayList<JuegoCasino> juegos;
    private double ganancias;

    private SsJuegos() {
//        juegos = FabricadorJuegosCasino.getJuegosCasino();
        servicios = new ServiciosJuegoV1();
        juegos = servicios.getJuegos();
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
        }/* else if (arg instanceof PartidaPoker.EventoPartidaPoker) {
         //finalizo/empezo una partida
         servicios.guardarPartida(juego);
         }*/

        setChanged();
        notifyObservers();
    }

    private void actualizarGanancias() {
        ganancias = 0;
        for (JuegoCasino juego : juegos) {
            ganancias += juego.getGanancias();
        }

        servicios.setGanancias(ganancias);
    }

    boolean hayJuegoActivos() {
        for (JuegoCasino juego : juegos) {
            if (juego.tienePartidasActivas()) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param codigoJuego juego por el cual se van a filtrar las partidas, o -1
     * si se quieren obtener todas
     */
    ArrayList<PartidaJuegoCasino> getPartidas(JuegoCasino codigoJuego) {
        return servicios.getPartidas(codigoJuego.getCodigo());
    }
}
