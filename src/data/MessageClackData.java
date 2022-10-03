package data;

import java.util.Date;

/**
 * Subclass to ClackData
 * implements an instant message function, the 'message'
 */

public class MessageClackData extends ClackData {
    /**
     * string representing instant message
     */
    private String message;

    /**
     * constructor to set up username, message, and type using super constructor
     */
    public MessageClackData(String userName, String message, int type) {
        super(userName, type);
        this.message = message;
    }

    /**
     * default constructor calling another constructor
     */
    public MessageClackData() {
        this("Anon", null, -1);
    }

    /**
     * return instant message
     */
    public String getData() {
        return message;
    }

    /**
     * hash code, overridden
     */
    public int hashCode() {
        return message.hashCode() * userName.hashCode() * type;
    }

    /**
     * equals function, overridden
     */
    public boolean equals(Object other) {
        MessageClackData otherMessage = (MessageClackData)other;
        return this.userName == otherMessage.userName &&
                this.type == otherMessage.type &&
                this.message == otherMessage.message;
    }

    /**
     * toString override,
     * returns a full description of the class with all instance
     * variables, including those in the superclass
     */
    public String toString() {
        return "The username is: " + getUserName() + "\n" +
                "The type is: " + getType() + "\n" +
                "The message is: " + message + "\n";
    }
}
