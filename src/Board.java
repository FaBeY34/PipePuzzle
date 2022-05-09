import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Path;

import java.util.ArrayList;

public class Board  {
    int currentBoard;
    private GridPane pane;
    private Tile[][] surface;
    private Tile pressedTile;
    private Tile releasedTile;

    public Board() {
        pane = new GridPane();
        surface = new Tile[4][4];
        currentBoard++;
    }

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

    public void placeTileAndAppendToPane(Tile tile) {
        int row = getNextAvailableRow();
        int col = getNextAvailableCol();

        surface[row][col] = tile;

        pane.add(tile, col, row);
    }

    private int getNextAvailableCol() {
        for (Tile[] tiles : surface) {
            for (int j = 0; j < surface[0].length; j++) {
                // System.out.println("row "+i+" col :"+ j+surface[i][j]);
                if (tiles[j] == null)
                    return j;
            }
        }
        return -1;
    }

    private int getNextAvailableRow() {
        for (int i = 0; i < surface.length; i++) {
            for (int j = 0; j < surface[0].length; j++) {
                if (surface[i][j] == null)
                    return i;
            }
        }
        return -1;
    }

    public void refresh() {

        pane.getChildren().clear();
        for (int i = 0; i < surface.length; i++) {
            for (int j = 0; j < surface[0].length; j++) {
                pane.add(surface[i][j], j, i);
            }
        }

    }

    public int getTileRow(Tile tile) {
        for (int i = 0; i < surface.length; i++) {
            for (int j = 0; j < surface[0].length; j++) {
                if (surface[i][j] == tile)
                    return i;
            }
        }
        return -1;
    }

    public int getTileCol(Tile tile) {
        for (Tile[] tiles : surface) {
            for (int j = 0; j < surface[0].length; j++) {
                if (tiles[j] == tile)
                    return j;
            }
        }
        return -1;
    }




    public int getStarterRow() {
        Tile starter = getStarterTile();
        return getTileRow(starter);
    }

    public int getStarterCol() {
        Tile starter = getStarterTile();
        return getTileCol(starter);
    }

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
}

