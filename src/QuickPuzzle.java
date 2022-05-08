

    import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;


    public class QuickPuzzle extends Application {

        BoardMaker boardMaker = new BoardMaker();
        Button nextLevelBt = new Button("NEXT LEVEL");
        Button previousBt = new Button("PREVIOUS LEVEL");
        Button playButton = new Button("", new ImageView(new Image("images/img.png", 200, 200, true, true)));

        public QuickPuzzle() {

        }

        @Override
        public void start(Stage primaryStage) throws Exception {
            Button playButton = new Button("", new ImageView(new Image("images/img.png", 200, 200, true, true)));
            Board board = new Board();
            boardMaker.setBoard(board);
            //BorderPane borderPane = new BorderPane();

            startIntroPanel(primaryStage);
            createfirstLevel(primaryStage);
            System.out.println(boardMaker.getBoard());
            FinishChecker finishChecker = new FinishChecker(boardMaker.getBoard());
            // System.out.println(finishChecker.getSolutionPath());

            GridPane pane = board.getPane();
            // pane.getChildren().add(2, nextLevel);
            pane.setOnMouseReleased(event -> {
                {

                }
                if (finishChecker.isGameFinished()) {

                    PipeDrawer pD = new PipeDrawer(finishChecker.getPath());
                    pD.startAnimation();
                    primaryStage.setScene(pD.getScene());

                    nextLevelBt.setOnMouseClicked(event1 -> {
                        freshBoard();
                        createnextBoard();
                        shownextLevel(primaryStage);
                        finishChecker.setBoard(boardMaker.getBoard());

                    });

                }

            });


        }

  /* if (finishChecker.isGameFinished()) {

                //  PipeDrawer pD = new PipeDrawer(finishChecker.getPath());
                // pD.startAnimation();
                nextLevelBt.setOnMouseClicked(event1 -> {
                    freshBoard();
                    createnextBoard();
                    shownextLevel(primaryStage);
                    finishChecker.setBoard(boardMaker.getBoard());

                });

            }*/


        private void freshBoard() {
            boardMaker.setBoard(new Board());
            // System.out.println(Arrays.deepToString(boardMaker.getBoard().getSurface()));

        }

        private void OpenFirstLevel(Stage primaryStage) {
            BorderPane borderPane = new BorderPane();
            Button mainMenu = new Button("RETURN TO MAIN MENU");
            GridPane level1 = boardMaker.getBoard().getPane();
            //  level1.setAlignment(Pos.CENTER);
            //pane.getChildren().add(level1);
            //pane.getChildren().add(mainMenu);
            //pane.getChildren().add(nextLevelBt);
            borderPane.setLeft(level1);
            VBox vBox = new VBox();
            vBox.getChildren().add(0, mainMenu);
            vBox.getChildren().add(1, nextLevelBt);
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

        private void shownextLevel(Stage primaryStage) {
            BorderPane borderPane = new BorderPane();
            GridPane currentGameBoard = boardMaker.getBoard().getPane();
            borderPane.setCenter(currentGameBoard);
            borderPane.setRight(nextLevelBt);
            borderPane.setLeft(previousBt);
            Scene scene = new Scene(borderPane);
            primaryStage.setScene(scene);
            primaryStage.show();
            //  previousBt.setOnMouseClicked(event -> openPreviousLevel(scene, primaryStage));

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
    }


