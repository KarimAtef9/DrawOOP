package GUI;

import java.awt.Point;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import com.sun.javafx.geom.Point2D;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.test.draw.cs31_cs25.DrawingEngineConcrete;
import eg.edu.alexu.csd.oop.test.draw.cs31_cs25.Engine;
import eg.edu.alexu.csd.oop.test.draw.cs31_cs25.ShapeConcrete;
import eg.edu.alexu.csd.oop.test.draw.cs31_cs25.circle;
import eg.edu.alexu.csd.oop.test.draw.cs31_cs25.ellipse;
import eg.edu.alexu.csd.oop.test.draw.cs31_cs25.line;
import eg.edu.alexu.csd.oop.test.draw.cs31_cs25.rectangle;
import eg.edu.alexu.csd.oop.test.draw.cs31_cs25.roundRectangle;
import eg.edu.alexu.csd.oop.test.draw.cs31_cs25.square;
import eg.edu.alexu.csd.oop.test.draw.cs31_cs25.triangle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MainController implements Initializable {
	private Engine de = new Engine();
	@FXML
	private Canvas canvas;
	@FXML
	private GraphicsContext gc;
	@FXML
	private Pane canPane = new Pane();
	@FXML
	private ColorPicker colorPicker;
	@FXML
	private ColorPicker colorFiller;
	@FXML
	private Color stroke = Color.WHITE;
	@FXML
	private Color fill = Color.WHITE;
	@FXML
	private TextField txt1;
	@FXML
	private TextField txt2;
	@FXML
	private TextField txt3;
	@FXML
	private Label lbl1;
	@FXML
	private Label lbl2;
	@FXML
	private Label lbl3;
	@FXML
	private Label lbl4;
	@FXML
	private Button d;
	private int i = 0;
	private boolean check = false;
	private Shape current;
	private int centerX;
	private int centerY;
	int undos = 0;
	
	
	public void strokeColor(ActionEvent event) {
		stroke = colorPicker.getValue();
	}
	
	public void fillColor(ActionEvent event) {
		fill = colorFiller.getValue();
	}
	
	public void drawLine (ActionEvent event) {
		txt1.setVisible(true);
		txt2.setVisible(true);
		lbl1.setVisible(true);
		lbl2.setVisible(true);
		lbl2.setText("P2");
		txt2.setPromptText("x,y");
		d.setVisible(true);
		check = false;
				
		d.setOnAction(e -> {
			boolean t1 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}[,]{1}[0-9]{1,}[.]?[0-9]{0,}", txt1.getText());
			boolean t2 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}[,]{1}[0-9]{1,}[.]?[0-9]{0,}", txt2.getText());
			if (t1 && t2) {
				Shape l = new line ();
				String center = txt1.getText();
				String[] s = center.split(",");
				Point p = new Point();
				p.x = (int) Double.parseDouble(s[0]);
				p.y = (int) Double.parseDouble(s[1]);
				l.setPosition(p);
				String point2 = txt2.getText();
				String[] s2 = point2.split(",");				
				l.getProperties().put("x2", Double.parseDouble(s2[0]));
				l.getProperties().put("y2", Double.parseDouble(s2[1]));
				
				l.setColor((Object)stroke);
				l.setFillColor((Object)fill);
				l.draw(canvas);
				de.addShape(l);
			}
			txt1.clear();
			txt2.clear();
			txt1.setVisible(false);
			txt2.setVisible(false);
			lbl1.setVisible(false);
			lbl2.setVisible(false);
			d.setVisible(false);
			
		});
		canPane.setOnMousePressed(e -> {
			centerX = (int)e.getX();
			centerY = (int)e.getY();
		});

		canPane.setOnMouseReleased(e -> {
			Shape l = new line ();
			l.setPosition(new Point(centerX,centerY));
			double x = e.getX();
			double y = e.getY();
			l.getProperties().put("x2", x);
			l.getProperties().put("y2", y);
			l.setColor((Object)stroke);
			l.setFillColor((Object)fill);
			l.draw(canvas);
			de.addShape(l);
			txt1.clear();
			txt2.clear();
			txt1.setVisible(false);
			txt2.setVisible(false);
			lbl1.setVisible(false);
			lbl2.setVisible(false);
			d.setVisible(false);
		});
		
