import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class TicTacToe implements ActionListener {
	JFrame frame = new JFrame("Tic tac toe");
	JPanel title_panel = new JPanel();
	JLabel winnerLabel = new JLabel("Welcome to Tic-Tac-Toe");
	JPanel button_panel = new JPanel();
	JButton[] buttons = new JButton[9];
	JButton newGameBtn = new JButton("New Game");
	String currentPlayer = "O";
	Random rand = new Random();
	int randomInt;

	void randomStartPlayer() {
		randomInt = rand.nextInt(2);
		if (randomInt == 1) {
			currentPlayer = "O";
		} else {
			currentPlayer = "X";
		}
	}

	public TicTacToe() {

		frame.setSize(600, 600);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		//title_panel.setLayout(new BoxLayout(title_panel, BoxLayout.Y_AXIS));
		title_panel.setLayout(new BorderLayout());
		newGameBtn.addActionListener(this);
		title_panel.add(winnerLabel, BorderLayout.NORTH);
		winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		winnerLabel.setFont(new Font("Arial", Font.BOLD, 18));
		title_panel.add(newGameBtn, BorderLayout.SOUTH);

		button_panel.setLayout(new GridLayout(3, 3));

		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton("");
			button_panel.add(buttons[i]);
			buttons[i].setEnabled(false);
			buttons[i].addActionListener(this);
			buttons[i].setFont(new Font("Arial", Font.BOLD, 100));
		}

		frame.add(title_panel, BorderLayout.NORTH);
		frame.add(button_panel);
		frame.setVisible(true);

	}

	void resetBoard() {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setEnabled(true);
			buttons[i].setText("");
			randomStartPlayer();
			buttons[i].setBackground(null);
		}
		winnerLabel.setText("Welcome to Tic-Tac-Toe");
		;
	}

	void makeMove(int btn) {
		buttons[btn].setText(currentPlayer);
		if (currentPlayer == "O") {
			currentPlayer = "X";
		} else {
			currentPlayer = "O";
		}
	}

	void winCheck() {
		if ((buttons[0].getText() == "X") && (buttons[1].getText() == "X") && (buttons[2].getText() == "X")) {
			xWins(0, 1, 2);
		}
		if ((buttons[3].getText() == "X") && (buttons[4].getText() == "X") && (buttons[5].getText() == "X")) {
			xWins(3, 4, 5);
		}
		if ((buttons[6].getText() == "X") && (buttons[7].getText() == "X") && (buttons[8].getText() == "X")) {
			xWins(6, 7, 8);
		}
		if ((buttons[0].getText() == "X") && (buttons[3].getText() == "X") && (buttons[6].getText() == "X")) {
			xWins(0, 3, 6);
		}
		if ((buttons[1].getText() == "X") && (buttons[4].getText() == "X") && (buttons[7].getText() == "X")) {
			xWins(1, 4, 7);
		}
		if ((buttons[2].getText() == "X") && (buttons[5].getText() == "X") && (buttons[8].getText() == "X")) {
			xWins(2, 5, 8);
		}
		if ((buttons[0].getText() == "X") && (buttons[4].getText() == "X") && (buttons[8].getText() == "X")) {
			xWins(0, 4, 8);
		}
		if ((buttons[2].getText() == "X") && (buttons[4].getText() == "X") && (buttons[6].getText() == "X")) {
			xWins(2, 4, 6);
		}
		// check O win conditions
		if ((buttons[0].getText() == "O") && (buttons[1].getText() == "O") && (buttons[2].getText() == "O")) {
			oWins(0, 1, 2);
		}
		if ((buttons[3].getText() == "O") && (buttons[4].getText() == "O") && (buttons[5].getText() == "O")) {
			oWins(3, 4, 5);
		}
		if ((buttons[6].getText() == "O") && (buttons[7].getText() == "O") && (buttons[8].getText() == "O")) {
			oWins(6, 7, 8);
		}
		if ((buttons[0].getText() == "O") && (buttons[3].getText() == "O") && (buttons[6].getText() == "O")) {
			oWins(0, 3, 6);
		}
		if ((buttons[1].getText() == "O") && (buttons[4].getText() == "O") && (buttons[7].getText() == "O")) {
			oWins(1, 4, 7);
		}
		if ((buttons[2].getText() == "O") && (buttons[5].getText() == "O") && (buttons[8].getText() == "O")) {
			oWins(2, 5, 8);
		}
		if ((buttons[0].getText() == "O") && (buttons[4].getText() == "O") && (buttons[8].getText() == "O")) {
			oWins(0, 4, 8);
		}
		if ((buttons[2].getText() == "O") && (buttons[4].getText() == "O") && (buttons[6].getText() == "O")) {
			oWins(2, 4, 6);
		}
	}

	private void oWins(int i, int j, int k) {
		buttons[i].setBackground(Color.GREEN);
		buttons[j].setBackground(Color.GREEN);
		buttons[k].setBackground(Color.GREEN);
		freezeBoard();
		winnerLabel.setText("Player O has won!!");
	}

	private void xWins(int i, int j, int k) {
		buttons[i].setBackground(Color.GREEN);
		buttons[j].setBackground(Color.GREEN);
		buttons[k].setBackground(Color.GREEN);
		freezeBoard();
		winnerLabel.setText("Player X has won!!");
	}

	void freezeBoard() {

		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setEnabled(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == newGameBtn) {
			resetBoard();
			JOptionPane.showMessageDialog(frame, "Starting a new game!\nIt is " + currentPlayer + "'s first turn!");
		}

		for (int i = 0; i < buttons.length; i++) {
			if (e.getSource() == buttons[i]) {
				if (buttons[i].getText() == "") {
					makeMove(i);
					winCheck();
				}

			}
		}

	}

}
