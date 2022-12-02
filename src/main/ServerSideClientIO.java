package main;

import data.ClackData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSideClientIO implements Runnable{
    private boolean closeConnection;
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;
    private ClackServer server;
    private Socket clientSocket;

    public ServerSideClientIO(ClackServer server, Socket clientSocket){
        this.server = server;
        this.clientSocket = clientSocket;
        closeConnection = false;
        dataToReceiveFromClient = null;
        dataToSendToClient = null;
        inFromClient = null;
        outToClient = null;
    }

    @Override
    public void run(){
        try{
            outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
            inFromClient = new ObjectInputStream(clientSocket.getInputStream());
            while(!closeConnection){
                this.receiveData();
                this.server.broadcast(dataToReceiveFromClient);
            }
        } catch (SecurityException se) {
            System.err.println("Security exception occurred");
        } catch (IllegalArgumentException iae) {
            System.err.println("Illegal argument exception occurred");
        } catch (IOException ioe) {
            System.err.println("IO exception occurred");
        }
    }

    public void receiveData(){
        try {
            dataToReceiveFromClient = (ClackData) inFromClient.readObject();
            if (dataToReceiveFromClient.getType() == ClackData.CONSTANT_LOGOUT) {
                server.remove(this);
                closeConnection = true;
            }
            if (dataToReceiveFromClient.getType() == ClackData.CONSTANT_LISTUSERS) {
                server.listUser();
            }
        }catch (IOException ioe) {
            System.err.println("IO exception occurred");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Class not found exception occurred");
        }
    }

    public void sendData(){
        try {
            outToClient.writeObject(dataToSendToClient);
        } catch (IOException ioe) {
            System.err.println("IO exception occurred");
        }
    }

    public void setDataToSendToClient(ClackData dataToSendToClient){
        this.dataToSendToClient = dataToSendToClient;
    }
}
