package main;

public class ClackServer {
    protected int port;
    protected boolean closeConnection;
    protected ClackData dataToReceiveFromClient;
    protected ClackData dataToSendToClient;
    protected static final int CONSTANT_LISTUSERS = 0;
    protected static final int CONSTANT_LOGOUT = 1;
    protected static final int CONSTANT_SENDMESSAGE = 2;
    protected static final int CONSTANT_SENDFILE = 3;

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

    /*public int hashCode(){

    }

    public boolean equals(){

    }*/

    public String toString(){
        return "The data to receive from client is: " + this.dataToReceiveFromClient + "\n" +
                "The data to send to client is: " + this.dataToSendToClient + "\n";
    }
}
