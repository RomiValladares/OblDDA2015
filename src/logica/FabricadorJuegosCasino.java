package logica;

import logica.poker.JuegoPoker;
import java.util.ArrayList;

public class FabricadorJuegosCasino {

    public static ArrayList<JuegoCasino> getJuegosCasino() {
        ArrayList<JuegoCasino> juegos = new ArrayList<>();
        juegos.add(new JuegoPoker());
        return juegos;
    }
}
