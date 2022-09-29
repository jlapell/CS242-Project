package main;
import data.ClackData;
import data.FileClackData;

public class ClackServer {
    protected int port;
    protected boolean closeConnection;
    protected ClackData dataToReceiveFromClient;
    protected ClackData dataToSendToClient;

    public ClackServer(int port){
        this.port = port;
        dataToSendToClient = null;
        dataToReceiveFromClient = null;
    }

    public ClackServer(){
        this(7000); // Needs to be constant?
    }

    public void start(){

    }

    public void receiveData(){

    }

    public void sendData(){

    }

    public int getPort(){
        return port;
    }

    public int hashCode(){
        return port * dataToReceiveFromClient.hashCode() * dataToSendToClient.hashCode();
    }

    public boolean equals(Object other){
        ClackServer otherData = (ClackServer)other;
        return this.port == otherData.port &&
                this.closeConnection == otherData.closeConnection &&
                this.dataToReceiveFromClient == otherData.dataToReceiveFromClient &&
                this.dataToSendToClient == otherData.dataToSendToClient;
    }

    public String toString(){
        return "The data to receive from client is: " + this.dataToReceiveFromClient + "\n" +
                "The data to send to client is: " + this.dataToSendToClient + "\n";
    }
}
