

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;

// this class will enable us tp create puzzle game.
public class QuickPuzzle extends Application {

    // the variables which will be used to create every level.
    BoardMaker boardMaker = new BoardMaker();
    Button nextLevelBt = new Button("", new ImageView(new Image("images/NextButton.PNG", 100, 100, true, true)));
    Button playButton = new Button("", new ImageView(new Image("images/img.png", 200, 200, true, true)));
    Button button = new Button("NumberOfMovements " + boardMaker.numberOfMovements);
    Button mainButton = new Button("", new ImageView(new Image("images/MainButton.PNG", 100, 100, true, true)));
    Scene scene;
    FinishChecker finishChecker;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // opening first screen
        startIntroPanel(primaryStage);

        // creating first board of level
        createFirstBoard(primaryStage);

        // whenever play button is clicked, it will start the first level
        playButton.setOnMouseClicked(event -> OpenFirstLevel(primaryStage));
        //OpenFirstLevel(primaryStage);

        // setting our finish checker with the first level of board.
        finishChecker = new FinishChecker(boardMaker.getBoard());

        // whenever next button is clicked, we start number of movements with 0, fresh the board to take the next board, showing the level and creating
        // the next of level board. Setting our finish-checker to control current board
        nextLevelBt.setOnMouseClicked(event1 -> {
            if (finishChecker.isGameFinished()) {
                boardMaker.numberOfMovements = 0;
                freshBoard();
                showNextLevel(primaryStage);
                createNextBoard();
                finishChecker.setBoard(boardMaker.getBoard());

            }
        });
    }
    // we couldn't implement this part
    private void startAnimation(Scene scene, Stage primaryStage) {
        GridPane pane = boardMaker.getBoard().getPane();
        Circle circle = new Circle(10, Color.RED);
        Path path = new Path();
        path.getElements().add(new MoveTo(75, 0));
        path.getElements().add(new VLineTo(450));
        path.getElements().add(new HLineTo(550));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setPath(path);
        pathTransition.setDuration(Duration.millis(4000));
        pane.getChildren().add(circle);
        pathTransition.isAutoReverse();
        pathTransition.setNode(circle);
        pathTransition.play();
        scene = new Scene(pane);
        //scen
        primaryStage.setScene(scene);
        primaryStage.show();
        // pathTransition.setOnFinished(event -> cre);
    }


    // set the board-maker with a new board.
    private void freshBoard() {
        boardMaker.setBoard(new Board());
        // System.out.println(Arrays.deepToString(boardMaker.getBoard().getSurface()));

    }

    // opens the first level screen. We create a Vbox and adding the nodes of level1(the board and buttons) and show it in the stage.Whenever main button is clicked
    // it will return to intro panel.
    private void OpenFirstLevel(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();

        GridPane level1 = boardMaker.getBoard().getPane();

        button.setText("NumberOfMovements : " + boardMaker.numberOfMovements);

        borderPane.setLeft(level1);
        VBox vBox = new VBox();
        vBox.getChildren().add(0, mainButton);

        vBox.getChildren().add(1, boardMaker.button);
        vBox.getChildren().add(2, nextLevelBt);
        vBox.setSpacing(200);
        borderPane.setRight(vBox);
        vBox.setPadding(new Insets(20, 30, 20, 20));
        scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
        mainButton.setOnMouseClicked(event -> {
            //freshBoard();
            // clearBoard();
            startIntroPanel(primaryStage);
        });


    }



    // it will generate the login screen
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
        //  playButton.setOnMouseClicked(event ->{

    }



    // this method will open the next level based on current board. However, after the last level we need to finish the game
    // that's why we are creating a text includes you win. Whenever next button is clicked of last level, it will show up.
    private void showNextLevel(Stage primaryStage) {
        System.out.println(boardMaker);
        if (boardMaker.getCurrentLevelNo() < 6) {
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
        } else {
            VBox vBox = new VBox();
            // vBox.setMaxSize(100,5);
            Text text = new Text(" YOU WİN");
            text.setFont(Font.font(100));
            text.setTextAlignment(TextAlignment.CENTER);
            vBox.getChildren().add(text);
            scene = new Scene(vBox);
            primaryStage.setScene(scene);
            primaryStage.show();


        }

    }

    // Creating the board of  next level.
    private void createNextBoard() {
        try {
            boardMaker.createBoard();
        } catch (FileNotFoundException e) {
            System.out.println("The file of level does not exist!!");
        }
    }

    // creating the board of first level.

    private void createFirstBoard(Stage primaryStage) {

        try {
            Board board = new Board();
            boardMaker.setBoard(board);
            boardMaker.createBoard();


        } catch (FileNotFoundException e) {
            System.out.println("File could not be found!");
        }
    }


}



