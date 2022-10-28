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
     * constructor to set up username, message, key, and type
     * @param userName
     * @param message
     * @param key
     * @param type
     */
    MessageClackData(String userName, String message, String key, int type) {
        super(userName, type);
        this.message = encrypt(message, key);
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
    * returns a decrypted message
     * @param key
     * @return
     */
    @Override
    public String getData(String key) {
        return decrypt(message, key);
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
        hash = 31 * hash + type;
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
        return "The username is: " + this.userName + "\n" +
                "The type is: " + this.type + "\n" +
                "The message is: " + this.message + "\n" +
                "The date is: " + this.date.toString() + "\n";
    }
}
