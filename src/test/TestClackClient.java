package test;

import main.ClackClient;

/**
 * test driver for ClackClient
 * have not added functions that are not yet implemented
 * @author Joseph LaPell
 * @author Naida Torres
 */
public class TestClackClient {
    public static void main(String[] args) {
        ClackClient testDefault = new ClackClient();
        System.out.println(testDefault.getUserName());
        System.out.println(testDefault.getHostName());
        System.out.println(testDefault.getPort());
        System.out.println(testDefault.hashCode());
        System.out.println(testDefault.toString());

        ClackClient testUserName = new ClackClient("TestUser");
        System.out.println(testUserName.getUserName());
        System.out.println(testUserName.getHostName());
        System.out.println(testUserName.getPort());
        System.out.println(testUserName.hashCode());
        System.out.println(testUserName.equals(testDefault));
        System.out.println(testUserName.toString());

        ClackClient testHostName = new ClackClient("TestUser", "TestHost");
        System.out.println(testHostName.getUserName());
        System.out.println(testHostName.getHostName());
        System.out.println(testHostName.getPort());
        System.out.println(testHostName.hashCode());
        System.out.println(testHostName.equals(testDefault));
        System.out.println(testHostName.toString());

        ClackClient testPort = new ClackClient("TestUser", "TestHost", 1000);
        System.out.println(testPort.getUserName());
        System.out.println(testPort.getHostName());
        System.out.println(testPort.getPort());
        System.out.println(testPort.hashCode());
        System.out.println(testPort.equals(testDefault));
        System.out.println(testPort.toString());
    }
}
