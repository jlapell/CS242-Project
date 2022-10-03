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


    public ClackData(String userName, int type){
        this.userName = userName;
        this.type = type;
        this.date = new Date(); // Not sure
    }

    public ClackData(int type){
        this("Anon", type);
    }

    public ClackData(){
        this(CONSTANT_LOGOUT);
    }

    public int getType(){
        return type;
    }

    /**
     *
     * @return
     */
    public String getUserName(){
        return userName;
    }

    /**
     *
     * @return
     */
    public Date getDate(){
        return date;
    }

    /**
     *
     */
    public abstract String getData();

}
