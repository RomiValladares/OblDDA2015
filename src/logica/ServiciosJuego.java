/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;

/**
 *
 * @author Romi
 */
public interface ServiciosJuego {

    public ArrayList<JuegoCasino> getJuegos();

    public double getGanancias();

    public void setGanancias(double ganancias);
}
