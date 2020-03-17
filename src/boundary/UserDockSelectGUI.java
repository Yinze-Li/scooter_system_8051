/**
 * 
 */
package boundary;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import control.DockList;
import control.TransactionList;
import control.UserList;

/**
 * define a class of a user Dock Select GUI
 * 
 * @author dingfan
 *
 */
public class UserDockSelectGUI extends JFrame implements ActionListener {

	private DockList dockList;
	private UserList userList;
	private TransactionList transactionList;

	private ConnectToMCU dockFunctionGUI;

	private JButton closeButton;
	private JButton dockA;
	private JButton dockB;
	private JButton dockC;
	private JPanel buttonJPanel;
	private JLabel infoLabel;
	

	public UserDockSelectGUI(DockList docklist, UserList userlist, TransactionList transactionlist) {
		this.dockList = docklist;
		this.userList = userlist;
		this.transactionList = transactionlist;

		dockA = new JButton("Dock A");
		dockB = new JButton("Dock B");
		dockC = new JButton("Dock C");
		closeButton = new JButton("Close");
		
		buttonJPanel = new JPanel();
		infoLabel = new JLabel("Please Select The Dock Station",SwingConstants.CENTER);
		
		
		buttonJPanel.setLayout(new GridLayout(4,1));
		buttonJPanel.add(dockA);
		buttonJPanel.add(dockB);
		buttonJPanel.add(dockC);
		buttonJPanel.add(closeButton);
		
		setLayout(new BorderLayout());
		this.getContentPane().add(infoLabel, BorderLayout.WEST);
		this.getContentPane().add(buttonJPanel, BorderLayout.CENTER);

		this.setTitle("Select The Dock Station");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(500, 500);
		this.setVisible(true);
		
		closeButton.addActionListener(this);
		dockA.addActionListener(this);
		dockB.addActionListener(this);
		dockC.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton eventSource = (JButton) e.getSource();
		if (eventSource.equals(this.closeButton)) {
			this.dispose();
		} 
		else if(eventSource.equals(this.dockA)) {
			System.out.println("docka");
			dockFunctionGUI = new ConnectToMCU(0, dockList, userList, transactionList);
		}
		else if(eventSource.equals(this.dockB)) {
			System.out.println("dockb");
			dockFunctionGUI = new ConnectToMCU(1, dockList, userList, transactionList);
		}
		else if(eventSource.equals(this.dockC)) {
			System.out.println("dockc");
			dockFunctionGUI = new ConnectToMCU(2, dockList, userList, transactionList);
		}
		
	}
}
