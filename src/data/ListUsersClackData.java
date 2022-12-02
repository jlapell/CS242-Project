package data;

import java.util.ArrayList;
import java.util.Objects;

public class ListUsersClackData extends ClackData{
    /**
     * Class of users connected to a server.
     */

    private ArrayList<String> userList;

    /**
     * constructor that sets username and type.
     *
     * @param userName
     * @param type
     */
    public ListUsersClackData(String userName, int type) {
        super(userName, type);
        userList = new ArrayList<String>();
    }

    /**
     * default constructor
     */
    public ListUsersClackData() {
        this("Anon", CONSTANT_LISTUSERS);
    }

    /**
     * adds user to userList.
     *
     * @param userName
     */
    public synchronized void addUser(String userName) {
        if (!userList.contains(userName)) {
            userList.add(userName);
        }
    }

    /**
     * removes user from userList.
     *
     * @param userName
     */
    public synchronized void deleteUser(String userName) {
        userList.remove(userName);
    }

    /**
     * returns message of users for user
     *
     * @return
     */
    @Override
    public String getData() {
        return userList;
    }

    @Override
    public String toString() {
        return "ListUsersClackData: " + getData();
    }
}
