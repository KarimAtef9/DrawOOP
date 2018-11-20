package GUI2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MainController {
	@FXML
	private Canvas canvas = new Canvas(870,590);
	private GraphicsContext gc = canvas.getGraphicsContext2D();
	
	
	public void drawRec (ActionEvent event) {
//		Point center = null;
//		center.x = 10;center.y=10;
//		Shape rec = new rectangle(center,20,10);
//		rec.draw(canvas);
		gc.setFill(Color.RED);
		gc.setStroke(Color.BLACK);
		gc.fillRect(50, 50, 300, 300);
		gc.strokeRect(10, 10, 100, 300);
		
	}
	
}
