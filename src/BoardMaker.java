import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.FileNotFoundException;

public class BoardMaker {
    // BoardMaker class will provide us to fill the board with given tiles and set the event action of tiles.So it needs board itself, a fileReader to read the required tiles
    //  level no to create next board and a button and number of moves  to record the tile movements in every changing

    //Variables
    private Board board;
    private FileReader fileReader;
    private int currentLevelNo;
    public int numberOfMovements = 0;
    Button button;

    // Constructors
    public BoardMaker() {
        button = new Button("NUMBER OF MOVES " + numberOfMovements);
        fileReader = new FileReader();
        initializeLevel();
    }

    public BoardMaker(Board board) {
        this.board = board;
        button = new Button("NUMBER OF MOVES " + numberOfMovements);
        this.fileReader = new FileReader();
        initializeLevel();
    }

    // After creating a boardmaker, we initialize the level as 0.
    private void initializeLevel() {
        currentLevelNo = 0;

    }

    // this method will arrange tiles to our board according to input files and start events for tiles which are movable.

    public void createBoard() throws FileNotFoundException {

        incrementLevel();
        Tile newTile;

        fileReader.setFileAndScanner(new File(getLevelPath()));
        while (fileReader.hasNextLine()) {
            newTile = createTiles(fileReader.getNextLine());
            addımages(newTile);
            board.placeTileAndAppendToPane(newTile);
        }
        setOnMouseSwipeEvents();
    }

    // this will bring us the current path of level file
    private String getLevelPath() {
        return "src/level" + currentLevelNo + ".txt";
    }

    // after every board creation we will increment the level.

    private void incrementLevel() {
        currentLevelNo++;

    }
    // this method will create tiles based on input files.

    private Tile createTiles(String inputLine) {
        String[] linesplit = inputLine.split(",");
        int id = Integer.parseInt(linesplit[0]);
        String type = linesplit[1];
        String property = linesplit[2];
        return new Tile(id, type, property);
    }

    // this method will add images to tile based on its property.

    private void addımages(Tile newTile) {
        String type = newTile.getType();
        String property = newTile.getProperty();
        String typePro = type + property;
        switch (typePro) {
            case "StarterVertical":
                newTile.getChildren().add(new ImageView(new Image("images/STARTERVERTICAL.PNG", 150, 150, true, true)));
                break;
            case "StarterHorizontal":
                newTile.getChildren().add(new ImageView(new Image("images/STARTERHORIZONTAL.PNG", 150, 150, true, true)));
                break;
            case "PipeVertical":
                newTile.getChildren().add(new ImageView(new Image("images/PIPEVERTICAL.jpeg", 150, 150, true, true)));
                break;
            case "PipeHorizontal":
                newTile.getChildren().add(new ImageView(new Image("images/PIPEHORIZONTAL.jpeg", 150, 150, true, true)));
                break;
            case "Pipe00":
                newTile.getChildren().add(new ImageView(new Image("images/CURVED00.jpeg", 150, 150, true, true)));
                break;
            case "Pipe01":
                newTile.getChildren().add(new ImageView(new Image("images/CURVED01.jpeg", 150, 150, true, true)));
                break;
            case "Pipe10":
                newTile.getChildren().add(new ImageView(new Image("images/CURVED10.jpeg", 150, 150, true, true)));
                break;
            case "Pipe11":
                newTile.getChildren().add(new ImageView(new Image("images/CURVED11.jpeg", 150, 150, true, true)));
                break;
            case "PipeStaticHorizontal":
                newTile.getChildren().add(new ImageView(new Image("images/PIPESTATICHORIZONTAL.jpeg", 150, 150, true, true)));
                break;
            case "PipeStaticVertical":
                newTile.getChildren().add(new ImageView(new Image("images/PIPESTATICVERTICAL.jpeg", 150, 150, true, true)));
                break;
            case "PipeStatic01":
                newTile.getChildren().add(new ImageView(new Image("images/PIPESTATIC01.jpeg", 150, 150, true, true)));
                break;
            case "Emptynone":
                newTile.getChildren().add(new ImageView(new Image("images/EMPTYNONE.jpeg", 150, 150, true, true)));
                break;
            case "EmptyFree":
                newTile.getChildren().add(new ImageView(new Image("images/EMPTYFREE.jpeg", 150, 150, true, true)));
                break;
            case "EndHorizontal":
                newTile.getChildren().add(new ImageView(new Image("images/ENDHORIZONTAL.PNG", 150, 150, true, true)));
                break;
            case "EndVertical":
                newTile.getChildren().add(new ImageView(new Image("images/ENDVERTICAL.PNG", 150, 150, true, true)));
                break;
        }
    }

