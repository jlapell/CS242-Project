package main;
import com.sun.security.ntlm.Server;
import data.ClackData;
import data.FileClackData;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * ClackServer represents the clack server
 */
public class ClackServer {
    protected int port;
    protected boolean closeConnection;
    private static final int CONSTANT_DEFAULTPORT = 7000;
    private ArrayList<ServerSideClientIO> serverSideClientIOArrayList;

    /**
     * Constructor that sets port number
     *
     * @param port
     */
    public ClackServer(int port) {
        if (port < 1024)
            throw new IllegalArgumentException("Port cannot be less than 1024.");
        this.port = port;
        this.serverSideClientIOArrayList = new ArrayList<>();
    }

    /**
     * Default construct for ClackServer
     */
    public ClackServer() {
        this(CONSTANT_DEFAULTPORT);
        serverSideClientIOArrayList = new ArrayList<>();
    }

    /**
     * Does not return anything, gets connections from the client and
     * echoes client data
     */
    public void start() {
        try {
            ServerSocket sskt = new ServerSocket(port);
            int i = 0;
            while (!closeConnection) {
                Socket clientSkt = sskt.accept();
                ServerSideClientIO newUser = new ServerSideClientIO(this, clientSkt);
                serverSideClientIOArrayList.add(newUser);
                Thread thread = new Thread(newUser);
            }
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
     * Method that returns the port
     *
     * @return port
     */
    public int getPort() {
        return port;
    }

    public synchronized void broadcast(ClackData dataToBroadcastToClients){
        for (int i = 0; i < serverSideClientIOArrayList.size(); i++){
            (serverSideClientIOArrayList.get(i)).setDataToSendToClient(dataToBroadcastToClients);
            (serverSideClientIOArrayList.get(i)).sendData();
        }
    }

    public synchronized void remove(ServerSideClientIO serverSideClientToRemove) {
        serverSideClientIOArrayList.remove(serverSideClientToRemove);
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
                this.closeConnection == otherData.closeConnection;
    }

    /**
     * toString override
     *
     * @return
     */
    public String toString() {
        return "The connection status is: " + (this.closeConnection ? "Closed" : "Open") + "\n" +
                "The port number is: " + this.port + "\n";
    }

    /**
     * main method
     * uses command arguments to create a new ClackServer object
     * and starts said object
     * @param args
     */
    public static void main(String[] args) {
        ClackServer server;
        if (args.length == 0)
            server = new ClackServer();
        else
            server = new ClackServer(Integer.parseInt(args[0]));
        server.start();
    }
}

