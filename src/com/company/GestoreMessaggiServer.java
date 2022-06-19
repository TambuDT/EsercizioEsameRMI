package com.company;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

public class GestoreMessaggiServer extends UnicastRemoteObject implements GestoreMessaggiInterface  {

    LinkedList<String> listaUtenti=new LinkedList<String>();
    LinkedList<Msg> listaMessaggi=new LinkedList<Msg>();


    protected GestoreMessaggiServer() throws RemoteException {
    }

    @Override
    public synchronized void signUp(String id) throws RemoteException, GiàRegistrato {
        if(listaUtenti.contains(id))throw new GiàRegistrato();
        else{
            listaUtenti.add(id);
            System.out.println(listaUtenti);
        }
    }

    @Override
    public synchronized void send(Msg m) throws RemoteException, GestorePieno {
        if(listaMessaggi.size()<=10){
            listaMessaggi.add(m);
            System.out.println(listaMessaggi);
        }
        else{
            throw new GestorePieno();
        }
    }

    @Override
    public synchronized Msg receive(String id) throws RemoteException, InterruptedException, ConsumatoreSconosciuto {
        if(!listaUtenti.contains(id))throw new ConsumatoreSconosciuto();
        return null;
    }

    @Override
    public synchronized boolean anyMessage(String id) throws RemoteException, ConsumatoreSconosciuto {
        if(!listaUtenti.contains(id))throw new ConsumatoreSconosciuto();
        for(int i=0;i<=listaMessaggi.size();i++){
            if(listaMessaggi.get(i).to.equals(id)){
            return true;
            }
        }
        return false;
    }
    public static void main(String args[]) throws RemoteException{
        Registry registro= LocateRegistry.createRegistry(8000);
        registro.rebind("server",new GestoreMessaggiServer());
        System.out.println("il server è pronto");
    }
}
