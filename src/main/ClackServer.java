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
    public int hashCode(){
        return port * dataToReceiveFromClient.hashCode() * dataToSendToClient.hashCode();
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
                "The data to send to client is: " + this.dataToSendToClient + "\n";
    }
}
