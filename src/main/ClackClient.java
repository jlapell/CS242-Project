package main;

import data.ClackData;

/**
 * ClackClient represents the client user.
 */
public class ClackClient {
    /**
     * String representing name of the client
     */
    private String userName;

    /**
     * String representing name of the computer
     * representing the server.
     */
    private String hostName;

    /**
     * Integer representing port number on server
     * connected to
     */
    private int port;

    /**
     * Boolean representing whether connection is
     * closed or not
     */
    private boolean closeConnection;

    /**
     * ClackData object representing data sent to server
     */
    private ClackData dataToSendToServer;

    /**
     * ClackData object representing data received from the server
     */
    private ClackData dataToReceiveFromServer;

    /**
     * default port number
     */
    protected static final int CONSTANT_DEFAULTPORT = 7000;

    /**
     * Constructor that accepts username, host name, and port
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
     * Constructor to set up port to default,
     * calling another constructor
     */
    public ClackClient(String userName, String hostName) {
        this(userName, hostName, CONSTANT_DEFAULTPORT);
    }

    /**
     * constructor that sets host name to be "localhost"
     */
    public ClackClient(String userName) {
        this(userName, "localhost");
    }

    /**
     * default constructor
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
    }

    /**
     * returns username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * returns host name
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * returns port
     */
    public int getPort() {
        return port;
    }

    /**
     * hashCode override
     */
    public int hashCode() {
        return userName.hashCode() * hostName.hashCode() * port *
                dataToSendToServer.hashCode() * dataToReceiveFromServer.hashCode();
    }

    /**
     * equals override
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
