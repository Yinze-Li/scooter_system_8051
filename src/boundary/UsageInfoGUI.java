package boundary;
import javax.swing.*;

import control.TransactionList;
import control.UserList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** 
* A class representing GUI to display all usage of scooters . 
* 
* <p> The class creates GUI object to display the usage of all scooters,
* it includes dock no, time, pick or return, if overtime...
* 
* @author Zou Shiwen 
* @version 1.0 
* @since 22th April, 2019
* @see javax.swing
*/ 
public class UsageInfoGUI {

	JFrame frame;
	JPanel panel1,panel2,panel3;
	JTextArea textarea;
	JButton button;
	UserList userList;
	TransactionList transactionList;
	
	/** 
     * This method is the constructor used for
     * initiate UsageInfoGUI objects.
     */
	public UsageInfoGUI(UserList userlist,TransactionList transactionlist) {
		userList=userlist;
		transactionList=transactionlist;
		
		frame=new JFrame();
		Font Font1=new Font("arial", Font.PLAIN, 20);
		textarea=new JTextArea(40,50);
		textarea.setFont(Font1);
		String[] line=transactionList.allTrans();
		textarea.setText("       ID         | Dock | Slot  |  Is Picked |  Year |  Month  |   Date  |  Hour  |  Minutes  | isOverTime\n");
		for(int i=0;i<line.length;i++) {
				textarea.append(line[i]);
		}
		button=new JButton("Return");
		button.setFont(Font1);
		button.addActionListener(new ButtonListener());
		panel1=new JPanel();
		panel1.add(textarea);
		panel2=new JPanel();
		panel2.add(button);
		panel3=new JPanel();
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
		panel3.add(panel1);
		panel3.add(panel2);
		frame.getContentPane().add(BorderLayout.CENTER,panel3);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(900,800);
		frame.setVisible(true);
	}
	
	/** 
	* A class is the inner class of UsageInfoGUI,
	* it performs closing the frame.
	* 
	* <p> The class implements the interface of ActionListener
	* and perform accordingly action.
	* 
	* @author Zou Shiwen 
	* @version 1.0 
	* @since 22th April, 2019
	*/ 
	class ButtonListener implements ActionListener {
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