//		l.setColor(Color.BLACK);
//		l.setFillColor(Color.BLUE);
//		l.setPosition(new Point (10,10));
//		l.draw(canvas);
	}
	
	public void drawSquare (ActionEvent event) {
		txt1.setVisible(true);
		txt2.setVisible(true);
		txt2.setPromptText("x");
		lbl1.setVisible(true);
		lbl2.setVisible(true);
		lbl2.setText("Length");
		d.setVisible(true);
		check = false;
		
		d.setOnAction(e -> {
			boolean t1 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}[,]{1}[0-9]{1,}[.]?[0-9]{0,}", txt1.getText());
			boolean t2 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}", txt2.getText());
			if (t1 && t2) {
				Shape sq = new square();
				sq.getProperties().put("length", Double.parseDouble(txt2.getText()));
				String center = txt1.getText();
				String[] s = center.split(",");
				Point p = new Point();
				p.x = (int) Double.parseDouble(s[0]);
				p.y = (int) Double.parseDouble(s[1]);
				sq.setPosition(p);
				sq.setColor((Object)stroke);
				sq.setFillColor((Object)fill);
				sq.draw(canvas);
				de.addShape(sq);
			}
			txt1.clear();
			txt2.clear();
			txt1.setVisible(false);
			txt2.setVisible(false);
			lbl1.setVisible(false);
			lbl2.setVisible(false);
			d.setVisible(false);
		});
		
		canPane.setOnMousePressed(e -> {
			centerX = (int)e.getX();
			centerY = (int)e.getY();
			
		});

		canPane.setOnMouseReleased(e -> {
			Shape sq = new square();
			sq.setPosition(new Point(centerX,centerY));
			Point p = (Point) sq.getPosition();
			double x = e.getX() - p.x;
			double y = e.getY() - p.y;
			sq.getProperties().put("length", x);
			sq.setColor((Object)stroke);
			sq.setFillColor((Object)fill);
			sq.draw(canvas);
			de.addShape(sq);
			txt1.setVisible(false);
			txt2.setVisible(false);
			lbl1.setVisible(false);
			lbl2.setVisible(false);
			d.setVisible(false);
		});
	}
	
	public void drawCircle (ActionEvent event) {
		txt1.setVisible(true);
		txt2.setVisible(true);
		txt2.setPromptText("r");
		lbl1.setVisible(true);
		lbl2.setVisible(true);
		lbl2.setText("Radius");
		d.setVisible(true);
		check = false;
		
		d.setOnAction(e -> {
			boolean t1 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}[,]{1}[0-9]{1,}[.]?[0-9]{0,}", txt1.getText());
			boolean t2 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}", txt2.getText());
			if (t1 && t2) {
				Shape cr = new circle();
				cr.getProperties().put("radius", Double.parseDouble(txt2.getText()));
				String center = txt1.getText();
				String[] s = center.split(",");
				Point p = new Point();
				p.x = (int) Double.parseDouble(s[0]);
				p.y = (int) Double.parseDouble(s[1]);
				cr.setPosition(p);
				cr.setColor((Object)stroke);
				cr.setFillColor((Object)fill);
				cr.draw(canvas);
				de.addShape(cr);
			}
			txt1.clear();
			txt2.clear();
			txt1.setVisible(false);
			txt2.setVisible(false);
			lbl1.setVisible(false);
			lbl2.setVisible(false);
			d.setVisible(false);
			
		});
		
		canPane.setOnMousePressed(e -> {
			centerX = (int)e.getX();
			centerY= (int)e.getY();
			;
		});

		canPane.setOnMouseReleased(e -> {
			Shape cr = new circle();
			cr.setPosition(new Point(centerX,centerY));
			Point p = (Point) cr.getPosition();
			double x = e.getX() - p.x;
			double y = e.getY() - p.y;
			double r = Math.sqrt(x*x + y*y);
			cr.getProperties().put("radius", r);
			cr.setColor((Object)stroke);
			cr.setFillColor((Object)fill);
			cr.draw(canvas);
			de.addShape(cr);
			txt1.setVisible(false);
			txt2.setVisible(false);
			lbl1.setVisible(false);
			lbl2.setVisible(false);
			d.setVisible(false);
		});
	}
	
	public void drawEllipse (ActionEvent event) {
		txt1.setVisible(true);
		txt2.setVisible(true);
		txt2.setPromptText("a");
		txt3.setPromptText("b");
		txt3.setVisible(true);
		lbl1.setVisible(true);
		lbl2.setVisible(true);
		lbl3.setVisible(true);
		lbl2.setText("a");
		lbl3.setText("b");
		d.setVisible(true);
		check = false;
		
		
		
		d.setOnAction(e -> {
			boolean t1 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}[,]{1}[0-9]{1,}[.]?[0-9]{0,}", txt1.getText());
			boolean t2 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}", txt2.getText());
			boolean t3 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}", txt3.getText());
			if (t1 && t2 && t3) {
				Shape el = new ellipse();
				el.getProperties().put("radius", Double.parseDouble(txt2.getText()));
				el.getProperties().put("b", Double.parseDouble(txt3.getText()));
				String center = txt1.getText();
				String[] s = center.split(",");
				Point p = new Point();
				p.x = (int) Double.parseDouble(s[0]);
				p.y = (int) Double.parseDouble(s[1]);
				el.setPosition(p);
				el.setColor((Object)stroke);
				el.setFillColor((Object)fill);
				el.draw(canvas);
				de.addShape(el);
			}
			txt1.clear();
			txt2.clear();
			txt3.clear();
			txt1.setVisible(false);
			txt2.setVisible(false);
			txt3.setVisible(false);
			lbl1.setVisible(false);
			lbl2.setVisible(false);
			lbl3.setVisible(false);
			d.setVisible(false);
			
		});
		
		canPane.setOnMousePressed(e -> {
			centerX = (int)e.getX();
			centerY = (int)e.getY();
			
		});

		canPane.setOnMouseReleased(e -> {
			Shape el = new ellipse();
			el.setPosition(new Point(centerX,centerY));
			Point p = (Point) el.getPosition();
			double x = e.getX() - p.x;
			double y = e.getY() - p.y;
			el.getProperties().put("radius", x);
			el.getProperties().put("b", y);
			el.setColor((Object)stroke);
			el.setFillColor((Object)fill);
			el.draw(canvas);
			de.addShape(el);
			txt1.setVisible(false);
			txt2.setVisible(false);
			txt3.setVisible(false);
			lbl1.setVisible(false);
			lbl2.setVisible(false);
			lbl3.setVisible(false);
			d.setVisible(false);
		});
	}
	
	public void drawTriangle (ActionEvent event) {
		txt1.setVisible(true);
		txt2.setVisible(true);
		txt2.setPromptText("x,y");
		txt3.setPromptText("x,y");
		txt3.setVisible(true);
		lbl1.setVisible(true);
		lbl2.setVisible(true);
		lbl3.setVisible(true);
		lbl2.setText("P2");
		lbl3.setText("P3");
		d.setVisible(true);
		check = true;
		
		

		d.setOnAction(e -> {
			boolean t1 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}[,]{1}[0-9]{1,}[.]?[0-9]{0,}", txt1.getText());
			boolean t2 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}[,]{1}[0-9]{1,}[.]?[0-9]{0,}", txt2.getText());
			boolean t3 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}[,]{1}[0-9]{1,}[.]?[0-9]{0,}", txt3.getText());
			if (t1 && t2 && t3) {
				Shape tri = new triangle();
				String center = txt1.getText();
				String[] s = center.split(",");
				Point p = new Point();
				p.x = (int) Double.parseDouble(s[0]);
				p.y = (int) Double.parseDouble(s[1]);
				tri.setPosition(p);
				String[] s2 = txt2.getText().split(",");
				tri.getProperties().put("x2", Double.parseDouble(s2[0]));
				tri.getProperties().put("y2", Double.parseDouble(s2[1]));
				String[] s3 = txt3.getText().split(",");
				tri.getProperties().put("x3", Double.parseDouble(s3[0]));
				tri.getProperties().put("y3", Double.parseDouble(s3[1]));
				
				tri.setColor((Object)stroke);
				tri.setFillColor((Object)fill);
				tri.draw(canvas);
				de.addShape(tri);
			}
			txt1.clear();
			txt2.clear();
			txt3.clear();
			i = 0;
			txt1.setVisible(false);
			txt2.setVisible(false);
			txt3.setVisible(false);
			lbl1.setVisible(false);
			lbl2.setVisible(false);
			lbl3.setVisible(false);
			d.setVisible(false);
			
		});
		int x[] = new int[3];
		int y[] = new int[3];
		
		if (check) {
			i = 0;
			canPane.setOnMouseClicked (e -> {
					x[i] = (int)e.getX();
					y[i] = (int)e.getY();
					if (check)
						i++;
					if (i == 3 && check) {
						Shape tri = new triangle();
						tri.setPosition(new Point (x[0],y[0]));
						tri.getProperties().put("x2", (double) x[1]);
						tri.getProperties().put("x3", (double) x[2]);
						tri.getProperties().put("y2", (double) y[1]);
						tri.getProperties().put("y3", (double) y[2]);
						tri.setColor((Object)stroke);
						tri.setFillColor((Object)fill);
						tri.draw(canvas);
						i = 0;
						de.addShape(tri);
					}
					txt1.setVisible(false);
					txt2.setVisible(false);
					txt3.setVisible(false);
					lbl1.setVisible(false);
					lbl2.setVisible(false);
					lbl3.setVisible(false);
					d.setVisible(false);
			});
		}
		
