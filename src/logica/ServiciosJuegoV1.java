/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import Persistencia.ManejadorBD;
import java.util.ArrayList;
import logica.FabricadorJuegosCasino.CodigosJuego;
import logica.poker.PartidaPoker;
import persistencia.Parametro;
import persistencia.persistentes.ParametrosPersistente;
import persistencia.persistentes.PartidaJuegoPersistente;
import persistencia.persistentes.PartidaPokerPersistente;

/**
 *
 * @author Romi
 */
public class ServiciosJuegoV1 implements ServiciosJuego {

    private final String stringConexion = "jdbc:mysql://localhost/casino?user=root&password=root";
    //TODO conectarme a la BD y eso
    ArrayList<PartidaJuegoCasino> partidas = new ArrayList<>();

    private ManejadorBD manejador = ManejadorBD.getInstancia();

    @Override
    public ArrayList<JuegoCasino> getJuegos() {
        return FabricadorJuegosCasino.getJuegosCasino();
    }

    @Override
    public double getGanancias() {
        ParametrosPersistente persistenteGanancias = new ParametrosPersistente(new Parametro("ganancias", ""));

        manejador.conectar(stringConexion);
        manejador.leerPersistente(persistenteGanancias);

        return (double) persistenteGanancias.getObjeto();
    }

    @Override
    public void setGanancias(double ganancias) {
        ParametrosPersistente persistenteGanancias = new ParametrosPersistente(new Parametro("ganancias", ganancias));

        manejador.conectar(stringConexion);
        manejador.modificar(persistenteGanancias);
    }

    @Override
    public void guardarPartida(PartidaJuegoCasino partida) {
        partidas.add(partida);
    }

    @Override
    public ArrayList<PartidaJuegoCasino> getPartidas(CodigosJuego codigoJuego) {
        PartidaJuegoPersistente persistente;
        if (codigoJuego == null) {
            persistente = new PartidaJuegoPersistente(new PartidaJuegoCasino());
        } else {
            persistente = getPartidaPersistente(codigoJuego);
        }
        manejador.conectar(stringConexion);
        return manejador.obtenerTodos(persistente);
    }

    @Override
    public void guardar(PartidaJuegoCasino p) {
        PartidaJuegoPersistente persistente = getPartidaPersistente(p);

        manejador.conectar(stringConexion);
        manejador.agregar(persistente);
    }

    @Override
    public void modificar(PartidaJuegoCasino p) {
        PartidaJuegoPersistente persistente = getPartidaPersistente(p);

        manejador.conectar(stringConexion);
        manejador.modificar(persistente);
    }

    /**
     * Metodo fabrica que devuelve el persistente segun la clase de p
     *
     * @param p partida para la que se crea el persistente
     * @return persistente para p
     */
    private PartidaJuegoPersistente getPartidaPersistente(PartidaJuegoCasino p) {
        if (p == null) {
            return new PartidaJuegoPersistente(p);
        }
        if (p.getClass().equals(PartidaPoker.class)) {
            return new PartidaPokerPersistente((PartidaPoker) p);
        }
        return new PartidaJuegoPersistente(p);
    }

    private PartidaJuegoPersistente getPartidaPersistente(CodigosJuego codigoJuego) {
        if (codigoJuego == CodigosJuego.POKER) {
            return new PartidaPokerPersistente(new PartidaPoker(0));
        }
        return new PartidaJuegoPersistente(new PartidaJuegoCasino());
    }

}
