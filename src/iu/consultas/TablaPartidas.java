/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iu.consultas;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logica.JuegoCasino;
import logica.PartidaJuegoCasino;

/**
 *
 * @author Romi
 */
public class TablaPartidas extends javax.swing.JPanel {

    /**
     * Creates new form TablaPartidas
     */
    public TablaPartidas() {
        initComponents();
        tablaPartidas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                lblParticipantesPartida.setText(tablaPartidas.getValueAt(tablaPartidas.getSelectedRow(), 0).toString());
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblPartidasJuego = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPartidas = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        lblParticipantesPartida = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaParticipantes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        comboJuegos = new javax.swing.JComboBox();

        lblPartidasJuego.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPartidasJuego.setText("Partidas juego {juego}");

        tablaPartidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Numero", "Comienzo", "Final", "Duracion (min)", "Total Apostado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaPartidas);

        lblParticipantesPartida.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblParticipantesPartida.setText("Participantes partida {n}");

        tablaParticipantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablaParticipantes);

        jLabel2.setText("Seleccione un juego:");

        comboJuegos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboJuegos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboJuegosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lblPartidasJuego, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblParticipantesPartida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(104, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboJuegos, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboJuegos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(lblPartidasJuego)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblParticipantesPartida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboJuegosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboJuegosActionPerformed
        // TODO add your handling code here:
        lblPartidasJuego.setText("Partidas Juego " + comboJuegos.getSelectedItem());
    }//GEN-LAST:event_comboJuegosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboJuegos;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblParticipantesPartida;
    private javax.swing.JLabel lblPartidasJuego;
    private javax.swing.JTable tablaParticipantes;
    private javax.swing.JTable tablaPartidas;
    // End of variables declaration//GEN-END:variables

    Object getJuegoSeleccionado() {
        return comboJuegos.getSelectedItem();
    }

    void actualizarJuegos(ArrayList<JuegoCasino> juegos) {
        comboJuegos.setModel(new DefaultComboBoxModel(juegos.toArray()));
        lblPartidasJuego.setText("Partidas Juego " + comboJuegos.getSelectedItem());
    }

    private final String[] columnasTablaPartidas = {"Numero", "Comienzo", "Final", "Duracion (mins)", "Total Apostado"};

    void actualizarPartidas(ArrayList<PartidaJuegoCasino> partidas) {
        Object[][] objs = new Object[partidas.size()][columnasTablaPartidas.length];
        int fila = 0;
        for (PartidaJuegoCasino partidaJuegoCasino : partidas) {
            String[] datos = new String[]{"" + partidaJuegoCasino.getNumeroPartida(),
                "" + partidaJuegoCasino.getComienzo(),
                "" + partidaJuegoCasino.getFinal(),
                "" + partidaJuegoCasino.getDuracion(),
                "" + partidaJuegoCasino.getTotalApostado()};
            objs[fila++] = armarFila(columnasTablaPartidas.length, datos);
//            objs[fila][col++] = partidaJuegoCasino.getNumeroPartida();
//            objs[fila][col++] = partidaJuegoCasino.getComienzo();
//            objs[fila][col++] = partidaJuegoCasino.getFinal();
//            objs[fila][col++] = partidaJuegoCasino.getDuracion();
//            objs[fila][col++] = partidaJuegoCasino.getTotalApostado();
        }
        tablaPartidas.setModel(new DefaultTableModel(objs, columnasTablaPartidas));
    }

    private Object[] armarFila(int cols, String[] datos) {
        Object[] fila = new Object[cols];
        for (int i = 0; i < cols; i++) {
            fila[i] = datos[i];
            i++;
        }
        return fila;
    }
}
