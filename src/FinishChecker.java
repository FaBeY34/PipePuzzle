import javafx.scene.layout.GridPane;
import javafx.scene.shape.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinishChecker {
    private Board board;

    public ArrayList<Tile> getSolutionList() {
        return solutionList;
    }

    private ArrayList<Tile> solutionList = new ArrayList<>();
    Path path = new Path();


    public FinishChecker(Board board) {
        this.board = board;
    }

    public FinishChecker() {
    }


    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isGameFinished() {
        Tile starter = board.getStarterTile();
        solutionList.add(starter);
        String starterProp = starter.getProperty();

        if (starterProp.equals(PipeProps.VERTICAL))
            return isVerticalSolutionAvailable();
        else
            return isHorizontalSolutionAvailable();

    }

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
                //  solutionList.add(nextTile);
               // System.out.println(board.getIndex(solutionList));
            }
            return nextTile != null;
        }

        return false;
    }

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

    private boolean isPipe(Tile tile) {
        return !tile.getType().equals("Empty");
    }

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
              //  System.out.println(board.getIndex(solutionList));
            }
            return nextTile != null;
        }
        if (isUpDirectionAvailable(board.getStarterTile())) {
            Tile nextTile = board.getTile(board.getTileRow(board.getStarterTile()) - 1, board.getTileCol(board.getStarterTile()));
            while (!(nextTile instanceof End)) {
                nextTile = getNextTile(nextTile);
                if (nextTile == null)
                    break;
            }
            return nextTile != null;
        }

        return false;


    }

    public Path getPath() {
        ArrayList<Tile> solutionList = getSolutionList();
        Tile starter = solutionList.get(0);
        int starterrow = board.getStarterRow();
        int startercol = board.getStarterCol();
        int[] coordinates = getStartercoordinate(starter, starterrow, startercol);
        path.getElements().add(new MoveTo(coordinates[0],coordinates[1]));
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
        if (starter.getProperty().equals("Horizontal")){
            path.getElements().add(new HLineTo(150));
        }
        else path.getElements().add(new VLineTo(150));
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

    private Tile getStarter(ArrayList<Tile> tiles) {
        for (Tile tile : tiles) {
            if (tile.getType().equals("Starter"))
                return tile;
        }
        return null;
    }
}

class PipeProps {
    static final String VERTICAL = "Vertical";
    static final String HORIZONTAL = "Horizontal";
    static final String _00 = "00";
    static final String _01 = "01";
    static final String _10 = "10";
    static final String _11 = "11";

    public static List<String> getPropsOfUpEntrance() {
        return new ArrayList<>(Arrays.asList(_00, _01, VERTICAL));
    }

    public static List<String> getPropsOfBottomEntrance() {
        return new ArrayList<>(Arrays.asList(_10, _11, VERTICAL));
    }


    public static List<String> getPropsOfRightEntrance() {
        return new ArrayList<>(Arrays.asList(_01, _11, HORIZONTAL));
    }

    public static List<String> getPropsOfLeftEntrance() {
        return new ArrayList<>(Arrays.asList(_10, _00, HORIZONTAL));
    }
}

class Directions {
    static final String LEFT = "Left";
    static final String RIGHT = "Right";
    static final String UP = "Up";
    static final String DOWN = "Down";
}