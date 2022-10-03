package data;

import java.io.File;

/**
 * Subclass to ClackData
 */
public class FileClackData extends ClackData{ // removed abstract because tester wasn't working
    protected String fileName;
    protected String fileContents;

    /**
     * Constructor that sets userName, fileName, and type
     * @param userName
     * @param fileName
     * @param type
     */
    public FileClackData(String userName, String fileName, int type){
        super(userName, type);
        this.fileName = fileName;
        this.fileContents = null;
    }

    /**
     * Default constructor
     */
    public FileClackData(){
        super(null,0);
    }

    /**
     * Constructor that sets fileName
     * @param fileName
     */
    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    /**
     * method that returns fileName
     * @return fileName
     */
    public String getFilename(){
        return fileName;
    }

    /**
     * method that returns fileContents
     * @return fileContents
     */
    public String getData() {
        return fileContents;
    }

    /**
     * declaration of readFileContents method
     */
    public void readFileContents(){
    }

    /**
     * declaration of writeFileContents method
     */
    public void writeFileContents(){
    }

    /**
     * hashCode override
     * @return
     */
    @Override
    public int hashCode(){
        return fileName.hashCode() * fileContents.hashCode() * super.userName.hashCode() * super.type;
    }

    /**
     * equals override
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        if(other == null)
            return false;
        if(!(other instanceof FileClackData))
            return false;
        FileClackData otherFile = (FileClackData)other;
        return this.fileName == otherFile.fileName &&
                this.fileContents == otherFile.fileContents &&
                super.userName == otherFile.userName &&
                super.type == otherFile.type;
    }

    /**
     * toString override
     * @return
     */
    @Override
    public String toString(){
        return "The file name is: " + this.fileName + "\n" +
                "The file contents are: " + this.fileContents + "\n";
    }
}
