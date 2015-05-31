/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iu.poker;

import iu.FrameJuegoCasino;
import iu.FrameJuegos;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import logica.Jugador;
import logica.PartidaJuegoCasino;
import logica.poker.CartaPoker;
import logica.poker.ManoPoker.EventoManoPoker;
import logica.poker.ManoPoker.EventosManoPoker;
import static logica.poker.ManoPoker.EventosManoPoker.FINALIZO_MANO;
import logica.poker.PartidaPoker;
import logica.poker.PartidaPoker.EventoPartidaPoker;
import logica.poker.PartidaPoker.EventosPartidaPoker;
import static logica.poker.PartidaPoker.EventosPartidaPoker.JUGADOR_SALDO_INSUFICIENTE;

/**
 *
 * @author Romi es observer porque observa a la partida
 */
public class FramePoker extends FrameJuegoCasino implements Observer {

    private PartidaPoker partida;
    //boolean para controlar que este o no en la partida (porque ya no tiene saldo)
    private boolean estaEnLaPartida = true;
    //chequea que ya haya comenzado la partida
    private boolean ingresoAPartida;

    /**
     * Creates new form FramePoker
     */
    public FramePoker(PartidaJuegoCasino partida, Jugador jugador) {
        setImagenFondo("src/imgs/poker_fondo.jpg");
        initComponents();
        setJugador(jugador);

        try {
            this.partida = (PartidaPoker) partida;
            this.partida.addObserver(this);
            panelDatosJugador1.setJugador(getJugador());
            panelDatosPartida1.setPartida(this.partida);

            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

            actualizarUI();
        } catch (Exception ex) {
            Logger.getLogger(FramePoker.class.getName()).log(Level.INFO, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void actualizarUI() {
        try {
            //los paneles deberian actualizarse solos
            //panelDatosPartida1.actualizarUI();
            panelAccionesJugador.setDialogVisible(false);
            if (partida.isComenzada()) {
                panelAccionesJugador.mostrarPanelApuesta(partida.getManoActual().getApuestaMaxima());
                panelEsperandoInicio.setVisible(false);
                panelJuegoPoker1.setVisible(true);
                panelAccionesJugador.setVisible(true);

                if (!ingresoAPartida) {
                    actualizarCartas();
                    ingresoAPartida = true;
                }
            } else {
                panelAccionesJugador.setVisible(false);

                panelJuegoPoker1.setVisible(false);
                panelEsperandoInicio.setVisible(true);
                lblJugadoresRestantes.setText(partida.getCantidadMaxJugadores()
                        - partida.getJugadores().size() + "");
            }
        } catch (Exception ex) {
            Logger.getLogger(FramePoker.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDatosPartida1 = new iu.poker.PanelDatosPartida();
        panelAccionesJugador = new iu.poker.PanelAccionesJugador(this);
        panelEsperandoInicio = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblJugadoresRestantes = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelDatosJugador1 = new iu.poker.PanelDatosJugador();
        panelJuegoPoker1 = new iu.poker.PanelCartasPoker();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(814, 503));
        setMinimumSize(new java.awt.Dimension(814, 503));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelEsperandoInicio.setMaximumSize(new java.awt.Dimension(153, 36));
        panelEsperandoInicio.setMinimumSize(new java.awt.Dimension(153, 36));

        jLabel1.setText("Esperando");

        lblJugadoresRestantes.setText("4");

        jLabel2.setText("jugador(es)");

        javax.swing.GroupLayout panelEsperandoInicioLayout = new javax.swing.GroupLayout(panelEsperandoInicio);
        panelEsperandoInicio.setLayout(panelEsperandoInicioLayout);
        panelEsperandoInicioLayout.setHorizontalGroup(
            panelEsperandoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEsperandoInicioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblJugadoresRestantes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(451, Short.MAX_VALUE))
        );
        panelEsperandoInicioLayout.setVerticalGroup(
            panelEsperandoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEsperandoInicioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEsperandoInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblJugadoresRestantes)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelJuegoPoker1.setMaximumSize(new java.awt.Dimension(594, 211));
        panelJuegoPoker1.setMinimumSize(new java.awt.Dimension(594, 211));
        panelJuegoPoker1.setOpaque(false);
        panelJuegoPoker1.setPreferredSize(new java.awt.Dimension(594, 211));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelDatosJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelEsperandoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelJuegoPoker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelAccionesJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(panelDatosPartida1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelDatosJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(panelEsperandoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11)
                        .addComponent(panelJuegoPoker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(panelAccionesJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelDatosPartida1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (estaEnLaPartida) {
            int dialogResult = JOptionPane.showConfirmDialog(this, "Salir del juego?", null, JOptionPane.YES_NO_OPTION);
            if (dialogResult == 0) {
                salirDelJuego();
            }
        } else {
            setVisible(false);
            dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    protected void setModoDescartarse(boolean set) {
        if (set) {
            panelJuegoPoker1.setModoDescartarse(this);
            panelAccionesJugador.setVisible(false);
        } else {
            panelJuegoPoker1.setModoDescartarse(set);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Variables declaration - do not modify">     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblJugadoresRestantes;
    private iu.poker.PanelAccionesJugador panelAccionesJugador;
    private iu.poker.PanelDatosJugador panelDatosJugador1;
    private iu.poker.PanelDatosPartida panelDatosPartida1;
    private javax.swing.JPanel panelEsperandoInicio;
    private iu.poker.PanelCartasPoker panelJuegoPoker1;
    // End of variables declaration//GEN-END:variables
// </editor-fold> 

    @Override
    public void update(Observable o, Object arg) {
        if (o.getClass().equals(PartidaPoker.class)) {
            panelDatosJugador1.actualizar();
            if (arg instanceof EventoManoPoker) {
                EventoManoPoker evento = (EventoManoPoker) arg;
                actualizar(evento);
            } else if (arg instanceof EventoPartidaPoker) {
                EventoPartidaPoker evento = (EventoPartidaPoker) arg;
                actualizar(evento);
            } else if (arg instanceof EventosPartidaPoker) {
                EventosPartidaPoker evento = (EventosPartidaPoker) arg;
                actualizar(evento);
            } else if (arg == null) {
                Logger.getLogger(FramePoker.class.getName()).log(Level.INFO, "FramePoker update " + (arg != null ? arg.getClass() : "") + arg);
                actualizarUI();
            }
        }
    }

    private void actualizar(EventosPartidaPoker evento) {
        if (evento.equals(EventosPartidaPoker.SALIDA_JUGADOR)) {
            if (partida.getManoActual().getApuesta() == null) {
                panelAccionesJugador.mostrarPanelApuesta(partida.getManoActual().getApuestaMaxima());
            }
        } else {
            actualizarUI();
        }
    }

    private void actualizar(EventoManoPoker evento) {
        Logger.getLogger(FramePoker.class.getName()).log(Level.INFO, "FramePoker update EventoManoPoker " + evento);
        panelDatosPartida1.addEvento(evento, partida);
        //TODO revisar cuando se termina la partida y eso
        if (evento.getEvento() != EventosManoPoker.GANADOR) {
            setAccionJugador(evento.getEvento());
        } else {
            panelAccionesJugador.mostrarPanelDialog(evento, partida.getManoActual().getGanadorYFigura(), partida.getManoActual().getApuesta().getTotalJugadores());
        }
        if (evento.getEvento() == FINALIZO_MANO) {
            if (partida.getManoActual().getGanadorYFigura() == null) {
                //todos pasaron y no hubo ganador
                panelAccionesJugador.mostrarPanelDialog(evento, partida.getManoActual().getGanadorYFigura(), 0);
            } else {
                panelAccionesJugador.mostrarPanelDialog(evento, partida.getManoActual().getGanadorYFigura(), partida.getManoActual().getApuesta().getTotalJugadores());
            }
            checkPuedeJugar();
        }
    }

    private void actualizar(EventoPartidaPoker evento) {
        Logger.getLogger(FramePoker.class.getName()).log(Level.INFO, "FramePoker update EventoPartidaPoker " + evento);
        if (!evento.getEvento().equals(JUGADOR_SALDO_INSUFICIENTE)) {
            if (evento.getEvento().equals(EventosPartidaPoker.FINALIZO_PARTIDA)) {
                mostrarDialogoFinPartida(evento);
            } else if (evento.getEvento().equals(EventosPartidaPoker.SALIDA_JUGADOR)) {
                if (evento.getJugador().equals(getJugador())) {
                    //I should take a look at this
                    //cerrar();
                } else if (partida.getManoActual() != null && partida.getManoActual().getApuesta() == null) {
                    panelAccionesJugador.mostrarPanelApuesta(partida.getManoActual().getApuestaMaxima());
                } else {
                    lblJugadoresRestantes.setText(partida.getCantidadMaxJugadores()
                            - partida.getJugadores().size() + "");
                }
            } else if (evento.getJugador().equals(getJugador())) {
                panelAccionesJugador.mostrarPanelDialog(evento);
            } else {
                actualizarUI();
            }
        }
    }

    private void salirDelJuego() {
        try {
            partida.retirarse(getJugador());

            cerrar();
        } catch (Exception ex) {
            Logger.getLogger(FramePoker.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    //reemplaza el panelAccionJugador con un panel para realizar una apuesta/pasar, esperando, etc
    private void setAccionJugador(EventosManoPoker accion) {
        if (accion != null) {
            switch (accion) {
                case COMENZO_MANO:
                    panelAccionesJugador.mostrarPanelApuesta(partida.getManoActual().getApuestaMaxima());
                    actualizarCartas();
                    break;
                case NUEVA_APUESTA:
                    panelAccionesJugador.setTextNuevaApuesta(partida.getManoActual().getApuesta() + ". Entrar en la apuesta?");
                    if (partida.getManoActual().getApuesta().getJugador().equals(getJugador())) {
                        setModoDescartarse(true);
                    } else {
                        panelAccionesJugador.setEsperandoVisible(false);
                        panelAccionesJugador.setAceptarApuestaVisible(true);
                    }
                    break;
                case DESCARTAR_CARTAS:
                    setModoDescartarse(true);
                    break;
            }
        }
    }

    /*
     * invocado desde PanelCartasPoker cuando el jugador descarta cartas
     */
    void descartarCartas(ArrayList<CartaPoker> cartasDescartadas) {
        try {
            List<CartaPoker> cartasNuevas = partida.getManoActual().descartarse(getJugador(), cartasDescartadas);

            panelJuegoPoker1.setCartas(cartasNuevas);

            panelAccionesJugador.mostrarFiguraRealizada(partida.getManoActual().getFiguraRealizada(getJugador()));
        } catch (Exception ex) {
            Logger.getLogger(FramePoker.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    protected boolean puedeSeguirEnJuego() {
        try {
            return partida.puedeJugar(getJugador());
        } catch (Exception ex) {
            Logger.getLogger(FramePoker.class.getName()).log(Level.SEVERE, null, ex);

            return false;
        }
    }

    private void actualizarCartas() {
        Logger.getLogger(FramePoker.class.getName()).log(Level.INFO, null, "actualizarCartas para " + getJugador());
        try {
            panelJuegoPoker1.setCartas(partida.getCartasJugador(getJugador()));
        } catch (Exception ex) {
            Logger.getLogger(FramePoker.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    protected void checkPuedeJugar() {
        //se agrega el chequeo !finalizada para que no se borre como observer
        //en el caso que queden solo dos jugadores, ya que los dos tienen que ver el 
        //dialog de ganador en juego
        if (!puedeSeguirEnJuego() && !partida.isFinalizada()) {
            estaEnLaPartida = false;
            partida.deleteObserver(this);
        }
    }

    private void mostrarDialogoFinPartida(EventoPartidaPoker evento) {
        try {
            panelAccionesJugador.mostrarDialogFinPartida(partida, partida.getGanador(), getJugador());
        } catch (Exception ex) {
            Logger.getLogger(FramePoker.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    void pasar() {
        partida.getManoActual().pasar(getJugador());
    }

    void apostar(double apuesta) {
        try {
            partida.getManoActual().apostar(getJugador(), apuesta);
        } catch (Exception ex) {
            Logger.getLogger(FramePoker.class.getName()).log(Level.INFO, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    void aceptarApuesta() {
        try {
            partida.getManoActual().aceptarApuesta(getJugador());
            setModoDescartarse(true);
        } catch (Exception ex) {
            Logger.getLogger(FramePoker.class.getName()).log(Level.INFO, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    void pasarApuesta() {
        partida.getManoActual().pasarApuesta(getJugador());
    }

    void continuarEnJuego(boolean continuar) {
        try {
            partida.continuarEnJuego(getJugador(), continuar);
            if (!continuar) {
                //se llama en el update, con SALIDA_JUGAODR
                cerrar();
            }
        } catch (Exception ex) {
            Logger.getLogger(FramePoker.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(getParent(), ex.getMessage());
        }
    }

    void cerrar() {
        partida.deleteObserver(this);

        setVisible(false);

        FrameJuegos frame = new FrameJuegos(getJugador());
        frame.setVisible(true);

        dispose();
    }

    boolean isModoDescartar() {
        return panelJuegoPoker1.isModoDescartar();
    }

}
