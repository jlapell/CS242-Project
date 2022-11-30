package main;

public class ClientSideServerListener implements Runnable {
    /**
     * This class takes over the responsibility of receiving data from the server and printing it.
     *
     * @author Joseph LaPell
     * @author Naida Torres
     */


    // answer to question why there are seperate classes:
    // seperate class bc each class runs at the same time while doing
    // different things. this allows the client to send and receive
    // messages at the same time, not having to wait to send a message
    // for the server response. it is called a listener bc it "listens,"
    // or waits for, messages from the server.
    private ClackClient client;

    /**
     * Constructor that takes in a ClackClient object 'client' as parameter
     *
     * @param client
     */
    public ClientSideServerListener(ClackClient client) {
        this.client = client;
    }

    /**
     * Method from overridden from Runnable interface
     */
    @Override
    public void run() {
        while(!client.getCloseConnection()) {
            client.receiveData();
            client.printData();
        }
    }
}
