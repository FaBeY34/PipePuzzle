import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Path;

import java.util.ArrayList;

public class Board {

    // a Board consists of the level of board(currentBoard),a pane and a surface which is a two-dimensional Tile array.
    // Pressed and released Tile enables us to check  Tile's events.

    //Variables
    int currentBoard;
    private GridPane pane;
    private Tile[][] surface;
    private Tile pressedTile;
    private Tile releasedTile;

    // Constructor
    public Board() {
        pane = new GridPane();
        surface = new Tile[4][4];
        currentBoard++;
    }


    // this method add tiles to surface and our grid pane.
    public void placeTileAndAppendToPane(Tile tile) {
        int row = getNextAvailableRow();
        int col = getNextAvailableCol();
        surface[row][col] = tile;
        pane.add(tile, col, row);
    }

    // We are finding appropriate columns in surface to fill it with tiles

    private int getNextAvailableCol() {
        for (Tile[] tiles : surface) {
            for (int j = 0; j < surface[0].length; j++) {
                if (tiles[j] == null)
                    return j;
            }
        }
        return -1;
    }

    // We are finding appropriate rows in surface to fill it with tiles

    private int getNextAvailableRow() {
        for (int i = 0; i < surface.length; i++) {
            for (int j = 0; j < surface[0].length; j++) {
                if (surface[i][j] == null)
                    return i;
            }
        }
        return -1;
    }
  // This method enables us to refresh our pane to assign the events correctly after every tile movement.

    public void refresh() {

        pane.getChildren().clear();
        for (int i = 0; i < surface.length; i++) {
            for (int j = 0; j < surface[0].length; j++) {
                pane.add(surface[i][j], j, i);
            }
        }

    }
    // This method find the row position of the specific tile.

    public int getTileRow(Tile tile) {
        for (int i = 0; i < surface.length; i++) {
            for (int j = 0; j < surface[0].length; j++) {
                if (surface[i][j] == tile)
                    return i;
            }
        }
        return -1;
    }

    // This method find the row position of the specific column.

    public int getTileCol(Tile tile) {
        for (Tile[] tiles : surface) {
            for (int j = 0; j < surface[0].length; j++) {
                if (tiles[j] == tile)
                    return j;
            }
        }
        return -1;
    }

     // This method finds the starter tile in the current board.

    public int getStarterRow() {
        Tile starter = getStarterTile();
        return getTileRow(starter);
    }
    // This method finds the starter column tile in the current board.

    public int getStarterCol() {
        Tile starter = getStarterTile();
        return getTileCol(starter);
    }
    // This method finds the starter row tile in the current board.

    public Tile getStarterTile() {
        for (Tile[] row : surface) {
            for (Tile tile : row) {
                if (tile.getType().equals("Starter")) {
                    return tile;
                }
            }
        }
        return null;
    }
    // this method finds the specific tile with the given  row and column values

    public Tile getTile(int row, int col) {
        if (row < 0 || col < 0)
            return null;
        if (row >= surface.length || col >= surface[0].length)
            return null;

        return surface[row][col];
    }


    public ArrayList<Integer> getIndex(ArrayList<Tile> path) {
        int index;
        ArrayList<Integer> indexList = new ArrayList<>();
        for (Tile tile : path) {
            index = 4 * getTileRow(tile) + getTileCol(tile);
            indexList.add(index);
        }
        return indexList;

    }
    // Getter and Set Methods

    public Tile[][] getSurface() {
        return surface;
    }


    public GridPane getPane() {
        return pane;
    }

    public Tile getPressedTile() {
        return pressedTile;
    }

    public Tile getReleasedTile() {
        return releasedTile;
    }

    public void setPressedTile(Tile pressedTile) {
        this.pressedTile = pressedTile;
    }

    public void setReleasedTile(Tile releasedTile) {
        this.releasedTile = releasedTile;
    }
}

