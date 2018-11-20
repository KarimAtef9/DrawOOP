package eg.edu.alexu.csd.oop.draw.cs31_cs25;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RoundRectangle extends square{
	public RoundRectangle () {
		super();
		properties.put("width", 0.0);
	}
	
@Override
public void draw(Object canvas) {
	// TODO Auto-generated method stub
			GraphicsContext gc =(GraphicsContext) ((Canvas) canvas).getGraphicsContext2D();
			
			gc.setStroke((Color)this.getColor());
			gc.setFill((Color)this.getFillColor());
			gc.fillRoundRect(this.getPosition().getX(), this.getPosition().getY(),
					this.getProperties().get("length"), this.getProperties().get("width"),2,2);
			gc.strokeRoundRect(this.getPosition().getX(), this.getPosition().getY(),
					this.getProperties().get("length"), this.getProperties().get("width"),2,2);
}
}
