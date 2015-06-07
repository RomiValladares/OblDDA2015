package logica.poker;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import logica.JuegoCasino;
import logica.Jugador;
import logica.PartidaJuegoCasino;
import logica.poker.ManoPoker.EventoManoPoker;

/**
 *
 * @author Romi
 * Observable porque lo observa la interfaz para actualizarse cuando por ejemplo entra un jugador a la partida
 * Observer porque observa a la partida
 */
public class JuegoPoker extends JuegoCasino implements Observer {

    private static final int codigo = 5;
    private final String etiqueta = "POKER";
    // todas las partidas activas (siendo jugadas) en el momento
    private ArrayList<PartidaPoker> partidas = new ArrayList<>();
    // partida que esta esperando a ser comenzada
    private PartidaPoker proximaPartida;

    public JuegoPoker() {
        crearPartida();
    }

    /*
     * @return POKER, partida x, z/y jugadores.
     * 
     * @see logica.JuegoCasino#getEtiqueta()
     */
    @Override
    public String getEtiqueta() {
        return etiqueta + ", partida: " + proximaPartida.getNumeroPartida()
                + ", " + proximaPartida.getJugadores().size() + "/"
                + proximaPartida.getCantidadMaxJugadores() + " jugadores";
    }

    @Override
    public PartidaJuegoCasino jugar(Jugador nuevoJugador) throws Exception {
        // TODO Auto-generated method stub

        proximaPartida.agregar(nuevoJugador);

        if (proximaPartida == null || proximaPartida.isComenzada()) {
            crearPartida();
            return partidas.get(partidas.size() - 1);
        }

        return proximaPartida;
    }

    @Override
    public void update(Observable observable, Object args) {
        if (args instanceof EventosJuegoCasino) {
            EventosJuegoCasino evento = (EventosJuegoCasino) args;
            if (evento == EventosJuegoCasino.NUEVA_GANANCIA) {
                actualizarGanancias();
                notificar(evento);
            }
        } else if (args instanceof ManoPoker.EventoManoPoker) {
            EventoManoPoker evento = (EventoManoPoker) args;
            if (evento.getEvento() != null && (evento.getEvento().equals(ManoPoker.EventosManoPoker.COMENZO_MANO) && partidas.contains(proximaPartida))) {
                crearPartida();
            }
        } else {
            notificar(null);
        }
    }

    private void actualizarGanancias() {
        double ganancias = 0;
        for (PartidaPoker partida : partidas) {
            ganancias += partida.getGanancias();
        }
        setGanancias(ganancias);
    }

    private void crearPartida() {
        if (proximaPartida != null) {
            partidas.add(proximaPartida);
        }
        proximaPartida = new PartidaPoker(partidas.size());
        proximaPartida.addObserver(this);
    }

    @Override
    public int getCodigo() {
        // TODO Auto-generated method stub
        return codigo;
    }

    @Override
    public boolean puedeJugar(Jugador nuevoJugador) throws Exception {
        return proximaPartida.puedeJugar(nuevoJugador);
    }

    private void notificar(Object evento) {
        setChanged();
        notifyObservers(evento);
    }

    @Override
    public boolean tienePartidasActivas() {
        for (PartidaPoker partida : partidas) {
            if (partida.isComenzada()) {
                return true;
            }
        }
        return false;
    }

}
