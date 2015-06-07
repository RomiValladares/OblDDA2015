/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.persistentes;

import Persistencia.Persistente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logica.PartidaJuegoCasino;

/**
 *
 * @author Romi
 */
public abstract class PartidaJuegoPersistente implements Persistente {

    private PartidaJuegoCasino u;

    public PartidaJuegoPersistente(PartidaJuegoCasino u) {
        this.u = u;
    }

    @Override
    public ArrayList<String> getInsertSql() {
        ArrayList r = new ArrayList();
        r.add("INSERT INTO Partidas(oid,numero,duracion,total_apostado,codigo_juego)"
                + "VALUES(" + getOid() + "," + u.getNumeroPartida() + ","
                + u.getDuracion() + ", " + u.getTotalApostado() + ", " + getCodigoJuego() + ")");
        return r;
    }

    @Override
    public void setOid(int oid) {
        u.setOid(oid);
    }

    @Override
    public String getUpdateSql() {
        return "UPDATE Partidas SET numero=" + u.getNumeroPartida()
                + ", duracion=" + u.getDuracion()
                + ", total_apostado=" + u.getTotalApostado() + " WHERE oid=" + getOid();
    }

    @Override
    public String getDeleteSql() {
        return "DELETE FROM Partidas " + " WHERE oid=" + getOid();
    }

    @Override
    public String getSelectSql() {
        String r = "SELECT * from Partidas";
        if (getOid() != 0) {
            r += " WHERE oid=" + getOid();
        }
        return r;
    }

    @Override
    public int getOid() {
        if (u == null) {
            return 0;
        } else {
            return u.getOid();
        }
    }

    @Override
    public void leer(ResultSet rs) throws SQLException {
        u.setOid(rs.getInt("oid"));
        u.setNumero(rs.getInt("numero"));
        u.setDuracion(rs.getLong("duracion"));
        u.setTotalApostado(rs.getDouble("saldo"));
    }

    @Override
    public Object getObjeto() {
        return u;
    }

    @Override
    public void limpiar() {

    }

    protected abstract int getCodigoJuego();

}
