package boundary;

import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;

import control.UserList;

import java.awt.event.ActionListener;

/**
 * 
 * @author liyin
 *
 */
public class EnrollmentGUI implements ActionListener {

	private int ID;
	private String name;
	private String email;
	private JPanel cPanel;
	private JPanel sPanel;
	private UserList userList;
	private JTextField nameField;
	private JTextField IDField;
	private JTextField emailField;
	private JLabel mainHint;
	private JLabel nameLabel;
	private JLabel IDLabel;
	private JLabel emailLabel;
	private JButton enroll;
	private JButton returnButton;
	private JFrame frame;

	public EnrollmentGUI(UserList userList) {
		this.userList = userList;
		startFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		{

			if (e.getSource() == enroll) {
				try {
					ID = Integer.parseInt(IDField.getText());// try to translate input to number
					if (!userList.checkIDFormat(ID)) {
						mainHint.setText("Invalid ID number");
						IDField.setText("");
						return;
					}
					if (userList.checkIDExist(ID)) {
						mainHint.setText("This ID has been enrolled");
						IDField.setText("");
						return;
					}
				} catch (Exception e1) {// generate a input error hint and clean the text field
					mainHint.setText("Wrong input! Again!");
					IDField.setText("");
					System.err.println("Wrong input!!! " + e1);
					return;
				}

				email = emailField.getText();
				if (!userList.checkEmailFormat(email)) {
					mainHint.setText("Invalid email address");
					emailField.setText("");
					return;
				}
				name = nameField.getText();
				userList.enroll(ID, name, email);
				userList.save();
				mainHint.setText("This account is enrolled!");
				IDField.setText("");
				emailField.setText("");
				nameField.setText("");
			} else if (e.getSource() == returnButton) {
				userList.save();
			}
		}
	}

	public void startFrame() {

		frame = new JFrame();
		frame.setLayout(new BorderLayout());

		cPanel = new JPanel();
		cPanel.setLayout(new GridLayout(0, 2));
		sPanel = new JPanel();
		sPanel.setLayout(new GridLayout(0, 2));

		mainHint = new JLabel("");

		nameField = new JTextField(10);
		nameLabel = new JLabel("name	:");
		IDField = new JTextField(10);
		IDLabel = new JLabel("ID	:");
		emailField = new JTextField(10);
		emailLabel = new JLabel("email	:");

		enroll = new JButton("Enroll");
		enroll.addActionListener(this);
		returnButton = new JButton("Save");
		returnButton.addActionListener(this);

		cPanel.add(IDLabel);
		cPanel.add(IDField);
		cPanel.add(nameLabel);
		cPanel.add(nameField);
		cPanel.add(emailLabel);
		cPanel.add(emailField);

		sPanel.add(enroll);
		sPanel.add(returnButton);

		frame.add(BorderLayout.NORTH, mainHint);
		frame.add(BorderLayout.CENTER, cPanel);
		frame.add(BorderLayout.SOUTH, sPanel);
		frame.setVisible(true);
		frame.setSize(800, 400);
	}
}