//		canPane.setOnMouseClicked (e -> {
//			x[1] = (int)e.getX();
//			y[1] = (int)e.getY();
//		});
//		canPane.setOnMouseClicked (e -> {
//			x[2] = (int)e.getX();
//			y[2] = (int)e.getY();
//			tri.setPosition(new Point(x[0],y[0]));


//		});
		
	}
	
	public void drawRec (ActionEvent event) {
		txt1.setVisible(true);
		txt2.setVisible(true);
		txt2.setPromptText("x");
		txt3.setPromptText("y");
		txt3.setVisible(true);
		lbl1.setVisible(true);
		lbl2.setVisible(true);
		lbl3.setVisible(true);
		lbl2.setText("Length");
		lbl3.setText("Width");
		d.setVisible(true);
		
		d.setOnAction(e -> {
			boolean t1 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}[,]{1}[0-9]{1,}[.]?[0-9]{0,}", txt1.getText());
			boolean t2 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}", txt2.getText());
			boolean t3 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}", txt3.getText());
			if (t1 && t2 && t3) {
				Shape rec = new rectangle();
				rec.getProperties().put("length", Double.parseDouble(txt2.getText()));
				rec.getProperties().put("width", Double.parseDouble(txt3.getText()));
				String center = txt1.getText();
				String[] s = center.split(",");
				Point p = new Point();
				p.x = (int) Double.parseDouble(s[0]);
				p.y = (int) Double.parseDouble(s[1]);
				rec.setPosition(p);
				rec.setColor((Object)stroke);
				rec.setFillColor((Object)fill);
				rec.draw(canvas);
				de.addShape(rec);
			}
			txt1.clear();
			txt2.clear();
			txt3.clear();
			txt1.setVisible(false);
			txt2.setVisible(false);
			txt3.setVisible(false);
			lbl1.setVisible(false);
			lbl2.setVisible(false);
			lbl3.setVisible(false);
			d.setVisible(false);
			
		});
		canPane.setOnMousePressed(e -> {
			centerX = (int)e.getX();
			centerY = (int)e.getY();
			
		});

		canPane.setOnMouseReleased(e -> {
			Shape rec = new rectangle();
			rec.setPosition(new Point(centerX,centerY));
			Point p = (Point) rec.getPosition();
			double x = e.getX() - p.x;
			double y = e.getY() - p.y;
			rec.getProperties().put("length", x);
			rec.getProperties().put("width", y);
			rec.setColor((Object)stroke);
			rec.setFillColor((Object)fill);
			rec.draw(canvas);
			de.addShape(rec);
			txt1.setVisible(false);
			txt2.setVisible(false);
			txt3.setVisible(false);
			lbl1.setVisible(false);
			lbl2.setVisible(false);
			lbl3.setVisible(false);
			d.setVisible(false);
		});
