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
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Romi Permite ABM de datos en la tabla Parametros
 */
public class ParametrosPersistente implements Persistencia.Persistente {

    private String nombre, valor;

    public ParametrosPersistente(String nombre, String valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    @Override
    public ArrayList<String> getInsertSql() {
        ArrayList r = new ArrayList();
        r.add("INSERT INTO Parametros(nombre,valor)"
                + "VALUES('" + nombre + ",'" + valor + "')");
        return r;
    }

    @Override
    public void setOid(int oid) {
        throw new NotImplementedException();
    }

    @Override
    public String getUpdateSql() {
        return "UPDATE Parametros SET nombre='" + nombre
                + "', valor='" + valor
                + "'";
    }

    @Override
    public String getDeleteSql() {
        return "DELETE FROM Parametros " + " WHERE nombre='" + nombre + "'";
    }

    @Override
    public String getSelectSql() {
        String r = "SELECT * from Parametros";
        if (nombre != null && !nombre.equals("")) {
            r += " WHERE nombre='" + nombre + "'";
        }
        return r;
    }

    @Override
    public int getOid() {
        return 0;
    }

    @Override
    public void leer(ResultSet rs) throws SQLException {
        nombre = rs.getString("nombre");
        valor = rs.getString("valor");
    }

    @Override
    public Persistente crearNuevo() {
        return new ParametrosPersistente(null, null);
    }

    @Override
    public Object getObjeto() {
        throw new NotImplementedException();
    }

    @Override
    public void limpiar() {

    }

}
