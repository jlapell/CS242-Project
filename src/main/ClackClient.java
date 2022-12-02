package main;

import data.ClackData;
import data.FileClackData;
import data.ListUsersClackData;
import data.MessageClackData;
import main.ClackServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * ClackClient class representing the client user.
 *
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
    private ObjectInputStream inFromServer;
    private ObjectOutputStream outToServer;
    private static final int CONSTANT_DEFAULTPORT = 7000;
    private static final String CONSTANT_DEFAULTKEY = "DefaultKey";



    /**
     * Constructor that sets username, host name, and port
     *
     * @param userName
     * @param hostName
     * @param port
     */
    public ClackClient(String userName, String hostName, int port) {
        if (userName == null) {
            throw new IllegalArgumentException("userName cannot be null");
        }
        if (hostName == null) {
            throw new IllegalArgumentException("hostName cannot be null");
        }
        if (port < 1024) {
            throw new IllegalArgumentException("port cannot be less than 1024");
        }
        this.userName = userName;
        this.hostName = hostName;
        this.port = port;
        closeConnection = false;
        dataToSendToServer = null;
        dataToReceiveFromServer = null;
        inFromServer = null;
        outToServer = null;
    }

    /**
     * Constructor to set up port to default, calling another constructor
     *
     * @param userName
     * @param hostName
     */
    public ClackClient(String userName, String hostName) {
        this(userName, hostName, CONSTANT_DEFAULTPORT);
    }

    /**
     * Constructor that sets host name to be "localhost"
     *
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
     * does not return anything but starts the connection
     * read data from the client
     * prints the data out
     */
    public void start() {
        try {
            this.inFromStd = new Scanner(System.in);
            Socket skt = new Socket(hostName, port);
            outToServer = new ObjectOutputStream(skt.getOutputStream());
            inFromServer = new ObjectInputStream(skt.getInputStream());
            ClientSideServerListener clientSideSL = new ClientSideServerListener(this);
            Thread clientSideThread = new Thread(clientSideSL);
            clientSideThread.start();
            while (!closeConnection) {
                this.readClientData();
                this.sendData();
            }
            inFromServer.close();
            outToServer.close();
            this.inFromStd.close();
            skt.close();
        }
        catch (UnknownHostException uhe) {
            System.err.println("Unknown host: " + uhe);
        }
        catch (NoRouteToHostException nrthe) {
            System.err.println("No route to host: " + nrthe);
        }
        catch (IOException ioe) {
            System.err.println("IOException: " + ioe);
        }
    }

    /**
     * reads the data from the client
     * does not return anything
     * not yet finished as of part 2
     */
    public void readClientData() {
        final String command = inFromStd.next();
        switch (command) {
            case "DONE":
                this.closeConnection = true;
                this.dataToSendToServer = new MessageClackData(this.userName, command, CONSTANT_DEFAULTKEY, ClackData.CONSTANT_LOGOUT);
                break;
            case "SENDFILE":
                this.dataToSendToServer = new FileClackData(this.userName, inFromStd.next(), ClackData.CONSTANT_SENDFILE);
                try {
                    ((FileClackData) this.dataToSendToServer).readFileContents(CONSTANT_DEFAULTKEY);
                } catch (Exception e) {
                    this.dataToSendToServer = null;
                    System.err.println("The file cannot be read: " + e);
                }
                break;
            case "LISTUSERS": // unsure
                // answer to question: displays a message to users of all users in chat,
                // uses constant list users variable to get users.
                // serverSideClientIOArrayList
                this.dataToSendToServer = new ListUsersClackData(userName, ClackData.CONSTANT_LISTUSERS);
                break;
            default:
                String message = command + this.inFromStd.nextLine();
                dataToSendToServer = new MessageClackData(this.userName, message, CONSTANT_DEFAULTKEY, ClackData.CONSTANT_SENDMESSAGE);
                break;
        }
    }

    /**
     * sendData function
     * sends data to server, does not return anything
     */
    public void sendData() {
        try {
            outToServer.writeObject(dataToSendToServer);
        }
        catch (UnknownHostException uhe) {
            System.err.println("Unknown host: " + uhe);
        }
        catch (NoRouteToHostException nrthe) {
            System.err.println("No route to host: " + nrthe);
        }
        catch (IOException ioe) {
            System.err.println("IOException: " + ioe);
        }
    }

    /**
     * receiveData function
     * receives data from the server, does not return anything
     */
    public void receiveData() {
        try {
            dataToReceiveFromServer = (ClackData) inFromServer.readObject();
        }
        catch (UnknownHostException uhe) {
            System.err.println("Unknown host: " + uhe);
        }
        catch (NoRouteToHostException nrthe) {
            System.err.println("No route to host: " + nrthe);
        }
        catch (IOException ioe) {
            System.err.println("IOException: " + ioe);
        }
        catch (ClassNotFoundException cnfe) {
            System.err.println("Class not found: " + cnfe);
        }
    }

    /**
     * prints the received data to standard output
     */
    public void printData() {
        if (this.dataToReceiveFromServer == null) return;
        System.out.println("User: " + dataToReceiveFromServer.getUserName());
        System.out.println("Date: " + dataToReceiveFromServer.getDate());
        System.out.println("Message: " + dataToReceiveFromServer.getData(CONSTANT_DEFAULTKEY));
    }

    /**
     * returns username
     *
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * returns host name
     *
     * @return hostName
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * returns port
     *
     * @return port
     */
    public int getPort() {
        return port;
    }

    /**
     * returns closeConnection
     *
     * @return closeConnection
     */
    public boolean getCloseConnection() {
        return closeConnection;
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
        hash = 31 * hash + (userName == null ? 0 : userName.hashCode());
        hash = 31 * hash + (hostName == null ? 0 : hostName.hashCode());
        hash = 31 * hash + (dataToSendToServer == null ? 0 : dataToSendToServer.hashCode());
        hash = 31 * hash + (dataToReceiveFromServer == null ? 0 : dataToReceiveFromServer.hashCode());
        return hash;
    }

    /**
     * equals override
     *
     * @param other
     * @return
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ClackClient)) {
            return false;
        }

        ClackClient otherClient = (ClackClient) other;
        return this.userName.equals(otherClient.userName) &&
                this.hostName.equals(otherClient.hostName) &&
                this.port == otherClient.port &&
                this.dataToSendToServer == otherClient.dataToSendToServer &&
                this.dataToReceiveFromServer == otherClient.dataToReceiveFromServer;
    }

    /**
     * toString override
     *
     * @return
     */
    public String toString() {
        return "The username is: " + this.userName + "\n" +
                "The hostname is: " + this.hostName + "\n" +
                "The port is: " + this.port + "\n" +
                "The connection is: " + this.closeConnection + "\n" +
                "The data sent to the server is: " + this.dataToSendToServer + "\n" +
                "The data received from the server is: " + this.dataToReceiveFromServer + "\n";
    }

    /**
     * main method
     * uses command line arguments to create a new ClackClient object
     * and starts said object
     *
     * @param args
     */
    public static void main(String[] args) { // ask TA if this is correct, better as switch?
        try {
            ClackClient client;
            if (args.length > 0) {
                final String input = args[0];
                if (input.contains("@")) {
                    if (input.contains(":")) {
                        // username, hostname, port
                        final String username = input.split("@")[0];
                        final String hostname = input.split("@")[1].split(":")[0];
                        final int port = Integer.parseInt(input.split(":")[1]);
                        client = new ClackClient(username, hostname, port);
                    } else {
                        // username, hostname
                        final String username = input.split("@")[0];
                        final String hostname = input.split("@")[1];
                        client = new ClackClient(username, hostname);
                    }
                }
                else {
                    // username
                    client = new ClackClient(input);
                }
            }
            else {
                // default
                client = new ClackClient();
            }
            client.start();
        }
        catch (ArrayIndexOutOfBoundsException aiobe) {
            System.err.println("Array index out of bounds: " + aiobe);
        }
        catch (NumberFormatException nfe) {
            System.err.println("Number format exception: " + nfe);
        }
    }
}