    // this method will generate tiles events after board is arranged.
    public void setOnMouseSwipeEvents() {
        Tile[][] boardSurface = board.getSurface();
        Tile tile;
        for (Tile[] tiles : boardSurface) {
            for (Tile value : tiles) {
                tile = value;
                if (tile.isMovable())
                    setOnSwipeEvent(tile);
            }
        }
    }

    // to indicate the events its important where each tile is positioned in board. Thats why we made a 9 group according to their positions
    // for example if row and column 00 we can only go to down or right or for case 01 we can go to left right or down and so on
    // group 1 > 00(down-right)    group 2 > 03(down-left)    group 3 > 30(right-up)   group 4 > 33(left-up)      group 5 > 01-02(left-right)    group 6 > 10-20(right-up-down)  group 7 >31-32(left-up-down)  group 8 >31-32(left--right-up) group 9> remain positions(left-right-down-up)
    public void setOnSwipeEvent(Tile tile) {
        int group = getGroup(tile);
        switch (group) {
            case 1:
                setEventsForGroup1(tile);
                break;
            case 2:
                setEventsForGroup2(tile);
                break;
            case 3:
                setEventsForGroup3(tile);
                break;
            case 4:
                setEventsForGroup4(tile);
                break;
            case 5:
                setEventsForGroup5(tile);
                break;
            case 6:
                setEventsForGroup6(tile);
                break;
            case 7:
                setEventsForGroup7(tile);
                break;
            case 8:
                setEventsForGroup8(tile);
                break;
            case 9:
                setEventsForGroup9(tile);
                break;

        }
    }
    // The setEventsForGroup 1 up to 9 will assign the events of tiles based on the logic mentioned in setOnSwipeEvent method.

    private void setEventsForGroup9(Tile tile) {
        setOnMouseSwipeLeftEvent(tile);
        setOnMouseSwipeRightEvent(tile);
        setOnMouseSwipeUpEvent(tile);
        setOnMouseSwipeDownEvent(tile);
    }

    private void setEventsForGroup8(Tile tile) {
        setOnMouseSwipeLeftEvent(tile);
        setOnMouseSwipeRightEvent(tile);
        setOnMouseSwipeUpEvent(tile);
    }

    private void setEventsForGroup7(Tile tile) {
        setOnMouseSwipeLeftEvent(tile);
        setOnMouseSwipeUpEvent(tile);
        setOnMouseSwipeDownEvent(tile);
    }

    private void setEventsForGroup6(Tile tile) {
        setOnMouseSwipeRightEvent(tile);
        setOnMouseSwipeUpEvent(tile);
        setOnMouseSwipeDownEvent(tile);
    }

    private void setEventsForGroup5(Tile tile) {
        setOnMouseSwipeLeftEvent(tile);
        setOnMouseSwipeRightEvent(tile);
        setOnMouseSwipeDownEvent(tile);
    }

    private void setEventsForGroup4(Tile tile) {
        setOnMouseSwipeLeftEvent(tile);
        setOnMouseSwipeUpEvent(tile);
    }

    private void setEventsForGroup3(Tile tile) {
        setOnMouseSwipeRightEvent(tile);
        setOnMouseSwipeUpEvent(tile);
    }

    private void setEventsForGroup2(Tile tile) {
        setOnMouseSwipeDownEvent(tile);
        setOnMouseSwipeLeftEvent(tile);
    }

    private void setEventsForGroup1(Tile tile) {
        setOnMouseSwipeDownEvent(tile);
        setOnMouseSwipeRightEvent(tile);
    }

