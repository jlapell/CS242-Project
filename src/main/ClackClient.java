package main;

import java.io.IOException;

import data.ClackData;
import data.FileClackData;
import data.MessageClackData;

import java.util.Scanner;

/**
 * ClackClient class representing the client user.
 * @author Joseph LaPell
 * @author Naida Torres
 */
public class ClackClient {
    private String userName;
    private String hostName;
    private int port;
    private boolean closeConnection;
    private ClackData dataToSendToServer;
    private ClackData dataToReceiveFromServer;
    private Scanner inFromStd;
    private static final int CONSTANT_DEFAULTPORT = 7000;

    /**
     * Constructor that sets username, host name, and port
     * @param userName
     * @param hostName
     * @param port
     */
    public ClackClient(String userName, String hostName, int port) {
        this.userName = userName;
        this.hostName = hostName;
        this.port = port;
        closeConnection = false;
        dataToSendToServer = null;
        dataToReceiveFromServer = null;
    }

    /**
     * Constructor to set up port to default, calling another constructor
     * @param userName
     * @param hostName
     */
    public ClackClient(String userName, String hostName) {
        this(userName, hostName, CONSTANT_DEFAULTPORT);
    }

    /**
     * Constructor that sets host name to be "localhost"
     * @param userName
     */
    public ClackClient(String userName) {
        this(userName, "localhost");
    }

    /**
     * Default constructor
     */
    public ClackClient() {
        this("Anon");
    }

    /**
     * start function declaration
     */
    public void start() {
    }

    /**
     * readClientData function declaration
     */
    public void readClientData() {
        final String command = inFromStd.next();
        switch(command) {
            case "DONE":
                closeConnection = true;
                dataToSendToServer = new MessageClackData(userName, command, ClackData.CONSTANT_SENDMESSAGE);
                break;
            case "SENDFILE":
                dataToSendToServer = new FileClackData(userName, inFromStd.next(), ClackData.CONSTANT_SENDFILE);
                try {
                    ((FileClackData) dataToSendToServer).readFileContents();
                }
                catch (IOException ioe) {
                    dataToSendToServer = null;
                    System.err.println(ioe);
                }
                break;
            case "LISTUSERS":
                // not yet implemented
                break;
            default:
                dataToSendToServer = new MessageClackData(userName, command, ClackData.CONSTANT_SENDMESSAGE);
                break;
        }
    }

    /**
     * sendData function declaration
     */
    public void sendData() {
    }

    /**
     * receiveData function declaration
     */
    public void receiveData() {
    }

    /**
     * printData function declaration
     */
    public void printData() {
        System.out.println(dataToReceiveFromServer.getUserName());
        System.out.println(dataToReceiveFromServer.getType());
        System.out.println(dataToReceiveFromServer.getData());
    }

    /**
     * returns username
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * returns host name
     * @return hostName
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * returns port
     * @return port
     */
    public int getPort() {
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
        hash = 31 * hash + (userName == null ? 0 : userName.hashCode());
        hash = 31 * hash + (hostName == null ? 0 : hostName.hashCode());
        hash = 31 * hash + (dataToSendToServer == null ? 0 : dataToSendToServer.hashCode());
        hash = 31 * hash + (dataToReceiveFromServer == null ? 0 : dataToReceiveFromServer.hashCode());
        return hash;
    }

    /**
     * equals override
     * @param other
     * @return
     */
    public boolean equals(Object other) {
        ClackClient otherClient = (ClackClient) other;
        return this.userName == otherClient.userName &&
                this.hostName == otherClient.hostName &&
                this.port == otherClient.port &&
                this.dataToSendToServer == otherClient.dataToSendToServer &&
                this.dataToReceiveFromServer == otherClient.dataToReceiveFromServer;
    }

    /**
     * toString override
     * @return
     */
    public String toString() {
        return "The username is: " + userName + "\n" +
                "The hostname is: " + hostName + "\n" +
                "The port is: " + port + "\n" +
                "The connection is: " + closeConnection + "\n" +
                "The data sent to the server is: " + dataToSendToServer + "\n" +
                "The data received from the server is: " + dataToReceiveFromServer + "\n";
    }
}
