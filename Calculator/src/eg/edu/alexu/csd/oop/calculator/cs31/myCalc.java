package eg.edu.alexu.csd.oop.calculator.cs31;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.calculator.Calculator;

public class myCalc implements Calculator {
	
	private ArrayList<String> list = new ArrayList<String>(5);
	private int current = -1;
	private int opIndex = 1;
	private File file = new File("history.txt");
	
	@Override
	public void input(String s) {
		// TODO Auto-generated method stub
		boolean valid = Pattern.matches
				("[-]?[0-9]{1,}[.]?[0-9]{0,}[/*+-]{1}[-]?[0-9]{1,}[.]?[0-9]{0,}", s);
			
		if (valid) {
			list.add(s);
			if(current == 4) {
				list.remove(0);
				current--;
			}
			current = list.size()-1;
		}
	}

	@Override
	public String getResult() {
		// TODO Auto-generated method stub
		if (current == -1)
			throw new RuntimeException();
		String s = list.get(current);
		
		if (s.contains("+")) {
			opIndex = s.indexOf("+");
		} else if (s.contains("*")) {
			opIndex = s.indexOf("*");
		} else if (s.contains("/")) {
			opIndex = s.indexOf("/");
		} else {
			opIndex = s.indexOf("-");
			if (opIndex == 0) {
				opIndex = s.substring(1).indexOf("-")+1;
			}
		}
		double num1 = Double.parseDouble(s.substring(0, opIndex));
		double num2 = Double.parseDouble(s.substring(opIndex+1));
		char c = s.charAt(opIndex);
		double result = 0.0;
		switch (c) {
		case '+' : result = num1 + num2;break;
		case '-' : result = num1 - num2;break;
		case '*' : result = num1 * num2;break;
		case '/' : result = num1 / num2;break;
		}
		String rslt = Double.toString(result);
		
		return rslt;
	}

	@Override
	public String current() {
		// TODO Auto-generated method stub
		if (list.isEmpty())
			return null;
		return list.get(current);
	}

	@Override
	public String prev() {
		// TODO Auto-generated method stub
		if (current <= 0)
			return null;
		current--;
		return list.get(current);
	}

	@Override
	public String next() {
		// TODO Auto-generated method stub
		if (current == 4 || current == list.size()-1)
			return null;
		current++;
		return list.get(current);
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		int i = 0;
		try {
			PrintWriter pw = new PrintWriter("history.txt");
			pw.println(Integer.toString(current));
			while (i < list.size()) {
				pw.println(list.get(i));
				i++;
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		list.clear();
		BufferedReader br = null;
		try {
			 br = new BufferedReader(new FileReader("history.txt"));
			 String line;
			 line = br.readLine();
			 current = Integer.parseInt(line);
			 while ((line = br.readLine()) != null) {
				 list.add(line);
			 }
			 br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