//		rec.setProperties();
		
//		gc.setFill(Color.RED);
//		gc.fillRect(50, 50, 150, 150);
		
	}

	public void drawRoundRec (ActionEvent event) {
		txt1.setVisible(true);
		txt2.setVisible(true);
		txt2.setPromptText("x");
		txt3.setPromptText("y");
		txt3.setVisible(true);
		lbl1.setVisible(true);
		lbl2.setVisible(true);
		lbl3.setVisible(true);
		lbl2.setText("Length");
		lbl3.setText("Width");
		d.setVisible(true);
		
		d.setOnAction(e -> {
			boolean t1 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}[,]{1}[0-9]{1,}[.]?[0-9]{0,}", txt1.getText());
			boolean t2 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}", txt2.getText());
			boolean t3 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}", txt3.getText());
			if (t1 && t2 && t3) {
				Shape rec = new roundRectangle();
				rec.getProperties().put("length", Double.parseDouble(txt2.getText()));
				rec.getProperties().put("width", Double.parseDouble(txt3.getText()));
				String center = txt1.getText();
				String[] s = center.split(",");
				Point p = new Point();
				p.x = (int) Double.parseDouble(s[0]);
				p.y = (int) Double.parseDouble(s[1]);
				rec.setPosition(p);
				rec.setColor((Object)stroke);
				rec.setFillColor((Object)fill);
				rec.draw(canvas);
				de.addShape(rec);
			}
			txt1.clear();
			txt2.clear();
			txt3.clear();
			txt1.setVisible(false);
			txt2.setVisible(false);
			txt3.setVisible(false);
			lbl1.setVisible(false);
			lbl2.setVisible(false);
			lbl3.setVisible(false);
			d.setVisible(false);
			
		});
		canPane.setOnMousePressed(e -> {
			centerX = (int)e.getX();
			centerY = (int)e.getY();
			
		});

		canPane.setOnMouseReleased(e -> {
			Shape rec = new roundRectangle();
			rec.setPosition(new Point(centerX,centerY));
			Point p = (Point) rec.getPosition();
			double x = e.getX() - p.x;
			double y = e.getY() - p.y;
			rec.getProperties().put("length", x);
			rec.getProperties().put("width", y);
			rec.setColor((Object)stroke);
			rec.setFillColor((Object)fill);
			rec.draw(canvas);
			de.addShape(rec);
			txt1.setVisible(false);
			txt2.setVisible(false);
			txt3.setVisible(false);
			lbl1.setVisible(false);
			lbl2.setVisible(false);
			lbl3.setVisible(false);
			d.setVisible(false);
		});
