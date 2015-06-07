/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import Persistencia.ManejadorBD;
import java.util.ArrayList;
import persistencia.Parametro;
import persistencia.persistentes.ParametrosPersistente;

/**
 *
 * @author Romi
 */
public class ServiciosJuegoV1 implements ServiciosJuego {

    private ManejadorBD manejador = ManejadorBD.getInstancia();

    @Override
    public ArrayList<JuegoCasino> getJuegos() {
        return FabricadorJuegosCasino.getJuegosCasino();
    }

    @Override
    public double getGanancias() {
        ParametrosPersistente persistenteGanancias = new ParametrosPersistente(new Parametro("ganancias", ""));

        manejador.conectar("");
        manejador.leerPersistente(persistenteGanancias);

        return (double) persistenteGanancias.getObjeto();
    }

    @Override
    public void setGanancias(double ganancias) {
        ParametrosPersistente persistenteGanancias = new ParametrosPersistente(new Parametro("ganancias", ganancias));

        manejador.conectar("");
        manejador.modificar(persistenteGanancias);
    }

}
