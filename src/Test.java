import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {

//        Tile[][] tiles = {{new Tile(1, "Pipe", "01"), new Tile(2, "Pipe", "Horizontal"), new Tile(3, "Pipe", "Horizontal"), new Tile(4, "Starter", "Horizontal")},
//                {new Tile(5, "Pipe", "Vertical"), new Tile(6, "Pipe", "11"), new Tile(7, "Empty", "None"), new Tile(8, "Empty", "None")},
//                {new Tile(9, "End", "Vertical"), new Tile(10, "Pipe", "Vertical"), new Tile(11, "Pipe", "None"), new Tile(12, "Pipe", "None")},
//                {new Tile(13, "Pipe", "None"), new Tile(14, "Pipe", "None"), new Tile(15, "Pipe", "None"), new Tile(16, "Pipe", "None")}
//
//        };

        Tile[][] tiles = {
                {new Tile(1, "Starter", "Vertical"), new Tile(2, "Pipe", "Horizontal"), new Tile(3, "Pipe", "Horizontal"), new Tile(4, "Starter", "Horizontal")},
                {new Tile(5, "Pipe", "Vertical"), new Tile(6, "Pipe", "11"), new Tile(7, "Empty", "None"), new Tile(8, "End", "Vertical")},
                {new Tile(9, "Pipe", "01"), new Tile(10, "Pipe", "Horizontal"), new Tile(11, "Pipe", "Horizontal"), new Tile(12, "Pipe", "00")},
                {new Tile(13, "Pipe", "None"), new Tile(14, "Pipe", "None"), new Tile(15, "Pipe", "None"), new Tile(16, "Pipe", "None")}
        };

        Board board = new Board();
        board.setSurface(tiles);


        FinishChecker finishChecker = new FinishChecker(board);
        if (finishChecker.isGameFinished())
            System.out.println("game finished");
        else System.out.println("game is not finished");

        Button btn = new Button();
        btn.addActionListener(e -> {
            if (finishChecker.isGameFinished()) {
                PathDrawer drawer = new PathDrawer(finishChecker.getPath());
                drawer.startTranstion();
            }
        });
    }
}