//		rec.setProperties();
		
//		gc.setFill(Color.RED);
//		gc.fillRect(50, 50, 150, 150);
		
	}
	
	public void refresh (ActionEvent event) {
		gc.clearRect(0, 0, 870, 590);
		de.refresh(canvas);
	}
	
	public void remove (ActionEvent event) {
		if (current != null ) {
			de.removeShape(current);
			current = null;
		}
		gc.clearRect(0, 0, 870, 590);
		de.refresh(canvas);
	}
	
	public void edit (ActionEvent event) {
//		if (current != null ) {
//			de.removeShape(current);
//			current = null;
//		}
		String s = find(current);
		if (s.equals("line")) {
			txt1.setVisible(true);
			Point center = (Point) current.getPosition();
			txt1.setText(center.getX()+","
					+center.getY());
			txt2.setVisible(true);
			lbl1.setVisible(true);
			lbl2.setVisible(true);
			lbl2.setText("P2");
			txt2.setText(current.getProperties().get("x2").toString()+","
			+current.getProperties().get("y2").toString());
			d.setVisible(true);
			
			
			d.setOnAction(e -> {
				boolean t1 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}[,]{1}[0-9]{1,}[.]?[0-9]{0,}", txt1.getText());
				boolean t2 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}[,]{1}[0-9]{1,}[.]?[0-9]{0,}", txt2.getText());
				if (t1 && t2) {
					Shape l2 = new line();
					Point p = new Point();
					p.x = (int) Double.parseDouble(txt1.getText().split(",")[0]);
					p.y = (int) Double.parseDouble(txt1.getText().split(",")[1]);
					l2.setPosition(p);
					String point2 = txt2.getText();
					String[] s2 = point2.split(",");				
					l2.getProperties().put("x2", Double.parseDouble(s2[0]));
					l2.getProperties().put("y2", Double.parseDouble(s2[1]));
					
					l2.setColor((Object)stroke);
					l2.setFillColor((Object)fill);
					de.updateShape(current, l2);
					gc.clearRect(0, 0, 870, 590);
					de.refresh(canvas);
				}
				txt1.clear();
				txt2.clear();
				txt1.setVisible(false);
				txt2.setVisible(false);
				lbl1.setVisible(false);
				lbl2.setVisible(false);
				d.setVisible(false);
			
			});
		
		
		} else if (s.equals("rectangle")) {
			txt1.setVisible(true);
			Point center = (Point) current.getPosition();
			txt1.setText(center.getX()+","
					+center.getY());
			txt2.setVisible(true);
			txt2.setText(current.getProperties().get("length").toString());
			txt3.setText(current.getProperties().get("width").toString());
			txt3.setVisible(true);
			lbl1.setVisible(true);
			lbl2.setVisible(true);
			lbl3.setVisible(true);
			lbl2.setText("Length");
			lbl3.setText("Width");
			d.setVisible(true);
			
			d.setOnAction(e -> {
				boolean t1 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}[,]{1}[0-9]{1,}[.]?[0-9]{0,}", txt1.getText());
				boolean t2 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}", txt2.getText());
				boolean t3 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}", txt3.getText());
				if (t1 && t2 && t3) {
					Shape rec = new rectangle();
					rec.getProperties().put("length", Double.parseDouble(txt2.getText()));
					rec.getProperties().put("width", Double.parseDouble(txt3.getText()));
					Point p = new Point();
					p.x = (int) Double.parseDouble(txt1.getText().split(",")[0]);
					p.y = (int) Double.parseDouble(txt1.getText().split(",")[1]);
					rec.setPosition(p);
					rec.setColor((Object)stroke);
					rec.setFillColor((Object)fill);
					de.updateShape(current, rec);
					gc.clearRect(0, 0, 870, 590);
					de.refresh(canvas);
				}
				txt1.clear();
				txt2.clear();
				txt3.clear();
				txt1.setVisible(false);
				txt2.setVisible(false);
				txt3.setVisible(false);
				lbl1.setVisible(false);
				lbl2.setVisible(false);
				lbl3.setVisible(false);
				d.setVisible(false);
				
			});
		} else if (s.equals("square")) {
			txt1.setVisible(true);
			Point center = (Point) current.getPosition();
			txt1.setText(center.getX()+","
					+center.getY());
			txt2.setVisible(true);
			txt2.setText(current.getProperties().get("length").toString());
			lbl1.setVisible(true);
			lbl2.setVisible(true);
			lbl2.setText("Length");
			d.setVisible(true);
			
			d.setOnAction(e -> {
				boolean t1 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}[,]{1}[0-9]{1,}[.]?[0-9]{0,}", txt1.getText());
				boolean t2 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}", txt2.getText());
				if (t1 && t2) {
					Shape sq = new square();
					sq.getProperties().put("length", Double.parseDouble(txt2.getText()));
					Point p = new Point();
					p.x = (int) Double.parseDouble(txt1.getText().split(",")[0]);
					p.y = (int) Double.parseDouble(txt1.getText().split(",")[1]);
					sq.setPosition(p);
					sq.setColor((Object)stroke);
					sq.setFillColor((Object)fill);
					de.updateShape(current, sq);
					gc.clearRect(0, 0, 870, 590);
					de.refresh(canvas);
					
				}
				txt1.clear();
				txt2.clear();
				txt1.setVisible(false);
				txt2.setVisible(false);
				lbl1.setVisible(false);
				lbl2.setVisible(false);
				d.setVisible(false);
			});
		}else if (s.equals("circle")) {
			txt1.setVisible(true);
			Point center = (Point) current.getPosition();
			txt1.setText(center.getX()+","
					+center.getY());
			txt2.setVisible(true);
			txt2.setText(current.getProperties().get("radius").toString());
			lbl1.setVisible(true);
			lbl2.setVisible(true);
			lbl2.setText("Radius");
			d.setVisible(true);
			
			d.setOnAction(e -> {
				boolean t1 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}[,]{1}[0-9]{1,}[.]?[0-9]{0,}", txt1.getText());
				boolean t2 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}", txt2.getText());
				if (t1 && t2) {
					Shape cr = new circle();
					cr.getProperties().put("radius", Double.parseDouble(txt2.getText()));
					Point p = new Point();
					p.x = (int) Double.parseDouble(txt1.getText().split(",")[0]);
					p.y = (int) Double.parseDouble(txt1.getText().split(",")[1]);
					cr.setPosition(p);
					cr.setColor((Object)stroke);
					cr.setFillColor((Object)fill);
					de.updateShape(current, cr);
					gc.clearRect(0, 0, 870, 590);
					de.refresh(canvas);
				}
				txt1.clear();
				txt2.clear();
				txt1.setVisible(false);
				txt2.setVisible(false);
				lbl1.setVisible(false);
				lbl2.setVisible(false);
				d.setVisible(false);
				de.refresh(canvas);
			});
		} else if (s.equals("ellipse")) {
			txt1.setVisible(true);
			Point center = (Point) current.getPosition();
			txt1.setText(center.getX()+","
					+center.getY());
			txt2.setVisible(true);
			txt2.setText(current.getProperties().get("radius").toString());
			txt3.setVisible(true);
			txt3.setText(current.getProperties().get("b").toString());
			lbl1.setVisible(true);
			lbl2.setVisible(true);
			lbl3.setVisible(true);
			lbl2.setText("a");
			lbl3.setText("b");
			d.setVisible(true);
			
			d.setOnAction(e -> {
				boolean t1 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}[,]{1}[0-9]{1,}[.]?[0-9]{0,}", txt1.getText());
				boolean t2 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}", txt2.getText());
				boolean t3 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}", txt3.getText());
				if (t1 && t2 && t3) {
					Shape el = new ellipse();
					el.getProperties().put("radius", Double.parseDouble(txt2.getText()));
					el.getProperties().put("b", Double.parseDouble(txt3.getText()));
					Point p = new Point();
					p.x = (int) Double.parseDouble(txt1.getText().split(",")[0]);
					p.y = (int) Double.parseDouble(txt1.getText().split(",")[1]);
					el.setPosition(p);
					el.setColor((Object)stroke);
					el.setFillColor((Object)fill);
					de.updateShape(current, el);
					gc.clearRect(0, 0, 870, 590);
					de.refresh(canvas);
				}
				txt1.clear();
				txt2.clear();
				txt3.clear();
				txt1.setVisible(false);
				txt2.setVisible(false);
				txt3.setVisible(false);
				lbl1.setVisible(false);
				lbl2.setVisible(false);
				lbl3.setVisible(false);
				d.setVisible(false);
				
			});
		} else if (s.equals("triangle")) {
			txt1.setVisible(true);
			Point center = (Point) current.getPosition();
			txt1.setText(center.getX()+","
					+center.getY());
			txt2.setVisible(true);
			lbl1.setVisible(true);
			lbl2.setVisible(true);
			lbl2.setText("P2");
			txt2.setText(current.getProperties().get("x2").toString()+","
			+current.getProperties().get("y2").toString());
			txt3.setVisible(true);
			lbl3.setVisible(true);
			lbl3.setText("P3");
			txt3.setText(current.getProperties().get("x3").toString()+","
			+current.getProperties().get("y3").toString());
			d.setVisible(true);
			
			d.setOnAction(e -> {
				boolean t1 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}[,]{1}[0-9]{1,}[.]?[0-9]{0,}", txt1.getText());
				boolean t2 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}[,]{1}[0-9]{1,}[.]?[0-9]{0,}", txt2.getText());
				boolean t3 = Pattern.matches("[0-9]{1,}[.]?[0-9]{0,}[,]{1}[0-9]{1,}[.]?[0-9]{0,}", txt3.getText());
				if (t1 && t2 && t3) {
					Shape tri = new triangle();
					Point p = new Point();
					p.x = (int) Double.parseDouble(txt1.getText().split(",")[0]);
					p.y = (int) Double.parseDouble(txt1.getText().split(",")[1]);
					tri.setPosition(p);
					String[] s2 = txt2.getText().split(",");
					tri.getProperties().put("x2", Double.parseDouble(s2[0]));
					tri.getProperties().put("y2", Double.parseDouble(s2[1]));
					String[] s3 = txt3.getText().split(",");
					tri.getProperties().put("x3", Double.parseDouble(s3[0]));
					tri.getProperties().put("y3", Double.parseDouble(s3[1]));
					tri.setColor((Object)stroke);
					tri.setFillColor((Object)fill);
					de.updateShape(current, tri);
					gc.clearRect(0, 0, 870, 590);
					de.refresh(canvas);
				}
				txt1.clear();
				txt2.clear();
				txt3.clear();
				i = 0;
				txt1.setVisible(false);
				txt2.setVisible(false);
				txt3.setVisible(false);
				lbl1.setVisible(false);
				lbl2.setVisible(false);
				lbl3.setVisible(false);
				d.setVisible(false);
				
			});
		}
	}
	
