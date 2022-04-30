import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
        fileReader.setFileAndScanner(new File(currentLevel.getPath()));
        while (fileReader.hasNextLine()) {
            newTile = createTiles(fileReader.getNextLine());
            imageView = getImages(newTile);
            newTile.getChildren().add(imageView);
            board.placeTileAndAppendToPane(newTile);
        }
        setOnMouseSwipeEvents();
    }

    private void printTileEvents() {
        for (Tile[] row :
                board.getSurface()) {
            for (Tile tile :
                    row) {
                System.out.println("id: " + tile.getTileId() + ", event: " + tile.getEventName());
            }
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

    public void setOnMouseSwipeEvents() {
        Tile[][] boardSurface = board.getSurface();
        Tile tile;
        for (int i = 0; i < boardSurface.length; i++) {
            for (int j = 0; j < boardSurface[i].length; j++) {
                tile = boardSurface[i][j];
                if (tile.isMovable())
                    setOnSwipeEvent(tile);
            }
        }
    }

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

    private void setOnMouseSwipeLeftEvent(Tile tile) {
        int row = board.getTileRow(tile);
        int col = board.getTileCol(tile);
        Tile leftTile = board.getSurface()[row][col - 1];
        if (isSwipeValid(tile, leftTile)) {
            setOnMousePressedEvent(tile);
            setOnMouseReleasedEvent(tile);
        }
    }

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

    private void setOnMouseReleasedEvent(Tile tile) {
        tile.setOnMouseReleased(e -> {
            Tile releasedTile = getReleasedTile(e.getSceneX(), e.getSceneY());
            board.setReleasedTile(releasedTile);
            // x, y değerler için null check yapmayı unutmayın
            if (areTilesConsecutive(board.getPressedTile(), board.getReleasedTile())) {
                System.out.println("released x: " + e.getSceneX() + ", y: " + e.getSceneY());
                swapTiles(board.getPressedTile(), board.getReleasedTile());
                board.refresh();
                clearOnMouseSwipeEvents();
                setOnMouseSwipeEvents();
            }
        });
    }

    private void clearOnMouseSwipeEvents() {
        Tile[][] boardSurface = board.getSurface();
        Tile tile;
        for (int i = 0; i < boardSurface.length; i++) {
            for (int j = 0; j < boardSurface[i].length; j++) {
                tile = boardSurface[i][j];
                if (tile.getOnMousePressed() != null)
                    tile.removeEventHandler(MouseEvent.MOUSE_PRESSED, tile.getOnMousePressed());
                if (tile.getOnMousePressed() != null)
                    tile.removeEventHandler(MouseEvent.MOUSE_RELEASED, tile.getOnMouseReleased());
            }
        }
    }

    private Tile getReleasedTile(double x, double y) {
        int col = (int) (x / 150);
        int row = (int) (y / 150);
        return board.getSurface()[row][col];
    }

    private void setOnMousePressedEvent(Tile tile) {
        tile.setOnMousePressed(e -> {
            System.out.println("pressed x: " + e.getSceneX() + ", y: " + e.getSceneY());
            board.setPressedTile(tile);
        });
    }

    private void swapTiles(Tile pressedTile, Tile releasedTile) {
        if (isSwipeValid(pressedTile, releasedTile)) {
            if (areTilesOnTopOfEachOther(pressedTile, releasedTile))
                swapTilesVertically(pressedTile, releasedTile);
            else if (areTilesConsecutive(pressedTile, releasedTile))
                swapTilesHorizontally(pressedTile, releasedTile);
        }
    }

    private void swapTilesHorizontally(Tile firstTile, Tile secondTile) {
        int row = board.getTileRow(firstTile);
        int firstTileCol = board.getTileCol(firstTile);
        int secondTileCol = board.getTileCol(secondTile);
        Tile[][] surface = board.getSurface();
        surface[row][firstTileCol] = secondTile;
        surface[row][secondTileCol] = firstTile;
    } // second 3108 pipe01, first 3703 emptyfree

    private void swapTilesVertically(Tile firstTile, Tile secondTile) {
        int firstTileRow = board.getTileRow(firstTile);
        int secondTileRow = board.getTileRow(secondTile);
        int col = board.getTileCol(firstTile);
        Tile[][] surface = board.getSurface();
        surface[firstTileRow][col] = secondTile;
        surface[secondTileRow][col] = firstTile;
    }

    private boolean areTilesConsecutive(Tile pressedTile, Tile releasedTile) {
        return areTilesOnTopOfEachOther(pressedTile, releasedTile)
                || areTilesSideBySide(pressedTile, releasedTile)
                && areTilesNotDiagonal(pressedTile, releasedTile);
    }

    private boolean areTilesNotDiagonal(Tile pressedTile, Tile releasedTile) {
        return !(areTilesSideBySide(pressedTile, releasedTile) && areTilesOnTopOfEachOther(pressedTile, releasedTile));
    }

    private boolean areTilesSideBySide(Tile pressedTile, Tile releasedTile) {
        return Math.abs(board.getTileCol(pressedTile) - board.getTileCol(releasedTile)) == 1;
    }

    private boolean areTilesOnTopOfEachOther(Tile pressedTile, Tile releasedTile) {
        return Math.abs(board.getTileRow(pressedTile) - board.getTileRow(releasedTile)) == 1;
    }

    private boolean isSwipeValid(Tile tile1, Tile tile2) {
        return tile1.isEmptyFreeTile() && tile2.isMovable() || tile1.isMovable() && tile2.isEmptyFreeTile();
    }


}
