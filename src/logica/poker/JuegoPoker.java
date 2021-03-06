package logica.poker;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.FabricadorJuegosCasino.CodigosJuego;
import logica.JuegoCasinoV1;
import logica.Jugador;
import logica.PartidaJuegoCasinoV1;
import logica.poker.ManoPoker.EventoManoPoker;
import observableremoto.ObservableRemoto;
import observableremoto.ObservadorRemoto;

/**
 * ObservadorRemoto porque observa a la partida, que es ObservableRemoto
 *
 * @author Romi
 */
public class JuegoPoker extends JuegoCasinoV1 implements ObservadorRemoto {

    private static final CodigosJuego codigo = CodigosJuego.POKER;
    private final String etiqueta = "POKER";

    public JuegoPoker() throws RemoteException {
        crearPartida();
    }

    /*
     * @return POKER, partida x, z/y jugadores.
     * 
     * @see logica.JuegoCasino#getEtiqueta()
     */
    @Override
    public String getEtiqueta() {
        return etiqueta + ", partida: " + getProximaPartida().getNumeroPartida()
                + ", " + getProximaPartida().getJugadoresPartida().size() + "/"
                + getProximaPartida().getCantidadMaxJugadores() + " jugadores";
    }

    @Override
    public PartidaJuegoCasinoV1 jugar(Jugador nuevoJugador) throws Exception {
        // TODO Auto-generated method stub

        getProximaPartida().agregar(nuevoJugador);

        if (getProximaPartida() == null || getProximaPartida().isComenzada()) {
            crearPartida();
            return getPartidas().get(getPartidas().size() - 1);
        }

        return getProximaPartida();
    }

//    @Override
//    public void update(Observable observable, Object args) {
//        if (args instanceof EventosJuegoCasino) {
//            EventosJuegoCasino evento = (EventosJuegoCasino) args;
//            if (evento == EventosJuegoCasino.NUEVA_GANANCIA) {
//                actualizarGanancias();
//                notificar(evento);
//            }
//        } else if (args instanceof ManoPoker.EventoManoPoker) {
//            EventoManoPoker evento = (EventoManoPoker) args;
//            if (evento.getEvento() != null && (evento.getEvento().equals(ManoPoker.EventosManoPoker.COMENZO_MANO) && getPartidas().contains(getProximaPartida()))) {
//                crearPartida();
//            }
//        } else {
//            notificar(null);
//        }
//    }
    private void actualizarGanancias() {
        double ganancias = 0;
        for (PartidaPokerV1 partida : getPartidas()) {
            ganancias += partida.getGanancias();
        }
        setGanancias(ganancias);
    }

    @Override
    public CodigosJuego getCodigo() {
        // TODO Auto-generated method stub
        return codigo;
    }

    @Override
    public boolean puedeJugar(Jugador nuevoJugador) throws Exception {
        return getProximaPartida().puedeJugar(nuevoJugador);
    }

    public void notificar(Object evento) {
        setChanged();
        notifyObservers(evento);
    }

    @Override
    public boolean tienePartidasActivas() {
        for (PartidaPokerV1 partida : getPartidas()) {
            if (partida.isComenzada()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getNombre() {
        return "Poker";
    }

    @Override
    protected void crearPartida() {
        try {
            if (getProximaPartida() != null) {
                getPartidas().add(getProximaPartida());
            }
            int ultimoNPartida = getUltimoNumeroPartida();
            setProximaPartida(new PartidaPokerV1(++ultimoNPartida));
            setUltimoNumeroPartida(ultimoNPartida);
            getProximaPartida().agregar(this);
        } catch (RemoteException ex) {
            Logger.getLogger(JuegoPoker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actualizar(ObservableRemoto origen, Object args) throws RemoteException {
        if (args instanceof EventosJuegoCasino) {
            EventosJuegoCasino evento = (EventosJuegoCasino) args;
            if (evento == EventosJuegoCasino.NUEVA_GANANCIA) {
                actualizarGanancias();
                notificar(evento);
            }
        } else if (args instanceof ManoPoker.EventoManoPoker) {
            EventoManoPoker evento = (EventoManoPoker) args;
            if (evento.getEvento() != null && (evento.getEvento().equals(ManoPoker.EventosManoPoker.COMENZO_MANO) && getPartidas().contains(getProximaPartida()))) {
                crearPartida();
            }
        } else {
            notificar(null);
        }
    }

}