    // this logic was explained above.
    private int getGroup(Tile tile) {
        int row = board.getTileRow(tile);
        int col = board.getTileCol(tile);
        String rowCol = "" + row + col;
        int group;
        switch (rowCol) {
            case "00":
                group = 1;
                break;
            case "03":
                group = 2;
                break;
            case "30":
                group = 3;
                break;
            case "33":
                group = 4;
                break;
            case "01":
            case "02":
                group = 5;
                break;
            case "10":
            case "20":
                group = 6;
                break;
            case "13":
            case "23":
                group = 7;
                break;
            case "31":
            case "32":
                group = 8;
                break;
            default:
                group = 9;
                break;
        }

        return group;
    }

    // to assign the left event for the appropriate tile, we need to check the left and itself is valid for swipe or not first. If it is we need to assign the pressed and released actions to it.
    private void setOnMouseSwipeLeftEvent(Tile tile) {
        int row = board.getTileRow(tile);
        int col = board.getTileCol(tile);
        Tile leftTile = board.getSurface()[row][col - 1];
        if (isSwipeValid(tile, leftTile)) {
            setOnMousePressedEvent(tile);
            setOnMouseReleasedEvent(tile);
        }
    }
    // to assign the right event for the appropriate tile, we need to check the right and itself is valid for swipe or not first. If it is we need to assign the pressed and released actions to it.

    private void setOnMouseSwipeRightEvent(Tile tile) {
        int row = board.getTileRow(tile);
        int col = board.getTileCol(tile);
        Tile rightTile = board.getSurface()[row][col + 1];
        if (isSwipeValid(tile, rightTile)) {
            tile.setEventName("Swipe Right");
            setOnMousePressedEvent(tile);
            setOnMouseReleasedEvent(tile);
        }
    }
    // to assign the up event for the appropriate tile, we need to check the up and itself is valid for swipe or not first. If it is we need to assign the pressed and released actions to it.

    private void setOnMouseSwipeUpEvent(Tile tile) {
        int row = board.getTileRow(tile);
        int col = board.getTileCol(tile);
        Tile upTile = board.getSurface()[row - 1][col];
        if (isSwipeValid(tile, upTile)) {
            tile.setEventName("Swipe Up");
            setOnMousePressedEvent(tile);
            setOnMouseReleasedEvent(tile);
        }
    }

    // to assign the down event for the appropriate tile, we need to check the down and itself is valid for swipe or not first. If it is, we need to assign the pressed and released actions to it.
    private void setOnMouseSwipeDownEvent(Tile tile) {
        int row = board.getTileRow(tile);
        int col = board.getTileCol(tile);
        Tile downTile = board.getSurface()[row + 1][col];
        if (isSwipeValid(tile, downTile)) {
            tile.setEventName("Swipe Down");
            setOnMousePressedEvent(tile);
            setOnMouseReleasedEvent(tile);
        }
    }

    // when pressedtile and released tile position are proper for arrangement, we need to set board first and also record the movements.
    private void setOnMouseReleasedEvent(Tile tile) {
        tile.setOnMouseReleased(e -> {
            Tile releasedTile = getReleasedTile(e.getSceneX(), e.getSceneY());
            board.setReleasedTile(releasedTile);

            // this method checks whether pressedTile and releasedTile are proper for changing(whether they are sideways or top of each other)
            if (areTilesConsecutive(board.getPressedTile(), board.getReleasedTile())) {
                System.out.println("released x: " + e.getSceneX() + ", y: " + e.getSceneY());
                swapTiles(board.getPressedTile(), board.getReleasedTile());


                numberOfMovements++;
                button.setText("NUMBER OF MOVES " + numberOfMovements);
                // after swap operation completes,we will  refresh the pane to add the current tiles to it
                board.refresh();
                // after every swapping we also need to remove events and reassign the events.
                clearOnMouseSwipeEvents();
                setOnMouseSwipeEvents();
                System.out.println("Number of movements : " + numberOfMovements);
            }
        });
    }

    // this method will remove the all events of tiles after every swapping.
    private void clearOnMouseSwipeEvents() {
        Tile[][] boardSurface = board.getSurface();
        Tile tile;
        for (Tile[] tiles : boardSurface) {
            for (Tile value : tiles) {
                tile = value;
                if (tile.getOnMousePressed() != null)
                    tile.removeEventHandler(MouseEvent.MOUSE_PRESSED, tile.getOnMousePressed());
                if (tile.getOnMousePressed() != null)
                    tile.removeEventHandler(MouseEvent.MOUSE_RELEASED, tile.getOnMouseReleased());
            }
        }
    }

