package simpleCalculator;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calc implements ActionListener {
	JTextField txt;
	JFrame frame;
	JTextField textfield;
	JButton[] numberButtons = new JButton[10];
	JButton[] functionButtons = new JButton[10];
	JButton addButton, subButton, mulButton, divButton;
	JButton decButton, equButton, delButton, clrButton, negButton;
	JPanel panel;
	Font myFont = new Font("Consolas", Font.BOLD, 30);
	double num1 = 0;
	double num2 = 0;
	char operator;

	Calc() {
		frame = new JFrame("Calculator");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(333, 475);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		textfield = new JTextField();
		textfield.setBounds(10, 10, 298, 50);
		textfield.setFont(myFont);
		textfield.setEditable(false);

		addButton = new JButton("+");
		subButton = new JButton("-");
		mulButton = new JButton("*");
		divButton = new JButton("/");
		decButton = new JButton(".");
		equButton = new JButton("=");
		negButton = new JButton("(-)");
		delButton = new JButton("DEL");
		clrButton = new JButton("CLR");

		functionButtons[0] = addButton;
		functionButtons[1] = subButton;
		functionButtons[2] = mulButton;
		functionButtons[3] = divButton;
		functionButtons[4] = decButton;
		functionButtons[5] = equButton;
		functionButtons[6] = negButton;
		functionButtons[7] = delButton;
		functionButtons[8] = clrButton;

		for (int i = 0; i <= 8; i++) {
			functionButtons[i].addActionListener(this);
			functionButtons[i].setFont(myFont);
			functionButtons[i].setFocusable(false);
		}
		for (int i = 0; i <= 9; i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFont(myFont);
			numberButtons[i].setFocusable(false);
		}

		negButton.setBounds(11, 380, 95, 50);
		delButton.setBounds(112, 380, 95, 50);
		clrButton.setBounds(213, 380, 95, 50);

		panel = new JPanel();
		panel.setBounds(10, 70, 300, 300);
		panel.setLayout(new GridLayout(4, 4, 3, 3));
		// panel.setBackground(Color.WHITE);

		panel.add(numberButtons[1]);
		panel.add(numberButtons[2]);
		panel.add(numberButtons[3]);
		panel.add(addButton);
		panel.add(numberButtons[4]);
		panel.add(numberButtons[5]);
		panel.add(numberButtons[6]);
		panel.add(subButton);
		panel.add(numberButtons[7]);
		panel.add(numberButtons[8]);
		panel.add(numberButtons[9]);
		panel.add(mulButton);
		panel.add(decButton);
		panel.add(numberButtons[0]);
		panel.add(equButton);
		panel.add(divButton);

		frame.add(panel);
		frame.add(negButton);
		frame.add(delButton);
		frame.add(clrButton);
		frame.add(textfield);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i <= 9; i++) {
			if (e.getSource() == numberButtons[i]) {
				textfield.setText(textfield.getText().concat(String.valueOf(i)));
			}
		}
		if (textfield.getText().length() > 0) {
			if (e.getSource() == decButton) {
				textfield.setText(textfield.getText().concat(String.valueOf(".")));
			}
			if (e.getSource() == addButton) {
				num1 = Double.parseDouble(textfield.getText());
				operator = '+';
				textfield.setText("");
			}
			if (e.getSource() == subButton) {
				num1 = Double.parseDouble(textfield.getText());
				operator = '-';
				textfield.setText("");
			}
			if (e.getSource() == mulButton) {
				num1 = Double.parseDouble(textfield.getText());
				operator = '*';
				textfield.setText("");
			}
			if (e.getSource() == divButton) {
				num1 = Double.parseDouble(textfield.getText());
				operator = '/';
				textfield.setText("");
			}
			if (e.getSource() == negButton) {
				if (textfield.getText().length() > 0) {
					double temp = Double.parseDouble(textfield.getText());
					temp *= -1;
					textfield.setText(String.valueOf(temp));
				}

			}
		}

		if (e.getSource() == clrButton) {
			textfield.setText("");
		}
		if (e.getSource() == delButton) {
			if (textfield.getText().length() > 0) {
				String string = textfield.getText();
				textfield.setText("");
				for (int i = 0; i < string.length() - 1; i++) {
					textfield.setText(textfield.getText() + string.charAt(i));
				}

				if (textfield.getText().length() == 1 && textfield.getText().charAt(0) == '-') {
					textfield.setText("");
				}
			}

		}

		if (e.getSource() == equButton) {

			if (textfield.getText().length() == 0) {
				num2 = 0;
			} else {
				num2 = Double.parseDouble(textfield.getText());
			}

			switch (operator) {
			case '+' -> {
				num1 = num1 + num2;
				textfield.setText(String.valueOf(num1));
			}
			case '-' -> {
				num1 = num1 - num2;
				textfield.setText(String.valueOf(num1));
			}
			case '*' -> {
				num1 = num1 * num2;
				textfield.setText(String.valueOf(num1));
			}
			case '/' -> {
				num1 = num1 / num2;
				textfield.setText(String.valueOf(num1));
			}
			}

		}

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Calc();
			}

		});

	}
}
