import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

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
        GridPane boardpane;
        Tile newTile;
        ImageView imageView;
        // Exception burada handle edilebilir
        fileReader.setFileAndScanner(new File(currentLevel.getPath()));
        while (fileReader.hasNextLine()) {
            newTile = createTiles(fileReader.getNextLine());
            imageView = getImages(newTile);
            newTile.getChildren().add(imageView);
            // TODO: imageView'ı tile içerisine koy
            board.placeTileAndAppendToPane(newTile);
            // TODO: Bunu kaldır
            newTile.setOnMouseClicked(mouseEvent -> System.out.println("clicked"));
           // setOnSwipeEvent(newTile);

            // TODO: Burada tek tek event handling yapabilirsin -> setOnSwipeEvent(newTile);
        }
                setOnSwipeEvents();

        // TODO: Ya bu şekilde ya da tek tek yukarıda olduğu gibi event handling yap
        // TODO: board'daki tile'lerin hepsini burada pane'ye ekle board.appendTilesToPane()
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

    public void setOnSwipeEvents() {
        Tile[][] boardSurface = board.getSurface();
        Tile tile;
        for (int i = 0; i < boardSurface.length; i++) {
            for (int j = 0; j < boardSurface[i].length; j++) {
                tile = boardSurface[i][j];
               if (tile.isMovable())
                   setOnSwipeEvent(tile);
//                tile.setOnMouseClicked(mouseEvent -> System.out.println(mouseEvent.getSceneX()));
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
        setOnSwipeLeftEvent(tile);
        setOnSwipeRightEvent(tile);
        setOnSwipeUpEvent(tile);
        setOnSwipeDownEvent(tile);
    }

    private void setEventsForGroup8(Tile tile) {
        setOnSwipeLeftEvent(tile);
        setOnSwipeRightEvent(tile);
        setOnSwipeUpEvent(tile);
    }

    private void setEventsForGroup7(Tile tile) {
        setOnSwipeLeftEvent(tile);
        setOnSwipeUpEvent(tile);
        setOnSwipeDownEvent(tile);
    }

    private void setEventsForGroup6(Tile tile) {
        setOnSwipeRightEvent(tile);
        setOnSwipeUpEvent(tile);
        setOnSwipeDownEvent(tile);
    }

    private void setEventsForGroup5(Tile tile) {
        setOnSwipeLeftEvent(tile);
        setOnSwipeRightEvent(tile);
        setOnSwipeDownEvent(tile);
    }

    private void setEventsForGroup4(Tile tile) {
        setOnSwipeLeftEvent(tile);
        setOnSwipeUpEvent(tile);
    }

    private void setEventsForGroup3(Tile tile) {
        setOnSwipeRightEvent(tile);
        setOnSwipeUpEvent(tile);
    }

    private void setEventsForGroup2(Tile tile) {
        setOnSwipeDownEvent(tile);
        setOnSwipeLeftEvent(tile);
    }

    private void setEventsForGroup1(Tile tile) {
        setOnSwipeDownEvent(tile);
        setOnSwipeRightEvent(tile);
    }

    private int getGroup(Tile tile) {
        int row = tile.getRow(tile.getTileId());
        int col = tile.getColumn(tile.getTileId());
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

    private void setOnSwipeLeftEvent(Tile tile) {
        int row = tile.getRow(tile.getTileId());
        int col = tile.getColumn(tile.getTileId());
        Tile leftTile = board.getSurface()[row][col - 1];
        if (isSwipeValid(tile, leftTile)) {
            tile.setOnMouseDragged(handler -> {
                swapTilesHorizontally(tile, leftTile);
                System.out.println("setOnSwipeLeftEvent clicked");
                // TODO: değişim sonrası group değişebileceğinden dolayı event'lerin yeniden atanması gerekiyor.
            });
        }
    }


    private void setOnSwipeRightEvent(Tile tile) {
        int row = tile.getRow(tile.getTileId());
        int col = tile.getColumn(tile.getTileId());
        Tile rightTile = board.getSurface()[row][col + 1];
        if (isSwipeValid(tile, rightTile)) {
            tile.setOnMouseDragged(handler -> {
                swapTilesHorizontally(tile, rightTile);
                System.out.println("setOnSwipeRightEvent clicked");
            });
        }
    }

    private void setOnSwipeUpEvent(Tile tile) {
        int row = tile.getRow(tile.getTileId());
        int col = tile.getColumn(tile.getTileId());
        Tile upTile = board.getSurface()[row - 1][col];
        if (isSwipeValid(tile, upTile)) {
            tile.setOnMouseDragged(handler -> {
                swapTilesVertically(tile, upTile);
                System.out.println("setOnSwipeUpEvent clicked");
            });
        }
    }

    private void setOnSwipeDownEvent(Tile tile) {
        int row = tile.getRow(tile.getTileId());
        int col = tile.getColumn(tile.getTileId());
        Tile downTile = board.getSurface()[row + 1][col];
        if (isSwipeValid(tile, downTile)) {
            tile.setOnMouseDragged(handler -> {
                swapTilesVertically(tile, downTile);
                System.out.println("setOnSwipeDownEvent clicked");
            });
        }
    }

    private boolean isSwipeValid(Tile tile1, Tile tile2) {
        return tile1.isEmptyFreeTile() && tile2.isMovable() || tile1.isMovable() && tile2.isEmptyFreeTile();
    }

    private void swapTilesHorizontally(Tile firstTile, Tile secondTile) {
        int row = firstTile.getRow(firstTile.getTileId());
        int firstTileCol = firstTile.getColumn(firstTile.getTileId());
        int secondTileCol = secondTile.getColumn(secondTile.getTileId());
        Tile[][] surface = board.getSurface();
        surface[row][firstTileCol] = secondTile;
        surface[row][secondTileCol] = firstTile;
        GridPane boardpane = board.getPane();
        System.out.println("dsdsdfdfds");
        GridPane.setConstraints(firstTile,secondTileCol,row);
        GridPane.setConstraints(secondTile,firstTileCol,row);


    }

    private void swapTilesVertically(Tile firstTile, Tile secondTile) {
        int firstTileRow = firstTile.getRow(firstTile.getTileId());
        int secondTileRow = secondTile.getRow(secondTile.getTileId());
        int col = firstTile.getColumn(firstTile.getTileId());
        Tile[][] surface = board.getSurface();
        surface[firstTileRow][col] = secondTile;
        surface[secondTileRow][col] = firstTile;
        GridPane.setConstraints(firstTile,secondTileRow,col);
        GridPane.setConstraints(secondTile,firstTileRow,col);
    }

}
