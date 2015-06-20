/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.rmi.RemoteException;
import java.util.ArrayList;
import observableremoto.ObservableRemoto;

/**
 *
 * @author Romi
 */
public interface Fachada extends ObservableRemoto {

    /**
     *
     * @param codigoJuego juego por el cual se van a filtrar las partidas, o -1
     * si se quieren obtener todas
     */
    ArrayList<DatosPartidaJuegoCasino> getDatosPartidas(JuegoCasinoV1 codigoJuego) throws RemoteException;
    // </editor-fold>

    double getGanancias() throws RemoteException;

    // <editor-fold defaultstate="collapsed" desc="SS JUEGOS">
    ArrayList<JuegoCasinoV1> getJuegos() throws RemoteException;

    boolean hayJuegosActivos() throws RemoteException;

    Usuario ingresar(String nombreUsuario, String contrasena) throws RemoteException, Exception;

}
