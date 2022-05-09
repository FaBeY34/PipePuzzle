import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    // This class will be used to read a file, so it has file and scanner properties.

    // Variables
    private File file;
    private Scanner scanner;

    // Constructor
    public FileReader(){

    }

    // This method will be used to read the level txt.
    public void setFileAndScanner(File file) throws FileNotFoundException {
        this.file = file;
        scanner = new Scanner(file);
    }

    // This method will bring the next line in txt file.
    public String getNextLine() {
        if (scanner != null && scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return null;
    }
    // this method will check whether the next line of file is empty or not.
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

}
