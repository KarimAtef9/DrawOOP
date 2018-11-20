package eg.edu.alexu.csd.oop.draw.cs31_cs25;

import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class circle extends ShapeConcrete {
	public circle () {
		properties.put("radius", 0.0);
	}
	
	@Override
	public void draw(Object canvas) {
		// TODO Auto-generated method stub
		GraphicsContext gc =(GraphicsContext) ((Canvas) canvas).getGraphicsContext2D();
		
		gc.setStroke((Paint)this.getColor());
		gc.setFill((Paint)this.getFillColor());
		gc.strokeOval(this.getPosition().getX() -this.getProperties().get("radius"),
				this.getPosition().getY() -this.getProperties().get("radius")
				,2* this.getProperties().get("radius"),2 * this.getProperties().get("radius"));
		gc.fillOval(this.getPosition().getX() -this.getProperties().get("radius"),
				this.getPosition().getY() -this.getProperties().get("radius")
				,2* this.getProperties().get("radius"),2 * this.getProperties().get("radius"));
	}
	
}
