package eg.edu.alexu.csd.oop.draw.cs31_cs25;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;

public class Engine implements DrawingEngine {
	List<Shape> shapes = new ArrayList<Shape>();
	List<Game> history = new ArrayList<Game>();
	int currentGameIndex = -1;
	LinkedList<Class<? extends Shape>> supportedShapes = 
			new LinkedList<Class<? extends Shape>>();
	
	@Override
	public void refresh(Object canvas) {
		// TODO Auto-generated method stub
		int lengh = shapes.size();
		for (int i = 0; i < lengh; i++) {
			shapes.get(i).draw(canvas);
		}
	}

	@Override
	public void addShape(Shape shape) {
		// TODO Auto-generated method stub
		shapes.add(shape);
		if (currentGameIndex != history.size() - 1) {
			history.clear();
			history.add(new Game(Identifier.add, shape));
			currentGameIndex = 0;
			return;
		}
		if (history.size() < 20) {
			history.add(new Game(Identifier.add, shape));
			currentGameIndex++;
		}
		else {
			history.remove(0);
			history.add(new Game(Identifier.add, shape));
		}
	}

	@Override
	public void removeShape(Shape shape) {
		// TODO Auto-generated method stub
		int index = shapes.indexOf(shape);
		if (index != -1) {
			if (currentGameIndex != history.size() - 1) {
				history.clear();
				history.add(new Game(Identifier.remove, shape));
				currentGameIndex = 0;
				return;
			}
			shapes.remove(index);
			history.add(new Game(Identifier.remove, shape));
			currentGameIndex++;
		}
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		// TODO Auto-generated method stub
		try {
			int index = shapes.indexOf(oldShape);
			if (index != -1) {
				shapes.set(index, newShape);
			}
		}
		catch (Exception e) {
		}
	}

	@Override
	public Shape[] getShapes() {
		// TODO Auto-generated method stub
		Shape[] shapesArray = new Shape[shapes.size()];
		return shapes.toArray(shapesArray);
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		// TODO Auto-generated method stub
		if (supportedShapes.isEmpty()) {
			supportedShapes.add(line.class);
			supportedShapes.add(circle.class);
			supportedShapes.add(rectangle.class);
			supportedShapes.add(ellipse.class);
			supportedShapes.add(triangle.class);
			supportedShapes.add(square.class);
			ClassLoader classLoader = DrawingEngineConcrete.class.getClassLoader();
			Class aClass = null;
			try {
				aClass = classLoader.loadClass("eg.edu.alexu.csd.oop.draw.RoundRectangle");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			//System.out.println("aClass.getName() = " + aClass.getName());
			supportedShapes.add(aClass);

		}
		return supportedShapes;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		if (history.size() > 0 && currentGameIndex >= 0) {
			Game temp = history.get(currentGameIndex);
			switch (temp.identifier) {
				case add:
					if (shapes.size() > 0)
						shapes.remove(shapes.size() - 1);
					break;
				case remove:
					shapes.add(temp.shape);
					break;
			}
			currentGameIndex--;
		}
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		if (currentGameIndex < history.size() - 1) {
			currentGameIndex++;
			if (history.size() > 0) {
				Game temp = history.get(currentGameIndex);
				switch (temp.identifier) {
					case add:
						shapes.add(temp.shape);
						break;
					case remove:
						if (shapes.size() > 0)
							shapes.remove(shapes.size() - 1);
				}
			}
		}
	}

	@Override
	public void save(String path) {
		// TODO Auto-generated method stub
		//saving using XML
		File fileSaving = new File("save");
		try {
			FileWriter fileWriter = new FileWriter(fileSaving);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write("<paint>");
			bufferedWriter.newLine();
			for (int i = 0; i < shapes.size(); i++) {
				bufferedWriter.write("<shape id=\"" + shapes.get(i).getClass().getName() + "\">");
				bufferedWriter.newLine();
				Point tempPostion = (Point) shapes.get(i).getPosition();
				bufferedWriter.write("<x>" + tempPostion.x + "</x>");
				bufferedWriter.newLine();
				bufferedWriter.write("<y>" + tempPostion.y + "</y>");
				bufferedWriter.newLine();
				bufferedWriter.write("<map>");
				bufferedWriter.newLine();
				HashMap<String, Double> propertiesTemp = (HashMap<String, Double>) shapes.get(i).getProperties();
				Set<String> keys = propertiesTemp.keySet();
				for (String s: keys) {
					bufferedWriter.write("<" + s + ">" + propertiesTemp.get(s)+"</" + s + ">");
					bufferedWriter.newLine();
				}
				bufferedWriter.write("</map>");
				bufferedWriter.newLine();
				bufferedWriter.write("<color>" + shapes.get(i).getColor()+ "</color>");
				bufferedWriter.newLine();
				bufferedWriter.write("<fillcolor>" + shapes.get(i).getFillColor()+ "</fillcolor>");
				bufferedWriter.newLine();
				bufferedWriter.write("</shape>");


			}
			bufferedWriter.newLine();
			bufferedWriter.write("</paint>");
			bufferedWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load(String path) {
		// TODO Auto-generated method stub
		
	}

}
