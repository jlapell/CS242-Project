package data;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Subclass to ClackData
 */
public class FileClackData extends ClackData {
    protected String fileName;
    protected String fileContents;

    /**
     * Constructor that sets userName, fileName, and type
     *
     * @param userName
     * @param fileName
     * @param type
     */
    public FileClackData(String userName, String fileName, int type) {
        super(userName, type);
        this.fileName = fileName;
        this.fileContents = null;
    }

    /**
     * Default constructor
     */
    public FileClackData() {
        super(null, 0);
    }

    /**
     * Constructor that sets fileName
     *
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * method that returns fileName
     *
     * @return fileName
     */
    public String getFilename() {
        return fileName;
    }

    /**
     * method that returns fileContents
     *
     * @return fileContents
     */
    public String getData() {
        return fileContents;
    }

    /**
     * Read contents from file and write to fileContents
     */
    public void readFileContents() throws IOException {
        File file = new File(fileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String nextLine;
            fileContents = "";
            while ((nextLine = bufferedReader.readLine()) != null)
                fileContents += nextLine;
            bufferedReader.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("File does not exist");
        }
//        catch (IOException ioe) {
//            System.err.println("IO exception occurred.");
//        }
    }

    /**
     * Read contents from file and write to fileContents, then encrypt them.
     *
     * @param key
     */
    public void readFileContents(String key) throws IOException {
        File file = new File(fileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String nextLine;
            fileContents = "";
            while ((nextLine = bufferedReader.readLine()) != null)
                fileContents += nextLine;
            bufferedReader.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("File does not exist");
        }
//        catch (IOException ioe) {
//            System.err.println("IO exception occurred.");
//        }
        fileContents = encrypt(fileContents, key);
    }

    /**
     * declaration of writeFileContents method
     */
    public void writeFileContents() {
        File file = new File(fileName);
        try {
            PrintWriter writeFile = new PrintWriter(file);
            writeFile.println(fileContents);
            writeFile.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("File does not exist");
        }
    }

    public void writeFileContents(String key) {
        File file = new File(fileName);
        try {
            PrintWriter writeFile = new PrintWriter(file);
            writeFile.println(decrypt(fileContents, key));
            writeFile.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("File does not exist");
        }
    }

    /**
     * hashCode override
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (fileName == null ? 0 : fileName.hashCode());
        hash = 31 * hash + (fileContents == null ? 0 : fileContents.hashCode());
        hash = 31 * hash + (userName == null ? 0 : userName.hashCode());
        hash = 31 * hash + type;
        return hash;
    }

    /**
     * equals override
     *
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        if (other == null)
            return false;
        if (!(other instanceof FileClackData))
            return false;
        FileClackData otherFile = (FileClackData) other;
        return this.fileName == otherFile.fileName &&
                this.fileContents == otherFile.fileContents &&
                super.userName == otherFile.userName &&
                super.type == otherFile.type;
    }

    /**
     * toString override
     *
     * @return
     */
    @Override
    public String toString() {
        return "The file name is: " + this.fileName + "\n" +
                "The file contents are: " + this.fileContents + "\n" +
                "The username is: " + this.userName + "\n" +
                "The type is: " + this.type + "\n" +
                "The date is: " + this.date.toString() + "\n";
    }

    /**
     * Method that takes a key and returns the decrypted file contents
     *
     * @param key
     * @return
     */
    @Override
    public String getData(String key) {
        return decrypt(fileContents, key);
    }
}
