package eg.edu.alexu.csd.oop.test.draw.cs31_cs25;

import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class square extends ShapeConcrete {
	public square () {
		properties.put("length", 0.0);
	}
	
	@Override
	public void draw(Object canvas) {
		// TODO Auto-generated method stub
		GraphicsContext gc =(GraphicsContext) ((Canvas) canvas).getGraphicsContext2D();
		
		gc.setStroke((Paint)this.getColor());
		gc.setFill((Paint)this.getFillColor());
		gc.fillRect(this.getPosition().getX(), this.getPosition().getY(),
				this.getProperties().get("length"), this.getProperties().get("length"));
		gc.strokeRect(this.getPosition().getX(), this.getPosition().getY(),
				this.getProperties().get("length"), this.getProperties().get("length"));
	}
}