    // this method will bring the tile in position where mouse is released.
    private Tile getReleasedTile(double x, double y) {
        int col = (int) (x / 150);
        int row = (int) (y / 150);


        if (row <= 3 && col <= 3)
            return board.getSurface()[row][col];
        return null;
    }

    // this is only for checking whether event is working correctly or not.
    private void setOnMousePressedEvent(Tile tile) {
        tile.setOnMousePressed(e -> {
            System.out.println("pressed x: " + e.getSceneX() + ", y: " + e.getSceneY());
            board.setPressedTile(tile);
        });
    }

    // this will swap pressed and released tile if they are proper for swapping.
    private void swapTiles(Tile pressedTile, Tile releasedTile) {
        if (isSwipeValid(pressedTile, releasedTile)) {
            if (areTilesOnTopOfEachOther(pressedTile, releasedTile))
                swapTilesVertically(pressedTile, releasedTile);
            else if (areTilesSideBySide(pressedTile, releasedTile))
                swapTilesHorizontally(pressedTile, releasedTile);
        }
    }

    // if tiles side by side, the columns of tiles will be replaced.
    private void swapTilesHorizontally(Tile firstTile, Tile secondTile) {
        int row = board.getTileRow(firstTile);
        int firstTileCol = board.getTileCol(firstTile);
        int secondTileCol = board.getTileCol(secondTile);
        Tile[][] surface = board.getSurface();
        surface[row][firstTileCol] = secondTile;
        surface[row][secondTileCol] = firstTile;
    }

    // if tiles areTilesOnTopOfEachOther , the rows of tiles will be replaced.
    private void swapTilesVertically(Tile firstTile, Tile secondTile) {
        int firstTileRow = board.getTileRow(firstTile);
        int secondTileRow = board.getTileRow(secondTile);
        int col = board.getTileCol(firstTile);
        Tile[][] surface = board.getSurface();
        surface[firstTileRow][col] = secondTile;
        surface[secondTileRow][col] = firstTile;
    }

    // this will check if tiles side by side or top of each other.
    private boolean areTilesConsecutive(Tile pressedTile, Tile releasedTile) {
        return areTilesOnTopOfEachOther(pressedTile, releasedTile)
                || areTilesSideBySide(pressedTile, releasedTile);

    }

    // side by side means the difference between their column values is 1, and they are in the same row.
    private boolean areTilesSideBySide(Tile pressedTile, Tile releasedTile) {
        return Math.abs(board.getTileCol(pressedTile) - board.getTileCol(releasedTile)) == 1
                && Math.abs(board.getTileRow(pressedTile) - board.getTileRow(releasedTile)) == 0;
    }

    // areTilesOnTopOfEachOther means the difference between their row values is 1, and they are in the same column.
    private boolean areTilesOnTopOfEachOther(Tile pressedTile, Tile releasedTile) {
        return Math.abs(board.getTileRow(pressedTile) - board.getTileRow(releasedTile)) == 1
                && Math.abs(board.getTileCol(pressedTile) - board.getTileCol(releasedTile)) == 0;
    }

    // for swapping, the tiles cannot be starter end and pipe-static. If the first tile is empty-free and the other is a movableTile swap is valid or tile1 is movable
    // and tile 2 is EmptyFree the condition will be satisfied for swapping.
    private boolean isSwipeValid(Tile tile1, Tile tile2) {
        return tile1.isEmptyFreeTile() && tile2.isMovable() || tile1.isMovable() && tile2.isEmptyFreeTile();
    }


    // Getter and Setter methods.
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public FileReader getFileReader() {
        return fileReader;
    }

    public void setFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public int getCurrentLevelNo() {
        return currentLevelNo;
    }

    public void setCurrentLevelNo(int currentLevelNo) {
        this.currentLevelNo = currentLevelNo;
    }

    public int getNumberOfMovements() {
        return numberOfMovements;
    }

    public void setNumberOfMovements(int numberOfMovements) {
        this.numberOfMovements = numberOfMovements;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}