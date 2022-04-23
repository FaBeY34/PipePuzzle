import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    private File file;
    private Scanner scanner;

    public FileReader(){

    }

    public void setFileAndScanner(File file) throws FileNotFoundException {
        this.file = file;
        scanner = new Scanner(file);
    }

    public String getNextLine() {
        if (scanner != null && scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return null;
    }

    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

}