//	public void empty (ActionEvent event) {
//		gc.clearRect(0, 0, 870, 590);
//	}
	
	public void undo (ActionEvent event) {
		de.undo();
		if (undos < 20)
			undos++;
		lbl4.setText(Integer.toString(undos));
		gc.clearRect(0, 0, 870, 590);
		de.refresh(canvas);
	}
	
	public void redo (ActionEvent event) {
		de.redo();
		if (undos > 0)
			undos--;
		lbl4.setText(Integer.toString(undos));
		gc.clearRect(0, 0, 870, 590);
		de.refresh(canvas);
	}
	
//	public void shapeSelected (MouseEvent e) {
//		  current = (Shape) e.getSource();
////		  Shape[] shapes = de.getShapes();
//	}
	

	public void select (ActionEvent event) {
		boolean d = true;
		Shape[] sh = de.getShapes();
		List<Shape> shap = Arrays.asList(sh);
		if (d) {
			canPane.setOnMouseClicked(e -> {
				int px =  (int) e.getX();
				int py = (int) e.getY();
				
				for (Shape i : shap) {
					Point p2 = (Point) i.getPosition();
					String s = find(i);
					int xMin, yMin;
					Double yMax;
					Double xMax;
					xMin = p2.x;
					yMin = p2.y;
					if (s.equals("line")) {
						xMax = i.getProperties().get("x2");
						yMax = i.getProperties().get("y2");
						
						double dxl = xMax - xMin;
						double dyl = yMax - yMin;
						double sl = dyl / dxl;
						double dxp = xMax - p2.x;
						double dyp = yMax - p2.y;
						double sp = dyp / dxp;
						if (sl == sp) {
							current = i;
						}				
					} else if (s.equals("circle")) {
						double dis = Math.sqrt((px - p2.x)^2+(py - p2.y)^2);
						if (dis <= i.getProperties().get("radius")) {
							current = i;
						}	
					} else if (s.equals("square")) {
						if (px <= p2.x + i.getProperties().get("length") &&  px >= p2.x && py >= p2.y
								&& py <= p2.y + i.getProperties().get("length")) {
							current = i;
						}
					}  else if (s.equals("rectangle")) {
						if (px <= p2.x + i.getProperties().get("length") && px >= p2.x && py >= p2.y
								&& py <= p2.y + i.getProperties().get("width")) {
							current = i;
						}
					} else if (s.equals("ellipse")) {
						double h = p2.x;
						double k = p2.y;
						double aS = (i.getProperties().get("radius")) * (i.getProperties().get("radius"));
						double bS = (i.getProperties().get("b")) * (i.getProperties().get("b"));
						double first = (px - h) * (px - h) / aS;
						double second = (py - k) * (py - k) / bS;
						if ((first + second) <= 1) {
							current = i;
						}
					} else if (s.equals("triangle")) {
						double p3x;
						p3x = i.getProperties().get("x2");
						double p3y;
						p3y = i.getProperties().get("y2");
						double p4x;
						p4x = i.getProperties().get("x3");
						double p4y;
						p4y = i.getProperties().get("y3");
						
						/* Calculate area of triangle ABC */
				        double A = area (p2.x, p2.y, p3x, p3y, p4x, p4y); 
				       
				       /* Calculate area of triangle PBC */  
				        double A1 = area (px, py, p3x, p3y, p4x, p4y); 
				       
				       /* Calculate area of triangle PAC */  
				        double A2 = area (px, py, p2.x, p2.y, p4x, p4y); 
				       
				       /* Calculate area of triangle PAB */   
				        double A3 = area (px, py, p2.x, p2.y, p3x, p3y);
				        
				        if (A == A1 + A2 + A3) {
				        	current = i;
				        }
					}
				}
			});
			
		}
		d = false;
		
	}
	
	static double area(int x1, int y1, double p3x, double p3y, double p3x2, double p3y2) { 
		return Math.abs((x1*(p3y-p3y2) + p3x*(p3y2-y1)+ p3x2*(y1-p3y))/2.0); 
	}
	

	public String find (Shape s) {
		Map<String,Double> m = s.getProperties();
		if (m.containsKey("x3")) {
			return "triangle";
		} else if (m.containsKey("x2")) {
			return "line";
		} else if (m.containsKey("b")) {
			return "ellipse";
		} else if (m.containsKey("radius")) {
			return "circle";
		} else if (m.containsKey("width")) {
			return "rectangle";
		} else if (m.containsKey("length")) {
			return "square";
		}
		return null;
	}
	
	public void release (ActionEvent event) {
		check = false;
		current = null;
		txt1.setVisible(false);
		txt2.setVisible(false);
		txt3.setVisible(false);
		lbl1.setVisible(false);
		lbl2.setVisible(false);
		lbl3.setVisible(false);
		d.setVisible(false);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void child() {
		this.canvas = new Canvas(870,560);
		this.gc = canvas.getGraphicsContext2D();
		this.canPane.getChildren().add(canvas);
	}
}
