package eg.edu.alexu.csd.oop.test.draw.cs31_cs25;

import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class triangle extends ShapeConcrete {
	public triangle () {
		properties.put("x2", 0.0);
		properties.put("y2", 0.0);
		properties.put("x3", 0.0);
		properties.put("y3", 0.0);
	}
	
	@Override
	public void draw(Object canvas) {
		double [] px = {this.getPosition().getX(),this.getProperties().get("x2")
				,this.getProperties().get("x3")};
		double [] py = {this.getPosition().getY(),this.getProperties().get("y2")
				,this.getProperties().get("y3")};
		
		GraphicsContext gc =(GraphicsContext) ((Canvas) canvas).getGraphicsContext2D();
		
		gc.setStroke((Paint)this.getColor());
		gc.setFill((Paint)this.getFillColor());
		gc.strokePolygon(px,py,3);
		gc.fillPolygon(px, py, 3);
	}
	
}
