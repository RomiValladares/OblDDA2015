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

    public void cargarDatosUsuariosPrueba() {
        ssUsuarios.cargarDatosUsuariosPrueba();
    }

    public Jugador ingresar(String nombreUsuario, String contrasena)
            throws Exception {
        return ssUsuarios.ingresar(nombreUsuario, contrasena);
    }

    public ArrayList<JuegoCasino> getJuegos() {
        return ssJuegos.getJuegos();
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }

    public boolean hayJuegosActivos() {
        return ssJuegos.hayJuegoActivos();
    }

    public double getGanancias() {
        return ssJuegos.getGanancias();
    }
}
