import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class MainClass implements ActionListener {
	JFrame frame = new JFrame("Age Calculator");
	JPanel panel;
	JLabel lblDay = new JLabel("Day   : ");
	JLabel lblMonth = new JLabel("Month : ");
	JLabel lblYear = new JLabel("Year  : ");
	JTextArea txt = new JTextArea("");
	JTextField txtDay = new JTextField();
	JTextField txtMonth = new JTextField();
	JTextField txtYear = new JTextField();
	JButton btn = new JButton("Check Age");
	DocumentFilter filter = new NumberOnlyFilter();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
	LocalDate date, now;
	int years, months, days;
	String answer;

	class NumberOnlyFilter extends DocumentFilter {
		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
				throws BadLocationException {
			String newText = fb.getDocument().getText(0, fb.getDocument().getLength()).substring(0, offset) + text
					+ fb.getDocument().getText(0, fb.getDocument().getLength()).substring(offset + length);

			try {
				Integer.parseInt(newText);
				super.replace(fb, offset, length, text, attrs);
			} catch (NumberFormatException e) {
				// ignore
			}
		}
	}

	MainClass() {
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		panel = new JPanel();
		GridLayout gridlayout = new GridLayout(3, 2);
		gridlayout.setVgap(3);
		panel.setLayout(gridlayout);
		lblDay.setFont(new Font("Arial", Font.BOLD, 14));
		lblMonth.setFont(new Font("Arial", Font.BOLD, 14));
		lblYear.setFont(new Font("Arial", Font.BOLD, 14));
		((AbstractDocument) txtDay.getDocument()).setDocumentFilter(filter);
		((AbstractDocument) txtMonth.getDocument()).setDocumentFilter(filter);
		((AbstractDocument) txtYear.getDocument()).setDocumentFilter(filter);
		txtDay.setFont(new Font("Arial", Font.BOLD, 25));
		txtMonth.setFont(new Font("Arial", Font.BOLD, 25));
		txtYear.setFont(new Font("Arial", Font.BOLD, 25));
		panel.add(lblDay);
		panel.add(txtDay);
		panel.add(lblMonth);
		panel.add(txtMonth);
		panel.add(lblYear);
		panel.add(txtYear);
		frame.add(panel, BorderLayout.NORTH);
		frame.add(btn, BorderLayout.CENTER);
		frame.add(txt, BorderLayout.SOUTH);
		lblDay.setHorizontalAlignment(JLabel.CENTER);
		lblMonth.setHorizontalAlignment(JLabel.CENTER);
		lblYear.setHorizontalAlignment(JLabel.CENTER);
		panel.setPreferredSize(new Dimension(0, 150));
		txt.setPreferredSize(new Dimension(20, 150));
		txt.setFont(new Font("Consolas", Font.BOLD, 40));
		txt.setLineWrap(true);
		frame.setResizable(false);

		btn.addActionListener(this);

		frame.setVisible(true);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MainClass();
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		txt.setText(answer);
		if (e.getSource() == btn) {
			String input = String.format("%s/%s/%s", txtDay.getText(), txtMonth.getText(), txtYear.getText());
			try {
				date = LocalDate.parse(input, formatter);
				now = LocalDate.now();
				// JOptionPane.showMessageDialog(frame, String.format("%s is a valid date.",
				// input));

				Period period = Period.between(date, now);
				answer = String.format("Years  : %d\nMonths : %d\nDays   : %d",
						period.getYears(), period.getMonths(), period.getDays());

				txt.setText(answer);

			} catch (DateTimeParseException ex) {
				JOptionPane.showMessageDialog(frame, String.format("%s is not a valid date.", input));
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Unknown Error occured");
			}

		}

	}

}
