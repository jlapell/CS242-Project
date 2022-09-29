package data;
import java.util.Date;

public abstract class ClackData {
    protected static final int CONSTANT_LISTUSERS = 0;
    protected static final int CONSTANT_LOGOUT = 1;
    protected static final int CONSTANT_SENDMESSAGE = 2;
    protected static final int CONSTANT_SENDFILE = 3;
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
        this("Anon", -1);
    }

    public int getType(){
        return type;
    }

    public String getUserName(){
        return userName;
    }

    public Date getDate(){
        return date;
    }

    public abstract String getData();

}
