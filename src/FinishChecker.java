import javafx.scene.layout.GridPane;
import javafx.scene.shape.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// this class enables us to check if the game is finished or not.We need to know board to check the game.WE will add the connected tiles to our solution list
// until reaching the end tile or finding unconnected pipe.
public class FinishChecker {

    // Variables
    private Board board;
    private ArrayList<Tile> solutionList = new ArrayList<>();
    Path path = new Path();

    // constructor
    public FinishChecker() {
    }

    // this method will check if the level is finished or not.To check it we will find starter tile first.Based on its propertyi we will continue to decide whether
    // a solution exist or not.
    public boolean isGameFinished() {
        Tile starter = board.getStarterTile();
        solutionList.add(starter);
        String starterProp = starter.getProperty();

        if (starterProp.equals(PipeProps.VERTICAL))
            return isVerticalSolutionAvailable();
        else
            return isHorizontalSolutionAvailable();

    }

    // this method will check whether two tiles can connect horizontally or not. if it is we will continue it until find the end tile.
    private boolean isHorizontalSolutionAvailable() {
        if (isLeftDirectionAvailable(board.getStarterTile())) {
            Tile nextTile = board.getTile(board.getStarterRow(), board.getStarterCol() - 1);
            solutionList.add(nextTile);
            while (!nextTile.getType().equals("End")) {
                nextTile = getNextTile(nextTile);
                if (nextTile == null) {
                    solutionList.clear();
                    break;
                }

            }
            return nextTile != null;
        }

        return false;
    }

    // this method will bring us the next tile based on current tile. According to direction, we can move to other connected pipe.
    private Tile getNextTile(Tile currentTile) {
        String direction = getDirection(currentTile);
        if (direction == null)
            return null;

        switch (direction) {
            case Directions.UP:
                return board.getTile(board.getTileRow(currentTile) - 1, board.getTileCol(currentTile));
            case Directions.DOWN:
                return board.getTile(board.getTileRow(currentTile) + 1, board.getTileCol(currentTile));
            case Directions.RIGHT:
                return board.getTile(board.getTileRow(currentTile), board.getTileCol(currentTile) + 1);
            case Directions.LEFT:
                return board.getTile(board.getTileRow(currentTile), board.getTileCol(currentTile) - 1);
        }
        return null;
    }

    // to find the direction we will check for every direction. We will control the all sides of the current tile.
    private String getDirection(Tile tile) {
        if (isUpDirectionAvailable(tile)) {
            return Directions.UP;
        } else if (isDownDirectionAvailable(tile)) {
            return Directions.DOWN;
        } else if (isRightDirectionAvailable(tile)) {
            return Directions.RIGHT;
        } else if (isLeftDirectionAvailable(tile)) {
            return Directions.LEFT;
        }

        return null;
    }

    // to check if a right path exist or not, we will find the right tile and check whether their connections established or not. Besides,  we must not pass the tile
    // that we have controlled before. That's why whenever we pass to another tile , we add it to our solution list. If this doesn't contain in our list, we can
    // surely say that right direction is available

    private boolean isRightDirectionAvailable(Tile tile) {
        int row = board.getTileRow(tile);
        int col = board.getTileCol(tile);
        if ((col + 1) == board.getSurface().length)
            return false;
        else {
            Tile rightTile = board.getTile(row, col + 1);
            return isPipe(rightTile) && isHorConnectionEstablished(tile, rightTile) && !solutionList.contains(rightTile);
        }
    }

    // to check if a left path exist or not, we will find the left tile and check whether their connections established or not. Besides,  we must not pass the tile
    // that we have controlled before. That's why whenever we pass to another tile , we add it to our solution list.If this doesn't contain in our list, we can
    // surely say that left direction is available

    private boolean isLeftDirectionAvailable(Tile tile) {
        int row = board.getTileRow(tile);
        int col = board.getTileCol(tile);
        if (col == 0)
            return false;
        else {
            Tile leftTile = board.getTile(row, col - 1);
            return isPipe(leftTile) && isHorConnectionEstablished(tile, leftTile) && !solutionList.contains(leftTile);
        }
    }
    // to check if an up path exist or not, we will find the up tile and check whether their connections established or not. Besides,  we must not pass the tile
    // that we have controlled before. That's why whenever we pass to another tile , we add it to our solution list.If this doesn't contain in our list, we can
    // surely say that up direction is available

    private boolean isUpDirectionAvailable(Tile tile) {
        int row = board.getTileRow(tile);
        int col = board.getTileCol(tile);
        if (row == 0)
            return false;
        else {
            Tile upTile = board.getTile(row - 1, col);
            return isPipe(upTile) && isVerConnectionEstablished(tile, upTile) && !solutionList.contains(upTile);
        }

    }
    // to check if an down path exist or not, we will find the up tile and check whether their connections established or not. Besides,  we must not pass the tile
    // that we have controlled before. That's why whenever we pass to another tile , we add it to our solution list.If this doesn't contain in our list, we can
    // surely say that down direction is available

