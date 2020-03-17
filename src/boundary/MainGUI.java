package boundary;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import control.DockList;
import control.TransactionList;
import control.UserList;


/**
 * define a class of a MainGUI
 * 
 * @author dingfan
 *
 */
class MainGUI extends JFrame implements ActionListener {

	private UserDockSelectGUI userDockSelectGUI;
	private ManagerFunctionGUI managerFunctionGUI;

	private DockList dockList;
	private UserList userList;
	private TransactionList transactionList;

	private JButton userButton;
	private JButton managerButton;

	public MainGUI(DockList docklist, UserList userlist, TransactionList transactionlist) {

		this.dockList = docklist;
		this.userList = userlist;
		this.transactionList = transactionlist;

		userButton = new JButton("user");
		managerButton = new JButton("manager");

		setLayout(new BorderLayout());
		this.getContentPane().add(userButton, BorderLayout.WEST);
		this.getContentPane().add(managerButton, BorderLayout.EAST);

		this.setTitle("Scooper Sharing System");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setVisible(true);

		userButton.addActionListener(this);
		managerButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton eventSource = (JButton) e.getSource();
		if (eventSource.equals(this.userButton)) {
			System.out.println("user");
			userDockSelectGUI = new UserDockSelectGUI(dockList, userList, transactionList);
			
		} else if (eventSource.equals(this.managerButton)) {
			System.out.printf("manager");
			managerFunctionGUI = new ManagerFunctionGUI(dockList, userList, transactionList);
		}
	}

}
