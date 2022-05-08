import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class PipeDrawer {
    Path path;
    GridPane pane;
    Scene scene;

    public PipeDrawer(Path path) {
        this.path = path;
    }

    public void startAnimation() {
        Circle circle = new Circle();
        circle.setRadius(10);
        circle.setFill(Color.RED);
        PathTransition pt = new PathTransition();
        pt.setPath(path);
        pt.setNode(circle);
        pt.setDuration(Duration.millis(4000));
        pt.setAutoReverse(false);
        pt.play();


    }

    public Scene getScene() {
        return scene;
    }


}
