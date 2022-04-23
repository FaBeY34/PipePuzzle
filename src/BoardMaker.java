import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;

public class BoardMaker {
    private Board board;
    private FileReader fileReader;
    private Level currentLevel;
    private int currentLevelNo;

    public BoardMaker(Board board) {
        this.board = board;
        this.fileReader = new FileReader();
        initializeLevel();
    }

    private void initializeLevel() {
        currentLevelNo = 0;
        currentLevel = new Level(currentLevelNo);
    }

    public void createBoard() throws FileNotFoundException {
        incrementLevel();
        Tile newTile;
        ImageView imageView;
        // Exception burada handle edilebilir
        fileReader.setFileAndScanner(new File(currentLevel.getPath()));
        while (fileReader.hasNextLine()) {
            newTile = createTiles(fileReader.getNextLine());
            board.placeTile(newTile);
            imageView = getImages(newTile);
            board.addImages(imageView, newTile);
        }
    }

    private void incrementLevel() {
        currentLevelNo++;
        currentLevel.setLevelNo(currentLevelNo);
    }

    private Tile createTiles(String inputLine) {
        String[] linesplit = inputLine.split(",");
        int id = Integer.parseInt(linesplit[0]);
        String type = linesplit[1];
        String property = linesplit[2];
        switch (type) {
            case "Starter":
                return new Starter(id, type, property);

            case "End":
                return new End(id, type, property);

            case "Empty":
                if (property.equals("none")) {
                    return new Pipe(id, type, property);
                } else
                    return new Empty(id, type, property);


            case "PipeStatic": {
                if (property.equals("Vertical") || property.equals("Horizontal"))
                    return new PipeStatic(id, type, property);
                else {
                    return new CurvedPipeStatic(id, type, property);
                }
            }
            case "Pipe": {
                if (property.equals("none"))
                    return new Pipe(id, type, property);
                else
                    return new CurvedPipe(id, type, property);
            }


        }
        return null;
    }

    private ImageView getImages(Tile tile) {
        String type = tile.getType();
        String property = tile.getProperty();
        Image image = null;
        switch (type) {
            case "Starter":
                if (property.equals("Horizontal")) {
                    image = new Image("images/STARTERHORIZONTAL.PNG", 150, 150, true, true);
                    return new ImageView((image));

                } else if (property.equals("Vertical")) {
                    image = new Image("images/STARTERVERTICAL.PNG", 150, 150, true, true);
                    return new ImageView(image);
                }
                break;
            case "End":
                if (property.equals("Horizontal")) {
                    image = new Image("images/ENDHORIZONTAL.PNG", 150, 150, true, true);
                    return new ImageView(image);
                } else if (property.equals("Vertical")) {
                    return new ImageView(image);
                }
                break;
            case "Empty":
                if (property.equals("none")) {
                    image = new Image("images/EMPTYNONE.jpeg", 150, 150, true, true);
                    return new ImageView(image);

                } else if (property.equals("Free")) {
                    image = new Image("images/EMPTYFREE.jpeg", 151, 152, true, true);
                    return new ImageView(image);
                }
            case "Pipe":
                switch (property) {
                    case "Horizontal":
                        image = new Image("images/PIPEHORIZONTAL.jpeg", 150, 150, true, true);
                        return new ImageView((image));

                    case "Vertical":
                        image = new Image("images/PIPEVERTICAL.jpeg", 150, 150, true, true);
                        return new ImageView(image);


                    case "00":
                        image = new Image("images/CURVED00.jpeg", 150, 150, true, true);
                        return new ImageView((image));

                    case "01":
                        image = new Image("images/CURVED01.jpeg", 150, 150, true, true);
                        return new ImageView(image);

                    case "10":
                        image = new Image("images/CURVED10.jpeg", 150, 150, true, true);
                        return new ImageView(image);

                    case "11":
                        image = new Image("images/CURVED11.jpeg", 150, 150, true, true);
                        return new ImageView(image);

                }
                break;
            case "PipeStatic":
                switch (property) {
                    case "Horizontal":
                        image = new Image("images/PIPESTATICHORIZONTAL.jpeg", 150, 150, true, true);
                        return new ImageView(image);

                    case "Vertical":
                        image = new Image("images/PIPESTATICVERTICAL.jpeg", 150, 150, true, true);
                        return new ImageView(image);


                    // case "00":

                    case "01":
                        image = new Image("images/PIPESTATIC01.jpeg", 100, 100, true, true);
                        return new ImageView(image);

                    // case "10":

                    // case "11":


                }
        }
        return null;
    }

}
