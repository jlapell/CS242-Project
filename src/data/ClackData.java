package data;
import java.util.Date;

/**
 * ClackData is an abstract superclass for FileClackData, ClackServer, MessageClackData, and ClackClient.
 *
 * @author Joseph LaPell
 * @author Naida Torres
 */
public abstract class ClackData {
    /**
     * Declaration of constant ints for type.
     */
    public static final int CONSTANT_LISTUSERS = 0;
    public static final int CONSTANT_LOGOUT = 1;
    public static final int CONSTANT_SENDMESSAGE = 2;
    public static final int CONSTANT_SENDFILE = 3;
    protected String userName;
    protected int type;
    protected Date date;

    /**
     * constructor that sets userName, type, and initializes Date
     *
     * @param userName
     * @param type
     */
    public ClackData(String userName, int type) {
        this.userName = userName;
        this.type = type;
        this.date = new Date(); // Not sure
    }

    /**
     * constructor that sets type
     *
     * @param type
     */
    public ClackData(int type) {
        this("Anon", type);
    }

    /**
     * default constructor
     */
    public ClackData() {
        this(CONSTANT_LOGOUT);
    }

    /**
     * method that returns type
     *
     * @return type
     */
    public int getType() {
        return type;
    }

    /**
     * method that returns userName
     *
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * method that returns date
     *
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * declaration of abstract method getData
     */
    public abstract String getData();

    /**
     * delcaration of abstract method getData that takes a key
     * @param key
     * @return
     */
    public abstract String getData(String key);

    protected static String encrypt(String inputStringToEncrypt, String key) {
        String encryptedString = "";
        for (int i = 0, j = 0; i < inputStringToEncrypt.length(); i++) {
            char c = inputStringToEncrypt.charAt(i);
            if (c == ' '){
                encryptedString += " ";
                continue;
            }else if(Character.isLowerCase(c)){
                encryptedString += (char) ((c + Character.toLowerCase(key.charAt(j)) - 2 * 'a') % 26 + 'a');
                j = ++j % key.length();
                continue;
            }else if(Character.isUpperCase(c)){
                encryptedString += (char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
                j = ++j % key.length();
                continue;
            }else
                encryptedString += c;
        }
        return encryptedString;
    }

    protected static String decrypt(String inputStringToDecrypt, String key) {
        String decryptedString = "";
        for (int i = 0, j = 0; i < inputStringToDecrypt.length(); i++) {
            char c = inputStringToDecrypt.charAt(i);
            if (c == ' '){
                decryptedString += " ";
                continue;
            }
            else if (Character.isLowerCase(c)) {
                decryptedString += (char) ((c - Character.toLowerCase(key.charAt(j)) + 26) % 26 + 'a');
                j = ++j % key.length();
                continue;
            }
            else if(Character.isUpperCase(c)){
                decryptedString += (char) ((c - key.charAt(j) + 26) % 26 + 'A');
                j = ++j % key.length();
            }
            else{
                decryptedString += c;
            }
        }
        return decryptedString;
    }
}
