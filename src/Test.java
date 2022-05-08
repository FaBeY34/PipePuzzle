import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;

public class Test extends Application {
    BoardMaker boardMaker = new BoardMaker();
    Button nextLevelBt = new Button("NEXTLEVEL");
    Button previousBt = new Button("PREVİOUSLEVEL");
    Button playButton = new Button("", new ImageView(new Image("images/img.png", 200, 200, true, true)));

    @Override
    public void start(Stage primaryStage) throws Exception {



        //Button playButton = new Button("", new ImageView(new Image("images/img.png", 200, 200, true, true)));
        Board board = new Board();
        boardMaker.setBoard(board);
        //BorderPane borderPane = new BorderPane();

        startIntroPanel(primaryStage);
        createfirstLevel(primaryStage);
        FinishChecker finishChecker = new FinishChecker(board);

        GridPane pane = board.getPane();
        // pane.getChildren().add(2, nextLevel);
        pane.setOnMouseReleased(event -> {
            {

            }
            if (finishChecker.isGameFinished()) {

                //  PipeDrawer pD = new PipeDrawer(finishChecker.getPath());
                // pD.startAnimation();
                nextLevelBt.setOnMouseClicked(event1 -> {
                    freshBoard();
                    createnextBoard();
                    shownextLevel(primaryStage);
                    finishChecker.setBoard(boardMaker.getBoard());

                });

            }

        });


    }

    private void freshBoard() {
        boardMaker.setBoard(new Board());
        // System.out.println(Arrays.deepToString(boardMaker.getBoard().getSurface()));

    }

    private void OpenFirstLevel(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();
        Button mainMenu = new Button("RETURN TO MAIN MENU");
        GridPane level1 = boardMaker.getBoard().getPane();
        level1.setAlignment(Pos.CENTER);
        borderPane.setCenter(level1);
        borderPane.setRight(nextLevelBt);
        borderPane.setLeft(mainMenu);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
        mainMenu.setOnMouseClicked(event -> startIntroPanel(primaryStage));
    }

    private void shownextLevel(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();
        GridPane currentGameBoard = boardMaker.getBoard().getPane();
        borderPane.setCenter(currentGameBoard);
        borderPane.setRight(nextLevelBt);
        borderPane.setLeft(previousBt);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
        previousBt.setOnMouseClicked(event -> openPreviousLevel(scene, primaryStage));

    }

    private void openPreviousLevel(Scene previousScene, Stage primaryStage) {
        int previousLevel = boardMaker.getCurrentLevelNo() - 1;
        boardMaker.setCurrentLevelNo(previousLevel);
        primaryStage.setScene(previousScene);
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

    public void startIntroPanel(Stage primaryStage) {
        // Button playButton = new Button("", new ImageView(new Image("images/img.png", 200, 200, true, true)));
        VBox starterMenu = new VBox();
        starterMenu.setAlignment(Pos.BOTTOM_LEFT);

        //Button playButton = new Button("", new ImageView(new Image("images/img.png", 200, 200, true, true)));
        Button ExitButton = new Button("", new ImageView(new Image("images/EXİT.PNG", 200, 200, true, true)));
        Button InfoButton = new Button("", new ImageView(new Image("images/Rulebutton.png", 200, 200, true, true)));
        playButton.setAlignment(Pos.CENTER);
        ExitButton.setAlignment(Pos.CENTER);
        InfoButton.setAlignment(Pos.CENTER);
        starterMenu.getChildren().addAll(playButton, InfoButton, ExitButton);
        starterMenu.setAlignment(Pos.CENTER);
        Scene scene = new Scene(starterMenu, 300, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void openınfotext() {
        Stage stage = new Stage();
    }

    private void exitgame(Stage primaryStage) {
        primaryStage.close();

    }


}