    private boolean isDownDirectionAvailable(Tile tile) {
        int row = board.getTileRow(tile);
        int col = board.getTileCol(tile);
        if ((row + 1) == board.getSurface().length)
            return false;
        else {
            Tile downTile = board.getTile(row + 1, col);
            return isPipe(downTile) && isVerConnectionEstablished(tile, downTile) && !solutionList.contains(downTile);
        }


    }

    // To understand the  connection, we created a class to keep the up down bottom and left gates.
    // As an example, we want to go down, so the up tile should have bottom gate and bottom-tile should have up gate.
    // to go up,  the up tile should have bottom gate and bottom tile should have up gate. If we decide which tile is up or down,
    // then, according to that logic It's easy to understand whether a vertical connection has existed or not
    private boolean isVerConnectionEstablished(Tile tile1, Tile tile2) {
        int tile1Row = board.getTileRow(tile1);
        int tile2Row = board.getTileRow(tile2);
        List<String> propsOfBottomEntrance = PipeProps.getPropsOfBottomEntrance();
        List<String> propsOfUpEntrance = PipeProps.getPropsOfUpEntrance();
        if (tile1Row < tile2Row) {
            return propsOfUpEntrance.contains(tile2.getProperty()) && propsOfBottomEntrance.contains(tile1.getProperty());
        } else {
            return propsOfUpEntrance.contains(tile1.getProperty()) && propsOfBottomEntrance.contains(tile2.getProperty());
        }
    }
    // To understand the  connection, we created a class to keep the up down bottom and left gates.
    // As an example, we want to go left, so the right tile should have left gate and left-tile should have right gate.
    // to go up,  the right tile should have left gate and left tile should have right gated. If we decide which tile is right or left,
    // then, according to that logic It's easy to understand whether a vertical connection has existed or not

    private boolean isHorConnectionEstablished(Tile tile1, Tile tile2) {
        int tile1Col = board.getTileCol(tile1);
        int tile2Col = board.getTileCol(tile2);
        List<String> propsOfRightEntrance = PipeProps.getPropsOfRightEntrance();
        List<String> propsOfLeftEntrance = PipeProps.getPropsOfLeftEntrance();
        if (tile1Col < tile2Col) {
            return propsOfRightEntrance.contains(tile1.getProperty()) && propsOfLeftEntrance.contains(tile2.getProperty());
        } else {
            return propsOfRightEntrance.contains(tile2.getProperty()) && propsOfLeftEntrance.contains(tile1.getProperty());
        }

    }
    // this will check tile is pipe or not

    private boolean isPipe(Tile tile) {
        return !tile.getType().equals("Empty");
    }
    // this method will check whether two tiles can connect vertically or not. if it is we will continue it until finding the end tile.

    private boolean isVerticalSolutionAvailable() {
        if (isDownDirectionAvailable(board.getStarterTile())) {
            Tile nextTile = board.getTile(board.getTileRow(board.getStarterTile()) + 1, board.getTileCol(board.getStarterTile()));
            solutionList.add(nextTile);
            while (!(nextTile.getType().equals("End"))) {
                nextTile = getNextTile(nextTile);
                if (nextTile == null) {
                    solutionList.clear();
                    break;
                }
                solutionList.add(nextTile);

            }
            return nextTile != null;
        }
        if (isUpDirectionAvailable(board.getStarterTile())) {
            Tile nextTile = board.getTile(board.getTileRow(board.getStarterTile()) - 1, board.getTileCol(board.getStarterTile()));
            while (!(nextTile.getType().equals("End"))) {
                nextTile = getNextTile(nextTile);
                if (nextTile == null)
                    break;
            }
            return nextTile != null;
        }

        return false;


    }
    // Getter and Setter Methods.

