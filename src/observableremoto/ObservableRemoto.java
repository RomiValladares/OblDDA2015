/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package observableremoto;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author DocenteFI
 */
public interface ObservableRemoto extends Remote{
    
    public void agregar(ObservadorRemoto obs) throws RemoteException;
    public void quitar(ObservadorRemoto obs) throws RemoteException;
    
    
}
