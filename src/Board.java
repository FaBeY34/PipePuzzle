import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;

public class Board {
    private GridPane pane;
    private Filereader filereader;
    private boardMaker boardMaker;


    public Filereader getFilereader() {
        return filereader;
    }

    public boardMaker getBoardMaker() {
        return boardMaker;
    }

    public Board(Level level) throws FileNotFoundException {
        filereader = new Filereader(level);
        filereader.readFile();
        boardMaker = new boardMaker();
        pane = new GridPane();
        createBoard();


    }

    public void createBoard() {
        while (filereader.hasNextline()) {
            Tile newtile = boardMaker.createTiles(filereader.getNextline());
            boardMaker.arrangetiles(newtile);
            ImageView imageView = boardMaker.getImages(newtile);
            addImages(imageView, newtile);

        }
    }

    private void addImages(ImageView imageView, Tile newtile) {
        int row = newtile.getRow(newtile.getTileId());
        int colomn = newtile.getColumn(newtile.getTileId());
        pane.add(imageView, colomn, row);
        pane.setAlignment(Pos.CENTER);

    }

    public GridPane getPane() {
        return pane;
    }
}
