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
    @Override
    public void start(Stage primaryStage) throws Exception {
        //BorderPane borderPane = new BorderPane();
        createIntroPanel(primaryStage);

      //  BoardMaker boardMaker = new BoardMaker(board);

        /*Button nextLevelBt = new Button("NEXT LEVEL"); // nextLevel btn
        nextLevelBt.setOnMouseClicked(e -> {
            if (finishChecker.isGameFinished()) {

            }
            PathDrawer drawer = new PathDrawer(finishChecker.getPath());
            drawer.startTranstion();
            createBoard(boardMaker);

        });*/
//        });





    }

    private void createIntroPanel(Stage primaryStage) {
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
        Scene scene = new Scene(starterMenu, 300, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
       // createFirstLevel(playButton);
    }


    private void openınfotext() {
        Stage stage = new Stage();
    }

    private void exitgame(Stage primaryStage) {
        primaryStage.close();

    }



    }






