package data;

import java.util.Date;

public class MessageClackData extends ClackData {
    private:
        String message; // string representing instant message
    public:
        // constructor to set up username, message, and type using super constructor
        MessageClackData(String userName, string message, int type) {
        super(userName, type);
        this.message = message;
        }

        // default constructor calling another constructor
        MessageClackData() {
        this("Unknown User", null, CONSTANT_DEFAULT_TYPE)
        }

}
