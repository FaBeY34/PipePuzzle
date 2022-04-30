import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class Board extends Node {
    private GridPane pane;
    private Tile[][] surface;
    private Tile pressedTile;
    private Tile releasedTile;

    public Board() {
        pane = new GridPane();
        surface = new Tile[4][4];
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
        for (int i = 0; i < surface.length; i++) {
            for (int j = 0; j < surface[0].length; j++) {
                if (surface[i][j] == null)
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
        for (int i = 0; i < surface.length; i++) {
            for (int j = 0; j < surface[0].length; j++) {
                if (surface[i][j] == tile)
                    return j;
            }
        }
        return -1;
    }


    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
