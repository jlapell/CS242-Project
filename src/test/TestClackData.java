package test;

import data.ClackData;
import data.FileClackData;
import data.MessageClackData;

/**
 * test driver for ClackData
 * have not added functions that are not yet implemented
 * @author Joseph LaPell
 * @author Naida Torres
 */

public class TestClackData {
    public static void main(String[] args) {
        ClackData testFileDefault = new FileClackData();
        System.out.println(testFileDefault.getType());
        System.out.println(testFileDefault.getUserName());
        System.out.println(testFileDefault.getDate());
        System.out.println(testFileDefault.hashCode());
        System.out.println(testFileDefault.toString());

        ClackData testFileParameters = new FileClackData("TestUser", "TestFile", 0);
        System.out.println(testFileParameters.getType());
        System.out.println(testFileParameters.getUserName());
        System.out.println(testFileParameters.getDate());
        System.out.println(testFileParameters.hashCode());
        System.out.println(testFileParameters.equals(testFileDefault));
        System.out.println(testFileParameters.toString());

        ClackData testMessageDefault = new MessageClackData();
        System.out.println(testMessageDefault.getType());
        System.out.println(testMessageDefault.getUserName());
        System.out.println(testMessageDefault.getDate());
        System.out.println(testMessageDefault.hashCode());
        System.out.println(testMessageDefault.toString());

        ClackData testMessageParameters = new MessageClackData("TestUser", "TestMessage", 1);
        System.out.println(testMessageParameters.getType());
        System.out.println(testMessageParameters.getUserName());
        System.out.println(testMessageParameters.getDate());
        System.out.println(testMessageParameters.hashCode());
        System.out.println(testMessageParameters.equals(testMessageDefault));
        System.out.println(testMessageParameters.toString());
    }
}
