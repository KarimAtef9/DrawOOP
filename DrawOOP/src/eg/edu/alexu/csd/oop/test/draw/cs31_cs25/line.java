package eg.edu.alexu.csd.oop.test.draw.cs31_cs25;

import java.awt.Graphics;
import java.awt.Point;

import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class line extends ShapeConcrete{
	public line() {
		properties.put("x2", 0.0);
		properties.put("y2", 0.0);
	}
	
	@Override
	public void draw(Object canvas) {
		// TODO Auto-generated method stub
		GraphicsContext gc =(GraphicsContext) ((Canvas) canvas).getGraphicsContext2D();
		
//		((GraphicsContext)canvas).setLineWidth(1.0);
		gc.setStroke((javafx.scene.paint.Paint)this.getColor());
		gc.setFill((Paint)this.getFillColor());
		gc.strokeLine(this.position.getX(), this.position.getY()
				, this.getProperties().get("x2"), this.getProperties().get("y2"));
	}
}
