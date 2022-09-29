import java.io.File;

public abstract class FileClackData extends ClackData{
    protected String fileName;
    protected String fileContents;

    public FileClackData(String userName, String fileName, int type){
        super(userName, type);
        this.fileName = fileName;
        this.fileContents = null;
    }

    public FileClackData(){
        super(null,0);
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public String getFilename(){
        return fileName;
    }

    public String getData(){
        return fileContents;
    }

    public void readFileContents(){
    }

    public void writeFileContents(){
    }

    /*public int hashCode(){
        return fileName.hashCode() * fileContents.hashCode();
    }*/

    public boolean equals(Object other) {
        FileClackData otherData = (FileClackData)other;
        return this.fileName == otherData.fileName &&
                this.fileContents == otherData.fileContents &&
                super.userName == otherData.userName &&
                super.type == otherData.type;
    }

    public String toString(){
        return "The file name is: " + this.fileName + "\n" +
                "The file contents are: " + this.fileContents + "\n";
    }
}
