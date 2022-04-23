import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;


public class QuickPuzzle extends Application {
    Level level;

    public QuickPuzzle() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Level level1 = new Level(1);
        //  level1.start(primaryStage);
        Board board = new Board();
        BoardMaker boardMaker = new BoardMaker(board);
        GridPane gridPane = board.getPane();
        //BorderPane borderPane = new BorderPane();
        VBox starterMenu = new VBox();
        starterMenu.setAlignment(Pos.BOTTOM_LEFT);

        Button playButton = new Button("", new ImageView(new Image("images/img.png", 200, 200, true, true)));
        Button ExitButton = new Button("", new ImageView(new Image("images/EXİT.PNG", 200, 200, true, true)));
        Button InfoButton = new Button("", new ImageView(new Image("images/Rulebutton.png", 200, 200, true, true)));
        playButton.setAlignment(Pos.CENTER);
        ExitButton.setAlignment(Pos.CENTER);
        InfoButton.setAlignment(Pos.CENTER);
        starterMenu.getChildren().addAll(playButton, InfoButton, ExitButton);
        starterMenu.setAlignment(Pos.CENTER);
        Scene scene = new Scene(starterMenu,300,600);
        primaryStage.setScene(scene);
        primaryStage.show();

        playButton.setOnMouseClicked(event -> startgame(gridPane, boardMaker));
        InfoButton.setOnMouseClicked(event -> openınfotext());
        ExitButton.setOnMouseClicked(event -> exitgame(primaryStage));


    }

    private void openınfotext() {
        Stage stage = new Stage();
    }

    private void exitgame(Stage primaryStage) {
        primaryStage.close();

    }

    private void startgame(GridPane gridPane, BoardMaker boardMaker) {
        createBoard(boardMaker);
        Stage stage = new Stage();
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
    }

    private void createBoard(BoardMaker boardMaker) {
        try {
            boardMaker.createBoard();
        } catch (FileNotFoundException e) {
            System.out.println("Level file is not found in FileReader class setFileAndScanner method");
        }
    }


}