//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.*;
//import javafx.stage.Stage;
//
//import java.io.FileNotFoundException;
//
//public class Test2 extends Application {
//
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        //Level level1 = new Level(1);
//        //  level1.start(primaryStage);
//        //  HBox starterMenu = new HBox();
//        //Button playButton = new Button("",
//        //      new ImageView(new Image("images/img.png", 300, 200, true, true)));
//        //starterMenu.getChildren().add(playButton);
//        //Scene scene = new Scene(starterMenu, 300, 300);
//
//        // level1
//
//
//        // play button
//       /* BorderPane bp1= new BorderPane();
//        playButton.setOnAction(e -> {
//            primaryStage.setScene(new Scene(bp1, 800, 450));
//            Board board = null;
//            try {
//                board = new Board(level1);
//            } catch (FileNotFoundException ex) {
//                ex.printStackTrace();
//            }
//            */
//        Level level1 = new Level(1);
//        Board board = new Board(level1);
//           GridPane gridPane = board.getPane();
//      //  GridPane gridPane = new GridPane();
//        //  Image image = new Image("images/CURVED00.jpeg", 100, 100, true, true);
//       // ImageView imageView = new ImageView(image);
//      //  gridPane.add(imageView,0,0,);
//        // gridPane.add();
//        //gridPane.getChildren().add(new ImageView(new Image("images/ENDHORIZONTAL.PNG")));
//        Scene scene2 = new Scene(gridPane);
//
//        // primaryStage.setScene(scene);
//        // GridPane gridPane = new GridPane();
//        // gridPane.add(new ImageView(new Image("images")),0,0);
//        //Starter starter = new Starter(1,"starter","horizontal");
//        //starter.getChildren().add(new ImageView(new Image("images/Curved00.PNG")));
//        // gridPane.add(starter,0,0);
//        // Scene scene = new Scene(gridPane);
//        primaryStage.setScene(scene2);
//        primaryStage.show();
//
//
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//
//}
