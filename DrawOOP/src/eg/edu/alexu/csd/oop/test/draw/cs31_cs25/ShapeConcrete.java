package eg.edu.alexu.csd.oop.test.draw.cs31_cs25;

import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public class ShapeConcrete implements Shape {
	protected Point position = new Point(0,0);
	protected Object color = Color.BLACK;
	protected Object fillColor = null;
	Map<String, Double> properties = new HashMap<>();
	
	@Override
	public void setPosition(Object position) {
		// TODO Auto-generated method stub
		Point positionTemp = (Point) position;
		this.position.x = positionTemp.x;
		this.position.y = positionTemp.y;
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return this.position;
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		// TODO Auto-generated method stub
		this.properties = properties;
	}

	@Override
	public Map<String, Double> getProperties() {
		// TODO Auto-generated method stub
		return this.properties;
	}

	@Override
	public void setColor(Object color) {
		// TODO Auto-generated method stub
		this.color = color;
	}

	@Override
	public Object getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}

	@Override
	public void setFillColor(Object color) {
		// TODO Auto-generated method stub
		this.fillColor = color;
	}

	@Override
	public Object getFillColor() {
		// TODO Auto-generated method stub
		return this.fillColor;
	}

	@Override
	public void draw(Object canvas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		ShapeConcrete sh = new ShapeConcrete();
		sh.setPosition(position);
		sh.setColor(color);
		sh.setFillColor(fillColor);
		sh.setProperties(properties);
		return sh;
		
	}
}
