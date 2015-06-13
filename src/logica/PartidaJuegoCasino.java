/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

/**
 *
 * @author Romi
 */
public class PartidaJuegoCasino extends Observable {

    private int oid;
    private int numeroPartida;

    //para calcular la duracion
    private Date tiempoInicial, tiempoFinal;
    private long duracion;
    private double totalApostado;
    private boolean comenzada = false;
    private boolean finalizada;

    public int getNumeroPartida() {
        return numeroPartida;
    }

    protected void setNumeroPartida(int numeroPartida) {
        this.numeroPartida = numeroPartida;
    }

    /**
     * @return duracion desde que empieza hasta que se obtiene el ganador, en
     * segundos
     */
    public long getDuracion() {
        if (tiempoInicial == null) {
            return 0;
        }
        if (tiempoFinal == null) {
            return new Date().getTime() - tiempoInicial.getTime();
        }
        return tiempoFinal.getTime() - tiempoInicial.getTime();
    }

    public double getTotalApostado() {
        return totalApostado;
    }

    public void setTotalApostado(double t) {
        totalApostado = t;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getOid() {
        return oid;
    }

    public void setNumero(int aInt) {
        numeroPartida = aInt;
    }

    /*
     * solo para cuando se va a leer desde la bd
     */
    public void setDuracion(long aLong) {
        duracion = aLong;
    }

    protected void finalizarPartida() {
        comenzada = false;
        finalizada = true;

        tiempoFinal = new Date();

        obtenerGanador();

        modificar();
    }

    protected void comenzar() {
        comenzada = true;

        //comenzar timer
        tiempoInicial = new Date();

        guardar();
    }

    public boolean isComenzada() {
        return comenzada;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public Date getComienzo() {
        return tiempoInicial;
    }

    public Date getFinal() {
        return tiempoFinal;
    }

    public void setFinal(Date date) {
        tiempoFinal = date;
    }

    public void setComienzo(Date date) {
        tiempoInicial = date;
    }

    protected void guardar() {
        SsJuegos.getInstancia().guardar(this);
    }

    protected void modificar() {
        SsJuegos.getInstancia().modificar(this);
    }

    //METODOS A IMPLEMENTAR EN LAS PARTIDAS
    protected void obtenerGanador() {
    }

    public Jugador getGanador() throws Exception {
        return null;
    }

    public ArrayList<Jugador> getJugadores() {
        return null;
    }

    /**
     * lectura desde BD
     *
     * @param j
     */
    public void agregarJugador(Jugador j) {
    }

    public void setGanador(Jugador j) {
    }

}
