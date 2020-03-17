package boundary;

import javax.swing.*;

import control.TransactionList;
import control.UserList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** 
* A class representing GUI to send email to users . 
* 
* <p> The class creates GUI object to display user's all transactions,
* and make a file if transactions when sending email.
* 
* @author Zou Shiwen 
* @version 1.0 
* @since 27th April, 2019
* @see javax.swing
*/ 
public class SendEmailGUI {

	JFrame frame;
	JPanel panel1, panel2, panel3, panel4, panel5;
	JLabel label;
	JButton sbutton, ebutton, rbutton;
	JTextField textfield;
	JTextArea textarea;
	UserList userList;
	TransactionList transactionList;
	int id;

	/** 
     * This method is the constructor used for
     * initiate SendEmailGUI objects.
     */
	public SendEmailGUI(UserList userlist, TransactionList transactionlist) {
		userList = userlist;
		transactionList = transactionlist;

		frame = new JFrame();
		Font Font1 = new Font("arial", Font.PLAIN, 20);
		label = new JLabel("Please Enter the QM Number:");
		label.setFont(Font1);
		textfield = new JTextField(20);
		textfield.setFont(Font1);
		sbutton = new JButton("Search");
		sbutton.setFont(Font1);
		sbutton.addActionListener(new SearchButtonListener());
		textarea = new JTextArea(40, 45);
		textarea.setFont(Font1);
		textarea.setText(
				"    Pick or Return     |       Time       |       Dock      |      Slot      |      Is Over Time\n");
		ebutton = new JButton("Send An Email");
		ebutton.setFont(Font1);
		ebutton.addActionListener(new EmailButtonListener());
		rbutton = new JButton("Return");
		rbutton.setFont(Font1);
		rbutton.addActionListener(new RetButtonListener());
		panel1 = new JPanel();
		panel1.add(label);
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 60, 10));
		panel2.add(textfield);
		panel2.add(sbutton);
		panel3 = new JPanel();
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
		panel3.add(panel1);
		panel3.add(panel2);
		panel4 = new JPanel();
		panel4.add(textarea);
		panel5 = new JPanel();
		panel5.setLayout(new FlowLayout(FlowLayout.LEFT, 100, 10));
		panel5.add(ebutton);
		panel5.add(rbutton);
		frame.getContentPane().add(BorderLayout.NORTH, panel3);
		frame.getContentPane().add(BorderLayout.CENTER, panel4);
		frame.getContentPane().add(BorderLayout.SOUTH, panel5);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setVisible(true);
	}

	/** 
	* A class is the inner class of SendEmailGUI. 
	* 
	* <p> The class implements the interface of ActionListener
	* and perform accordingly action.
	* 
	* @author Zou Shiwen 
	* @version 1.0 
	* @since 27th April, 2019
	*/ 
	class SearchButtonListener implements ActionListener {
		/**
		 * This method is perform action when clicking search button.
	     * @param event The click action on button.
	     * @version 1.0
	     */
		public void actionPerformed(ActionEvent event) {
			try {
				textarea.setText("");
				String input = textfield.getText();
				id = Integer.parseInt(input);
				if (userList.checkIDFormat(id) == true) {
					if (userList.checkIDExist(id) == true) {
						String[] line = transactionList.allUserTrans(id);
						if (line != null) {
							label.setText("Valid input id.");
							for (String cell : line) {
								textarea.append(cell + "\n");
							}
						} else {
							label.setText("Registered user has no transaction.");
						}

					} else {
						label.setText("Not registered input id. Write again.");
					}
				} else {
					label.setText("Invalid Id format. Write again.");
				}
			} catch (NumberFormatException n) {
				label.setText("Invalid integer format. Write again.");
			} catch (NullPointerException e) {
				label.setText("Please input id.");
			}

		}

	}

	/** 
	* A class is the inner class of SendEmailGUI,
	* it perform sending email to users by making file.
	* 
	* <p> The class implements the interface of ActionListener
	* and perform accordingly action.
	* 
	* @author Zou Shiwen 
	* @version 1.0 
	* @since 27th April, 2019
	*/ 
	class EmailButtonListener implements ActionListener {
		/**
		 * This method is perform action when clicking send email button.
	     * @param event The click action on button.
	     * @version 1.0
	     */
		public void actionPerformed(ActionEvent event) {
			transactionList.sendEmail(id);
		}
	}

	/** 
	* A class is the inner class of SendEmailGUI,
	* it perform closing the frame.
	* 
	* <p> The class implements the interface of ActionListener
	* and perform accordingly action.
	* 
	* @author Zou Shiwen 
	* @version 1.0 
	* @since 27th April, 2019
	*/ 
	class RetButtonListener implements ActionListener {
		/**
		 * This method is perform action when clicking return button.
	     * @param event The click action on button.
	     * @version 1.0
	     */
		public void actionPerformed(ActionEvent event) {
			frame.dispose();
		}
	}

}
