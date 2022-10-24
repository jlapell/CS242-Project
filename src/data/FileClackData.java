package data;

import java.io.*;

/**
 * Subclass to ClackData
 */
public class FileClackData extends ClackData {
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
     * Read contents from file and write to fileContents
     */
    public void readFileContents(){
        File file = new File(fileName);
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String nextLine;
            while((nextLine = bufferedReader.readLine()) != null){
                fileContents += nextLine;
            }
            bufferedReader.close();
        }catch(FileNotFoundException fnfe){
            System.err.println("File does not exist");
        }catch(IOException ioe) {
            System.err.println("IO exception occurred.");
        }
    }

    /**
     * Read contents from file and write to fileContents, then encrypt them.
     * @param key
     */
    public void readFileContents(String key){
        File file = new File(fileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String nextLine;
            while ((nextLine = bufferedReader.readLine()) != null) {
                fileContents += nextLine;
            }
        }catch(FileNotFoundException fnfe){
            System.err.println("File does not exist");
        }catch(IOException ioe) {
            System.err.println("IO exception occurred.");
        }
        encrypt(fileContents, key);
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
        int hash = 7;
        hash = 31 * hash + (fileName == null ? 0 : fileName.hashCode());
        hash = 31 * hash + (fileContents == null ? 0 : fileContents.hashCode());
        return hash;
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

    @Override
    public String getData(String key) {
        return decrypt(fileContents, key);
    }
}
