package data;

import java.util.Date;

/**
 * Subclass to ClackData
 * implements an instant message function, the 'message'
 * @author Joseph LaPell
 * @author Naida Torres
 */

public class MessageClackData extends ClackData {
    private String message;

    /**
     * constructor to set up username, message, and type using super constructor
     * @param userName
     * @param message
     * @param type
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
     * @return message
     */
    public String getData() {
        return message;
    }

    /**
     * hash code, overridden
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (message == null ? 0 : message.hashCode());
        hash = 31 * hash + (userName == null ? 0 : userName.hashCode());
        return hash;
    }

    /**
     * equals function, overridden
     * @return other
     */
    @Override
    public boolean equals(Object other) {
        if (other == null)
            return false;
        if (!(other instanceof MessageClackData))
            return false;
        MessageClackData otherMessage = (MessageClackData)other;
        return this.userName == otherMessage.userName &&
                this.type == otherMessage.type &&
                this.message == otherMessage.message;
    }

    /**
     * toString override,
     * returns a full description of the class with all instance
     * variables, including those in the superclass
     * @return
     */
    @Override
    public String toString() {
        return "The username is: " + getUserName() + "\n" +
                "The type is: " + getType() + "\n" +
                "The message is: " + message + "\n";
    }
}
