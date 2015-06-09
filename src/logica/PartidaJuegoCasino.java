/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.Date;
import java.util.Observable;

/**
 *
 * @author Romi
 */
public abstract class PartidaJuegoCasino extends Observable {

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

    /*
     * @return duracion desde que empieza hasta que se obtiene el ganador, en segundos
     */
    public long getDuracion() {
        if (tiempoInicial == null) {
            return 0;
        }
        if (tiempoFinal == null) {
            return tiempoInicial.getTime() - new Date().getTime();
        }
        return tiempoInicial.getTime() - tiempoFinal.getTime();
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
    }

    protected void comenzar() {
        comenzada = true;

        //comenzar timer
        tiempoInicial = new Date();
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

    public void setFinal(java.sql.Date date) {
        tiempoFinal = date;
    }

    public void setComienzo(java.sql.Date date) {
        tiempoInicial = date;
    }

}