    public Path getPath() {
        ArrayList<Tile> solutionList = getSolutionList();
        Tile starter = solutionList.get(0);
        int starterrow = board.getStarterRow();
        int startercol = board.getStarterCol();
        int[] coordinates = getStartercoordinate(starter, starterrow, startercol);
        path.getElements().add(new MoveTo(coordinates[0], coordinates[1]));
        addFirstPpath(starter);
        for (int i = 0; i < solutionList.size(); i++) {
            //if ()
        }

        /*We create a Path object which we later add directions for animation translation(�teleme).
        Path path = new Path();
        //This variables will be used for the positions of animation to start from.
        double x = 93;
        double y = 100;
        //Animation starts with element moving to x,y position.
        path.getElements().add(new MoveTo(x, y));
        //Level 1,2,3 have same animation so we defined them under this if block with condition of level less then 3
        if (board.currentBoard < 3) {
            //We start going down 360 pixels
            path.getElements().add(new VLineTo(360));
            //We go to x=160 and y=368 with 160 starting radius and 160 ending radius. The last 2 parameters are defining arc.
            path.getElements().add(new ArcTo(160, 160, 0, 160, 368, false, false));
            //We go 370 pixels to left
            path.getElements().add(new HLineTo(370));
        } else {
            //We start going down 240 pixels
            path.getElements().add(new VLineTo(240));
            //We go to x=160 and y=280 with 160 starting radius and 160 ending radius. The last 2 parameters are defining arc.
            path.getElements().add(new ArcTo(160, 160, 0, 160, 280, false, false));
            //We go 370 pixels to left.
            path.getElements().add(new HLineTo(370));
            //We go to x=370 and y=200 with 160 starting radius and 160 ending radius. The last 2 parameters are defining arc.
            path.getElements().add(new ArcTo(160, 160, 0, 370, 200, false, false));
            //We go 180 pixels up.
            path.getElements().add(new VLineTo(180));
        }
     /*   GridPane pane = board.getPane();
        Path gamePath = new Path();
        ArrayList<Tile> Tiles = getSolutionPath();
        Tile starter = board.getStarterTile();
        gamePath.getElements().add(new MoveTo(starter.getLayoutX()+75, starter.getLayoutY()+75));
        for (Tile tile : Tiles) {
            if (!tile.getType().equals("Starter"))
                gamePath.getElements().add(new LineTo(tile.getLayoutX()+75, tile.getLayoutY()+75));
        }
       // pane.getChildren().add(gamePath);
        return gamePath;*/

        return path;
    }

    private void addFirstPpath(Tile starter) {
        if (starter.getProperty().equals("Horizontal")) {
            path.getElements().add(new HLineTo(150));
        } else path.getElements().add(new VLineTo(150));
    }

    private int[] getStartercoordinate(Tile starter, int starterrow, int startercol) {
        int[] coordinates = new int[2];
        int x = coordinates[0], y = coordinates[1];
        if (starter.getProperty().equals("Horizontal")) {
            x = 150 + starterrow * 150;
            y = 75 + startercol * 150;
        } else x = 75 + starterrow * 150;
        y = 150 * startercol;

        return coordinates;

    }


    public Path getSolution() {


        GridPane pane = board.getPane();
        Path gamePath = new Path();
        ArrayList<Tile> Tiles = solutionList;
        Tile starter = board.getStarterTile();
        gamePath.getElements().add(new MoveTo(starter.getLayoutX() + 75, starter.getLayoutY() + 75));
        for (Tile tile : Tiles) {
            if (!tile.getType().equals("Starter"))
                gamePath.getElements().add(new LineTo(tile.getLayoutX() + 75, tile.getLayoutY() + 75));
        }
        pane.getChildren().add(gamePath);
        return gamePath;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }





    // Getter and Setter Methods

    public ArrayList<Tile> getSolutionList() {
        return solutionList;
    }


    public FinishChecker(Board board) {
        this.board = board;
    }
}
//

// This class will be used to keep the properties of tiles according to their entrance
class PipeProps {

    // Variables
    static final String VERTICAL = "Vertical";
    static final String HORIZONTAL = "Horizontal";
    static final String _00 = "00";
    static final String _01 = "01";
    static final String _10 = "10";
    static final String _11 = "11";

    // pipe00 pipe01 and vertical tiles has an up entrance that is possible to go up
    public static List<String> getPropsOfUpEntrance() {
        return new ArrayList<>(Arrays.asList(_00, _01, VERTICAL));
    }
    // pipe-10 pipe-11 and vertical tiles has a bottom entrance that is possible to go down

    public static List<String> getPropsOfBottomEntrance() {
        return new ArrayList<>(Arrays.asList(_10, _11, VERTICAL));
    }

    // pipe-01 pipe-11 and horizontal tiles has a right entrance that is possible to go right

    public static List<String> getPropsOfRightEntrance() {
        return new ArrayList<>(Arrays.asList(_01, _11, HORIZONTAL));
    }

    // pipe-10 pipe-00 and horizontal tiles has a left entrance that is possible to go left
    public static List<String> getPropsOfLeftEntrance() {
        return new ArrayList<>(Arrays.asList(_10, _00, HORIZONTAL));
    }

}

// this class will be used just to keep directions in a simple way.
class Directions {
    static final String LEFT = "Left";
    static final String RIGHT = "Right";
    static final String UP = "Up";
    static final String DOWN = "Down";
}