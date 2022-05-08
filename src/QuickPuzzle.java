

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class QuickPuzzle extends Application {

    BoardMaker boardMaker = new BoardMaker();
    Button nextLevelBt = new Button("", new ImageView(new Image("images/NextButton.PNG", 100, 100, true, true)));
    Button playButton = new Button("", new ImageView(new Image("images/img.png", 200, 200, true, true)));
    Button button = new Button("NumberOfMovements " + boardMaker.numberOfMovements);


    @Override
    public void start(Stage primaryStage) throws Exception {
        Board board = new Board();
        boardMaker.setBoard(board);
        startIntroPanel(primaryStage);
        createfirstLevel(primaryStage);
        System.out.println(boardMaker.getBoard());
        FinishChecker finishChecker = new FinishChecker(boardMaker.getBoard());
        nextLevelBt.setOnMouseClicked(event1 -> {
            if (finishChecker.isGameFinished()) {
                boardMaker.numberOfMovements = 0;
                //PipeDrawer pD = new PipeDrawer(finishChecker.getPath());
                //pD.startAnimation();
                freshBoard();
                shownextLevel(primaryStage);
                createnextBoard();
                finishChecker.setBoard(boardMaker.getBoard());


            }
        });


    }

    ;


    private void freshBoard() {
        boardMaker.setBoard(new Board());
        // System.out.println(Arrays.deepToString(boardMaker.getBoard().getSurface()));

    }


    private void OpenFirstLevel(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();
        Button mainMenu = new Button("", new ImageView(new Image("images/MainButton.PNG", 100, 100, true, true)));
        GridPane level1 = boardMaker.getBoard().getPane();

        button.setText("NumberOfMovements : " + boardMaker.numberOfMovements);

        borderPane.setLeft(level1);
        VBox vBox = new VBox();
        vBox.getChildren().add(0, mainMenu);

        vBox.getChildren().add(1, boardMaker.button);
        vBox.getChildren().add(2, nextLevelBt);
        vBox.setSpacing(200);
        borderPane.setRight(vBox);
        vBox.setPadding(new Insets(20, 30, 20, 20));
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
        mainMenu.setOnMouseClicked(event -> {
            freshBoard();
            startIntroPanel(primaryStage);
        });
    }

    private void startIntroPanel(Stage primaryStage) {

        VBox starterMenu = new VBox();
        starterMenu.setAlignment(Pos.BOTTOM_LEFT);
        Button ExitButton = new Button("", new ImageView(new Image("images/EXİT.PNG", 200, 200, true, true)));
        //  Button InfoButton = new Button("", new ImageView(new Image("images/Rulebutton.png", 200, 200, true, true)));
        playButton.setAlignment(Pos.CENTER);
        ExitButton.setAlignment(Pos.CENTER);
        starterMenu.setAlignment(Pos.CENTER);
        starterMenu.getChildren().addAll(playButton, ExitButton);
        Scene scene = new Scene(starterMenu, 300, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void shownextLevel(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();
        GridPane currentGameBoard = boardMaker.getBoard().getPane();
        borderPane.setLeft(currentGameBoard);
        VBox vBox = new VBox();
        vBox.getChildren().add(0, nextLevelBt);
        boardMaker.button.setText("NUMBER OF MOVES: 0");
        vBox.getChildren().add(1, boardMaker.button);
        vBox.setSpacing(200);
        borderPane.setRight(vBox);
        vBox.setPadding(new Insets(20, 30, 20, 20));
        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    private void createnextBoard() {
        try {
            boardMaker.createBoard();
        } catch (FileNotFoundException e) {
            System.out.println("The file of level does not exist!!");
        }
    }

    private void createfirstLevel(Stage primaryStage) {
        playButton.setOnMouseClicked(event -> {
            try {
                boardMaker.createBoard();
                OpenFirstLevel(primaryStage);

            } catch (FileNotFoundException e) {
                System.out.println("File could not be found!");
            }
        });
    }
}


