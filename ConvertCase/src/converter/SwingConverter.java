package converter;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

public class SwingConverter implements ActionListener {
	final String lc = "                     lower case                    ";
	final String uc = "                     UPPER CASE                    ";
	final String cc = "                 convertToCamelCase                ";
	final String sc = "                     snake_case                    ";
	final String CC = "                  Capitalized case                 ";
	final String ac = "                  aLtErNaTiNg cAsE                 ";
	final String tc = "                     Title case                    ";
	final String wt = "                  W i d e   T e x t                ";

	final String[] btnNames = {"Clear", "Copy" , "Previous", lc, uc, cc, sc, CC, ac, tc, wt };
	JLabel lbl1;
	JTextArea jtxt1;
	String str, newStr, prevStr;
	Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
	StringSelection ss;

	ArrayList<String> listOfStrings = new ArrayList<>(Arrays.asList("a", "an", "the", "in", "with", "by", "of", "on", "and", "or", "but", "for", "neither",
			"nor", "yet", "so", "although", "because", "if", "since", "unless", "until", "while"));
	
	SwingConverter() {
		JFrame frame = new JFrame("Case Converter");
		Font fnt = new Font("Consolas", Font.BOLD, 30);
		Font fntSmall = new Font("Consolas", Font.BOLD, 18);
		frame.setLayout(new FlowLayout());
		frame.setSize(970, 700);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		lbl1 = new JLabel(" Write Here : ");
		jtxt1 = new JTextArea(5, 35);
		jtxt1.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(jtxt1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		frame.add(lbl1);
		frame.add(scroll);
		jtxt1.setFont(new Font("Arial", Font.PLAIN, 30));
		
		/////////////////////////////
		ArrayList<JButton> btns = new ArrayList<JButton>();
		JButton btn;
		
		for( int i = 0 ; i < btnNames.length; i++) {
			btns.add(new JButton(btnNames[i]));
			btn = btns.get(i);
			if(i<3)btn.setFont(fntSmall);
			else btn.setFont(fnt);
			btn.addActionListener(this);
			frame.add(btn);
		}
		/////////////////////////////

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// lbl1.setText(e.getActionCommand());
		str = jtxt1.getText();
		// prevStr = jtxt1.getText();
		newStr = "";
		switch (e.getActionCommand()) {
		case lc -> {
			newStr = str.toLowerCase();
			jtxt1.setText(newStr);
		}
		case uc -> {
			newStr = str.toUpperCase();
			jtxt1.setText(newStr);
		}
		case cc -> {
			for (int i = 0; i < str.length(); i++) {
				if (i == 0) {
					newStr += Character.toLowerCase(str.charAt(i));
				} else {
					if (!(str.charAt(i) == ' ' || str.charAt(i) == '_')) {
						if (str.charAt(i - 1) == ' ' || str.charAt(i - 1) == '_') {
							newStr += Character.toUpperCase(str.charAt(i));
						} else {
							newStr += Character.toLowerCase(str.charAt(i));
						}
					}
				}
			}
		}

		case sc -> {
			for (int i = 0; i < str.length(); i++) {
				newStr = str.replace(' ', '_');
			}
		}

		case CC -> {
			for (int i = 0; i < str.length(); i++) {
				if (i == 0) {
					newStr += Character.toUpperCase(str.charAt(i));
				} else if (i > 1 && (str.charAt(i - 2) == '.' || str.charAt(i - 2) == '!' || str.charAt(i - 2) == '?')
						&& str.charAt(i - 1) == ' ') {
					newStr += Character.toUpperCase(str.charAt(i));
				} else {
					newStr += Character.toLowerCase(str.charAt(i));
				}

			}
		}

		case ac -> {
			int alt = 0;
			for (int i = 0; i < str.length(); i++) {
				if (alt == 0) {
					newStr += Character.toLowerCase(str.charAt(i));
					alt = 1;
				} else {
					newStr += Character.toUpperCase(str.charAt(i));
					alt = 0;
				}
			}
		}

		case wt -> {
			for (int i = 0; i < str.length(); i++) {
				newStr += str.charAt(i);
				newStr += ' ';
			}
		}
		case tc -> {
				String[] arrayOfStr =  str.split(" ");
				for (int x = 0; x < arrayOfStr.length; x++) {
					if(!listOfStrings.contains(arrayOfStr[x])|| x == arrayOfStr.length-1) {
						for (int i = 0; i < arrayOfStr[x].length(); i++) {
							if (i == 0) newStr += Character.toUpperCase(arrayOfStr[x].charAt(i));
							else newStr += Character.toLowerCase(arrayOfStr[x].charAt(i));
						}
					} else {
						newStr += arrayOfStr[x].toLowerCase();
					}
					if (x!= arrayOfStr.length-1) {
						newStr += " ";
					}
					
					
				}
		}

		case "Copy" -> {
			ss = new StringSelection(str);
			clpbrd.setContents(ss, null);
			newStr = str;
		}
		case "Previous" -> {
			newStr = prevStr;
		}

		}

		jtxt1.setText(newStr);
		prevStr = str;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new SwingConverter();
			}
		});
	}

}
