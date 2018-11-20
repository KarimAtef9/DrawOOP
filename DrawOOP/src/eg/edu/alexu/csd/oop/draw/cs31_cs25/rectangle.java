package eg.edu.alexu.csd.oop.draw.cs31_cs25;

import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class rectangle extends square {
	public rectangle () {
		super();
		properties.put("width", 0.0);
	}
	
	@Override
	public void draw(Object canvas) {
		// TODO Auto-generated method stub
		GraphicsContext gc =(GraphicsContext) ((Canvas) canvas).getGraphicsContext2D();
		
		gc.setStroke((Color)this.getColor());
		gc.setFill((Color)this.getFillColor());
		gc.fillRect(this.getPosition().getX(), this.getPosition().getY(),
				this.getProperties().get("length"), this.getProperties().get("width"));
		gc.strokeRect(this.getPosition().getX(), this.getPosition().getY(),
				this.getProperties().get("length"), this.getProperties().get("width"));	
	}
}
