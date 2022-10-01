package test;

import main.ClackServer;

/**
 * test driver for ClackServer
 * did not add tests for functions that are not yet implemented
 */
public class TestClackServer {
    public static void main(String[] args) {
        ClackServer testDefault = new ClackServer();
        System.out.println(testDefault.getPort());
        System.out.println(testDefault.hashCode());
        System.out.println(testDefault.toString());

        ClackServer testPort = new ClackServer(1000);
        System.out.println(testPort.getPort());
        System.out.println(testPort.hashCode());
        System.out.println(testPort.equals(testDefault));
        System.out.println(testPort.toString());
    }
}
