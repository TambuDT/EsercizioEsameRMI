package com.company;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ProduttoreMessaggi extends Thread {
    private int idProd=10;
    public ProduttoreMessaggi(){

    }

    public void run()  {

        int[] arrayDestinatari=new int[5];
        /*for(int i=0;i<=5;i++){
            arrayDestinatari[i]=(int) Math.random()*5;
        }*/
        arrayDestinatari[0]=(int) Math.random()*5;

        Registry registro= null;
        try {
            registro = LocateRegistry.getRegistry("localhost",8000);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        GestoreMessaggiInterface g=null;
        try {
           g=(GestoreMessaggiInterface) registro.lookup("server");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        Msg messaggio=new Msg(idProd,arrayDestinatari,"fanculo");
        try {
            g.send(messaggio);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (GestorePieno e) {
            e.printStackTrace();
        }
    }
}
