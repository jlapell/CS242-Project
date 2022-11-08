package main;
import com.sun.security.ntlm.Server;
import data.ClackData;
import data.FileClackData;

import java.io.*;
import java.net.*;
/**
 * ClackServer represents the clack server
 */
public class ClackServer {
    protected int port;
    protected boolean closeConnection;
    protected ClackData dataToReceiveFromClient;
    protected ClackData dataToSendToClient;
    private static final int CONSTANT_DEFAULTPORT = 7000;

    private ObjectInputStream inFromClient;

    private ObjectOutputStream outToClient;

    /**
     * Constructor that sets port number
     *
     * @param port
     */
    public ClackServer(int port) {
        if (port < 1024)
            throw new IllegalArgumentException("Port cannot be less than 1024.");
        this.port = port;
        dataToSendToClient = null;
        dataToReceiveFromClient = null;
    }

    /**
     * Default construct for ClackServer
     */
    public ClackServer() {
        this(CONSTANT_DEFAULTPORT);
    }

    /**
     * Declaration of start method
     */
    public void start() {
        try {
            ServerSocket sskt = new ServerSocket(CONSTANT_DEFAULTPORT);
            Socket clientSkt = sskt.accept();
            inFromClient = new ObjectInputStream((clientSkt.getInputStream()));
            outToClient = new ObjectOutputStream(clientSkt.getOutputStream());
            while (!closeConnection) {
                receiveData();
                dataToSendToClient = dataToReceiveFromClient;
                sendData();
            }
            outToClient.close();
            inFromClient.close();
            clientSkt.close();
            sskt.close();
        } catch (SecurityException se) {
            System.err.println("Security exception occurred");
        } catch (IllegalArgumentException iae) {
            System.err.println("Illegal argument exception occurred");
        } catch (IOException ioe) {
            System.err.println("IO exception occurred");
        }
    }

    /**
     * Declaration of receiveData method
     */
    public void receiveData() {
        try {
            if (something)
                closeConnection = true;
            dataToReceiveFromClient = (ClackData) inFromClient.readObject();
        } catch (IOException ioe) {
            System.err.println("IO exception occurred");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Class not found exception occurred");
        }
    }

    /**
     * Declaration of sendData method
     */
    public void sendData() {
        try {
            outToClient.writeObject(dataToSendToClient);
        } catch (IOException ioe) {
            System.err.println("IO exception occurred");
        }
    }

    /**
     * Method that returns the port
     *
     * @return port
     */
    public int getPort() {
        return port;
    }

    /**
     * hashCode override
     *
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
     *
     * @param other
     */
    public boolean equals(Object other) {
        if (other == null)
            return false;
        if (!(other instanceof ClackServer))
            return false;
        ClackServer otherData = (ClackServer) other;
        return this.port == otherData.port &&
                this.closeConnection == otherData.closeConnection &&
                this.dataToReceiveFromClient == otherData.dataToReceiveFromClient &&
                this.dataToSendToClient == otherData.dataToSendToClient;
    }

    /**
     * toString override
     *
     * @return
     */
    public String toString() {
        return "The data to receive from client is: " + this.dataToReceiveFromClient + "\n" +
                "The data to send to client is: " + this.dataToSendToClient + "\n" +
                "The connection status is: " + (this.closeConnection ? "Closed" : "Open") + "\n" +
                "The port number is: " + this.port + "\n";
    }

    public static void main(String args[]) {
            ClackServer server;
            if (args.length == 0)
                server = new ClackServer();
            else
                server = new ClackServer(Integer.parseInt(args[0]));
    }
}