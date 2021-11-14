package unsw.automata;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javafx.util.Duration;

/**
 * A JavaFX controller for the Conway's Game of Live Application.
 *
 * @author Robert Clifton-Everest
 *
 */
public class GameOfLifeController {
    @FXML
    private GridPane grid;
    
    @FXML
	private Pane pane;
	
	@FXML
	private Button tick;
	
	@FXML
    private Button play;
    
    private GameOfLife gol;

    private Timeline timeline;

    private boolean mouse = false;

	public GameOfLifeController() {
		gol = new GameOfLife();
		timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> {gol.tick();}));
		timeline.setCycleCount(Animation.INDEFINITE);
    }
    
    @FXML
	public void initialize() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j< 10; j++) {
                CheckBox checkBox = new CheckBox("");
                checkBox.selectedProperty().bindBidirectional(gol.cellProperty(i, j));
                
                grid.add(checkBox, i, j);
            }
        }
	}
	
	@FXML
	public void handleTick(MouseEvent event) {
		gol.tick();
	}
	
	@FXML
	public void handlePlay(MouseEvent event) {
		if (mouse) {
			timeline.stop();
            play.setText("Play");
            mouse = false;
		} else {
			timeline.play();
            play.setText("Stop");
            mouse = true;
		}
	}
}