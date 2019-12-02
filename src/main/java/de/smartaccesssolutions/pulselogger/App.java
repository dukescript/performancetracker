package de.smartaccesssolutions.pulselogger;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import com.sun.javafx.perf.PerformanceTracker;
import java.time.Duration;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * JavaFX App
 */
public class App extends Application {

    private final long[] frameTimes = new long[1000];
    private int frameTimeIndex = 0;
    private boolean arrayFilled = false;

    @Override
    public void start(Stage stage) {
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 600, 800);
        stage.setScene(scene);
        final Label label = getPerformanceTrackerLabel(scene);
        borderPane.setTop(label);
      
        stage.show();
    }

    private Label getPerformanceTrackerLabel(Scene scene) {
        final Label label = new Label("FPS");
        final PerformanceTracker sceneTracker = PerformanceTracker.getSceneTracker(scene);
        Timeline timeline = new Timeline(); timeline.setCycleCount(Timeline.INDEFINITE);
        javafx.util.Duration duration = javafx.util.Duration.seconds(1);
        EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                label.setText(String.format("Current frame rate: %.3f",sceneTracker.getInstantFPS()));
            }
        };

        KeyFrame keyFrame = new KeyFrame(duration, onFinished, null, null);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        return label;
    }

    public static void main(String[] args) {
        launch();
    }

}
