package logica.poker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.JuegoCasino.EventosJuegoCasino;
import logica.Jugador;
import logica.PartidaJuegoCasino;

/*
 * Observable porque FramePoker lo observa
 * Observer porque observa a cada ManoPoker
 */
public class PartidaPoker extends PartidaJuegoCasino implements Observer {

    private Jugador ganador;
    private double ganancias;

    private HashMap<Jugador, Double> jugadores = new HashMap<>();
    private ManoPoker manoActual;
    private int cantidadMaxJugadores = 4;
    private double apuestaBase = 50;

    public void retirarse(Jugador jugador) throws Exception {
        if (jugadores.containsKey(jugador)) {
            if (manoActual != null && !manoActual.isFinalizada()) {
                manoActual.retirarse(jugador);
            }
            //resta el 10% de lo ganado
            restarGanancias(jugador);
            jugadores.remove(jugador);

            //esto es para cuando un jugador elija retirarse sin que haya empezado la partida
            if (isComenzada() && jugadores.size() == 1 && !isFinalizada()) {
                finalizarPartida();
            } else {
                notificar(new EventoPartidaPoker(EventosPartidaPoker.SALIDA_JUGADOR, jugador + " sale del juego.", jugador));
            }
            if (jugadoresQueSiguen == jugadores.size() && isComenzada()) {
                jugadoresQueSiguen = 0;
                comenzar();
            }
        } else {
            throw new Exception("El jugador no esta en esta partida.");
        }
    }

    @Override
    protected void obtenerGanador() {
        if (jugadores.size() >= 1) {
            //puede pasar que haya terminado la mano, y el ganador salga
            if (manoActual.getGanadorYFigura() == null || !jugadores.containsKey(manoActual.getGanadorYFigura().getKey())) {
                ganador = (Jugador) jugadores.keySet().toArray()[0];
            } else {
                ganador = manoActual.getGanadorYFigura().getKey();
            }
            ganador.agregarSaldo(manoActual.getPozo());
            notificar(new EventoPartidaPoker(EventosPartidaPoker.FINALIZO_PARTIDA, "Finalizo la partida. Ganador: " + ganador, ganador));
        } else {
            notificar(new EventoPartidaPoker(EventosPartidaPoker.FINALIZO_PARTIDA, "Finalizo la partida sin ganador.", null));
        }
    }

    @Override
    public Jugador getGanador() throws Exception {
        if (!isFinalizada()) {
            throw new Exception("La partida no finalizo, todavia no existe un ganador.");
        }
        return ganador;
    }

    private int jugadoresQueSiguen = 0;

