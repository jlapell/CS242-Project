package main;
import data.ClackData;
import data.FileClackData;

/**
 * ClackServer represents the clack server
 */
public class ClackServer {
    protected int port;
    protected boolean closeConnection;
    protected ClackData dataToReceiveFromClient;
    protected ClackData dataToSendToClient;
    private static final int CONSTANT_DEFAULTPORT = 7000;

    /**
     * Constructor that sets port number
     * @param port
     */
    public ClackServer(int port){
        this.port = port;
        dataToSendToClient = null;
        dataToReceiveFromClient = null;
    }

    /**
     * Default construct for ClackServer
     */
    public ClackServer(){
        this(CONSTANT_DEFAULTPORT);
    }

    /**
     * Declaration of start method
     */
    public void start(){

    }

    /**
     * Declaration of receiveData method
     */
    public void receiveData(){

    }

    /**
     * Declaration of sendData method
     */
    public void sendData(){

    }

    /**
     * Method that returns the port
     * @return port
     */
    public int getPort(){
        return port;
    }

    /**
     * hashCode override
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + port;
        hash = 31 * hash + (dataToReceiveFromClient == null ? 0 : dataToReceiveFromClient.hashCode());
        hash = 31 * hash + (dataToSendToClient == null ? 0 : dataToSendToClient.hashCode());
        hash = 31 * hash + (closeConnection ? 0 : 1);
        return hash;
    }

    /**
     * equals override
     * @param other
     */
    public boolean equals(Object other){
        if(other == null)
            return false;
        if(!(other instanceof ClackServer))
            return false;
        ClackServer otherData = (ClackServer)other;
        return this.port == otherData.port &&
                this.closeConnection == otherData.closeConnection &&
                this.dataToReceiveFromClient == otherData.dataToReceiveFromClient &&
                this.dataToSendToClient == otherData.dataToSendToClient;
    }

    /**
     * toString override
     * @return
     */
    public String toString(){
        return "The data to receive from client is: " + this.dataToReceiveFromClient + "\n" +
                "The data to send to client is: " + this.dataToSendToClient + "\n" +
                "The connection status is: " + (this.closeConnection ? "Closed" : "Open") + "\n" +
                "The port number is: " + this.port + "\n";
    }
}
