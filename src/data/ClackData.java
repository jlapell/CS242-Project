package data;

import java.util.Date;

public abstract class ClackData {
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
      //  userName;
       // type;

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
