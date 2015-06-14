package logica;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

//Observable: lo observa la interfaz de usuario
//Observer: observa los subsistemas
public class Fachada extends Observable implements Observer {

    private static Fachada instancia;
    private final SsUsuarios ssUsuarios;
    private final SsJuegos ssJuegos;

    // <editor-fold defaultstate="collapsed" desc="SINGLETON">  
    private Fachada() {
        ssUsuarios = SsUsuarios.getInstancia();
        ssJuegos = SsJuegos.getInstancia();
        ssJuegos.addObserver(this);
    }

    public static Fachada getInstancia() {
        if (instancia == null) {
            instancia = new Fachada();
        }
        return instancia;
    }
    // </editor-fold> 

    public Usuario ingresar(String nombreUsuario, String contrasena)
            throws Exception {
        return ssUsuarios.ingresar(nombreUsuario, contrasena);
    }

    // <editor-fold defaultstate="collapsed" desc="SS JUEGOS">  
    public ArrayList<JuegoCasino> getJuegos() {
        return ssJuegos.getJuegos();
    }

    public boolean hayJuegosActivos() {
        return ssJuegos.hayJuegoActivos();
    }

    public double getGanancias() {
        return ssJuegos.getGanancias();
    }

    /**
     *
     * @param codigoJuego juego por el cual se van a filtrar las partidas, o -1
     * si se quieren obtener todas
     */
    public ArrayList<DatosPartidaJuegoCasino> getDatosPartidas(JuegoCasino codigoJuego) {
        return ssJuegos.getDatosPartidas(codigoJuego);
    }
    // </editor-fold> 

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }

}
