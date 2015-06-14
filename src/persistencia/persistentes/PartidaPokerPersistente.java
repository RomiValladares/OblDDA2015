/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.persistentes;

import logica.DatosPartidaJuegoCasino;

/**
 *
 * @author Romi
 */
public class PartidaPokerPersistente extends PartidaJuegoPersistente {

    public PartidaPokerPersistente(DatosPartidaJuegoCasino u) {
        super(u);
    }

    @Override
    protected int getCodigoJuego() {
        return 5;//el codigo del Poker
    }

}
