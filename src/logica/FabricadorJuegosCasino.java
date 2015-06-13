package logica;

import logica.poker.JuegoPoker;
import java.util.ArrayList;

public class FabricadorJuegosCasino {

    public enum CodigosJuego {

        POKER(5);
        private int codigo;

        private CodigosJuego(int value) {
            this.codigo = value;
        }

        public int getCodigo() {
            return codigo;
        }
    }

    public static ArrayList<JuegoCasino> getJuegosCasino() {
        ArrayList<JuegoCasino> juegos = new ArrayList<>();
        juegos.add(new JuegoPoker());
        return juegos;
    }
}
