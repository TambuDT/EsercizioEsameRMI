package com.company;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GestoreMessaggiInterface extends Remote {
    void signUp(String id) throws RemoteException,GiàRegistrato;
    void send(Msg m) throws RemoteException,GestorePieno;
    Msg receive(String id)throws RemoteException,InterruptedException,ConsumatoreSconosciuto;
    boolean anyMessage(String id)throws RemoteException,ConsumatoreSconosciuto;
}
