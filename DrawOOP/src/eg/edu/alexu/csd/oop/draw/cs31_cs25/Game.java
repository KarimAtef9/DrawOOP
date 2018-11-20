package eg.edu.alexu.csd.oop.draw.cs31_cs25;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Game {
	Identifier identifier;
	Shape shape;
	Game(Identifier identifier, Shape shape){
		this.identifier = identifier;
		this.shape = shape;
	}
}

enum Identifier {
	add, remove;
}
