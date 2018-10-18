package GUI;

import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.calculator.cs31.myCalc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController {
	
	@FXML
	private Label lbl = new Label("");
	private boolean start = true;
	private myCalc cal = new myCalc();
	
	
	public void input (ActionEvent event) {
		if (start) {
			lbl.setText("");
			start = false;
		}
		String value = ((Button)event.getSource()).getText();
		lbl.setText(lbl.getText() + value);
	}
	
	public void save (ActionEvent event) {
		cal.save();
	}
	
	public void load (ActionEvent event) {
		cal.load();
	}
	
	public void next (ActionEvent event) {
		String s = cal.next();
		if (s == null) {
			lbl.setText("No More Next");
			start = true;
		} else {
			lbl.setText(s);
		}
	}
	
	public void prev (ActionEvent event) {
		String s = cal.prev();
		if (s == null) {
			lbl.setText("No More Prev");
			start = true;
		} else {
			lbl.setText(s);
		}
	}
	
	public void current (ActionEvent event) {
		lbl.setText(cal.current());
	}
	
	public void result (ActionEvent event) {
		String s = lbl.getText();
		boolean valid = Pattern.matches
				("[-]?[0-9]{1,}[.]?[0-9]{0,}[/*+-]{1}[-]?[0-9]{1,}[.]?[0-9]{0,}", s);
		if(!valid) {
			lbl.setText("invalid input!");
		} else {
			cal.input(s);
			lbl.setText(cal.getResult());
		}
		start = true;
	}
	
	public void clear (ActionEvent event) {
		lbl.setText("");
	}
}