    //se invoca cada vez que termina una mano y se les pregunta a los jugadores si seguir o no
    public void continuarEnJuego(Jugador jugador, boolean seguir) throws Exception {
        if (!seguir) {
            retirarse(jugador);
        } else {
            Logger.getLogger(PartidaPoker.class.getName()).log(Level.INFO, null, "continuarEnJuego " + jugador);
            jugadoresQueSiguen++;
            Logger.getLogger("jugadoresQueSiguen " + jugadoresQueSiguen + " jugadores.size()=" + jugadores.size());
            if (jugadoresQueSiguen == jugadores.size()) {
                jugadoresQueSiguen = 0;
                comenzar();
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o.getClass().equals(ManoPoker.class)) {
            if (manoActual.isFinalizada()) {
                setTotalApostado(getTotalApostado() + manoActual.getMontoApostado());
                if (manoActual.getGanadorYFigura() != null) {
                    //actualiza el saldo del jugador
                    jugadores.put(manoActual.getGanadorYFigura().getKey(), manoActual.getPozo());
                }
                checkJugadoresSaldoInsuficiente();
            }
            //si despues de chequear el saldo de los jugadores todavia no termino la partida
            //deja que la mano actual notifique
            if (!isFinalizada()) {
                notificar(arg);
            }

            modificar();
        }
    }

    public boolean puedeJugar(Jugador nuevoJugador) throws Exception {
        if (!checkSaldoSuficiente(nuevoJugador)) {
            // TODO hardcoded
            throw new Exception("El saldo del jugador "
                    + nuevoJugador.getNombre()
                    + " es menor a la apuesta base (" + apuestaBase + ")");
        } else if (jugadores.containsKey(nuevoJugador)) {
            return true;
        } else if (jugadores.size() == cantidadMaxJugadores) {
            // TODO hardcoded
            throw new Exception("La partida " + getNumeroPartida() + " ya tiene "
                    + cantidadMaxJugadores + " jugadores.");
        } else {
            return true;
        }

    }

    private boolean checkSaldoSuficiente(Jugador j) {
        return j.getSaldo() > apuestaBase;
    }

    private void restarGanancias(Jugador jugador) {
        double diezPorcientoGanancias = (10 * jugadores.get(jugador)) / 100;
        jugador.restarSaldo(diezPorcientoGanancias);
        ganancias += diezPorcientoGanancias;
        notificar(EventosJuegoCasino.NUEVA_GANANCIA);
    }

    protected double getGanancias() {
        return ganancias;
    }

    public enum EventosPartidaPoker {

        COMENZO_PARTIDA, FINALIZO_PARTIDA, JUGADOR_SALDO_INSUFICIENTE, SALIDA_JUGADOR
    }

    public PartidaPoker(int numeroPartida) {
        setNumeroPartida(numeroPartida);
    }

    public void agregar(Jugador nuevoJugador) throws Exception {
        if (puedeJugar(nuevoJugador)) {
            jugadores.put(nuevoJugador, 0d);

            if (jugadores.size() == cantidadMaxJugadores) {
                comenzar();
            } else {
                notificar();
            }
        }
    }

    @Override
    protected void comenzar() {
        super.comenzar();

        //se asume que de afuera ya se controlo que todos los jugadores tuvieran saldo disponible
        descontarSaldo();

        boolean primeraMano = false;
        if (manoActual == null) {
            primeraMano = true;
        }

        if (manoActual != null && manoActual.getApuesta() == null) {
            nuevaMano(manoActual.getPozo());
        } else {
            nuevaMano(0);
        }

        if (primeraMano) {
            notificar(EventosPartidaPoker.COMENZO_PARTIDA);
        }
        //si no ya avisa la nueva mano
    }

    private void descontarSaldo() {
        for (Map.Entry<Jugador, Double> entrySet : jugadores.entrySet()) {
            entrySet.getKey().restarSaldo(apuestaBase);
        }
    }

    private void nuevaMano(double pozoAcumulado) {
        manoActual = new ManoPoker(new ArrayList<>(jugadores.keySet()), apuestaBase, pozoAcumulado);
        manoActual.addObserver(this);
        manoActual.comenzar();
    }

    private void checkJugadoresSaldoInsuficiente() {
        ArrayList<Jugador> jugadoresSaldoInsuficiente = new ArrayList<>();
        for (Iterator<Map.Entry<Jugador, Double>> iterator = jugadores.entrySet().iterator(); iterator.hasNext();) {
            Jugador j = iterator.next().getKey();
            if (!checkSaldoSuficiente(j)) {
                jugadoresSaldoInsuficiente.add(j);
                //iterator.remove();
                //notificar(new EventoPartidaPoker(EventosPartidaPoker.JUGADOR_SALDO_INSUFICIENTE, j + " ya no tiene saldo suficiente, queda fuera del juego", j));
            }
        }

        if (jugadoresSaldoInsuficiente.size() == jugadores.size()) {
            //perdieron todos?
            finalizarPartida();
        } else if (jugadoresSaldoInsuficiente.size() == jugadores.size() - 1) {
            //solo uno tiene saldo suficiente asi que gana
            finalizarPartida();
        } else {
            //quedan al menos dos personas en el juego, no gano nadie todavia
            for (Iterator<Map.Entry<Jugador, Double>> iterator = jugadores.entrySet().iterator(); iterator.hasNext();) {
                Jugador j = iterator.next().getKey();
                if (jugadoresSaldoInsuficiente.contains(j)) {
                    iterator.remove();
                    notificar(new EventoPartidaPoker(EventosPartidaPoker.JUGADOR_SALDO_INSUFICIENTE, j + " ya no tiene saldo suficiente, queda fuera del juego", j));
                }
            }
        }
    }

    @Override
    public void agregarJugador(Jugador j) {
        jugadores.put(j, 0d);
    }

    @Override
    public void setGanador(Jugador j) {
        ganador = j;
    }

    @Override
    public ArrayList<Jugador> getJugadores() {
        return new ArrayList<>(jugadores.keySet());
    }

    public int getCantidadMaxJugadores() {
        return cantidadMaxJugadores;
    }

    private void notificar() {
        notificar(null);
    }

    private void notificar(Object msg) {
        setChanged();
        if (msg != null) {
            notifyObservers(msg);
        } else {
            notifyObservers();
        }
    }

    public List<CartaPoker> getCartasJugador(Jugador j) throws Exception {
        return manoActual.getCartasJugador(j);
    }

    public ManoPoker getManoActual() {
        return manoActual;
    }

    public static class EventoPartidaPoker {

        private EventosPartidaPoker evento;
        private String descripcion;
        private Jugador jugador;

        protected EventoPartidaPoker(EventosPartidaPoker evento) {
            this.evento = evento;
        }

        protected EventoPartidaPoker(EventosPartidaPoker evento, String descripcion) {
            this.evento = evento;
            this.descripcion = descripcion;
        }

        protected EventoPartidaPoker(EventosPartidaPoker evento, String descripcion, Jugador j) {
            this.evento = evento;
            this.descripcion = descripcion;
            this.jugador = j;
        }

        public EventosPartidaPoker getEvento() {
            return evento;
        }

        public String getDescripcion() {
            return descripcion != null ? descripcion : evento.toString();
        }

        public Jugador getJugador() {
            return jugador;
        }

        @Override
        public String toString() {
            return "EventoPartidaPoker{" + "evento=" + evento + ", descripcion=" + descripcion + ", jugador=" + jugador + '}';
        }

    }
}
