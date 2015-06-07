/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.persistentes;

import Persistencia.Persistente;
import logica.poker.PartidaPoker;

/**
 *
 * @author Romi
 */
public class PartidaPokerPersistente extends PartidaJuegoPersistente {

    public PartidaPokerPersistente(PartidaPoker u) {
        super(u);
    }

    @Override
    protected int getCodigoJuego() {
        return 5;//el codigo del Poker
    }

    @Override
    public Persistente crearNuevo() {
        return new PartidaPokerPersistente(new PartidaPoker(0));
    }

}
