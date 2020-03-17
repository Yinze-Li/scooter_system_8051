/**
 * 
 */
package boundary;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import control.DockList;
import control.TransactionList;
import control.UserList;

/**
 * define a class of a Manager Function GUI
 * 
 * @author dingfan
 *
 */
public class ManagerFunctionGUI extends JFrame implements ActionListener {

	private DockList dockList;
	private UserList userList;
	private TransactionList transactionList;

	private QueryUserInfoGUI queryUserInfoGUI;
	private EnrollmentGUI enrollmentGUI;
	private UsageInfoGUI usageInfoGUI;
	private ScooperStateGUI scooperStateGUI;
	private SendEmailGUI sendEmailGUI;
	private ManagerDockSelectGUI managerDockSelectGUI;

	private JButton closeButton;
	private JButton queryUserInfoButton;
	private JButton enrollmentButton;
	private JButton usageInformationButton;
	private JButton scooperStateButton;
	private JButton sendEmailButton;
	private JButton managerDockSelectButton;

	private JPanel buttonJPanel;



	public ManagerFunctionGUI(DockList docklist, UserList userlist, TransactionList transactionlist) {
		this.dockList = docklist;
		this.userList = userlist;
		this.transactionList = transactionlist;

		closeButton = new JButton("close");

		enrollmentButton = new JButton("enroll");
		queryUserInfoButton = new JButton("user information");
		managerDockSelectButton = new JButton("dock state");
		scooperStateButton = new JButton("scooper state");
		usageInformationButton = new JButton("usage information");
		sendEmailButton = new JButton("send e-mail");

		buttonJPanel = new JPanel();

		setLayout(new BorderLayout());
		this.getContentPane().add(closeButton, BorderLayout.SOUTH);
		this.getContentPane().add(buttonJPanel, BorderLayout.CENTER);

		buttonJPanel.setLayout(new GridLayout(3, 2));
		buttonJPanel.add(enrollmentButton);
		buttonJPanel.add(queryUserInfoButton);
		buttonJPanel.add(managerDockSelectButton);
		buttonJPanel.add(scooperStateButton);
		buttonJPanel.add(usageInformationButton);
		buttonJPanel.add(sendEmailButton);

		this.setTitle("MANAGER SYSTEM");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(500, 500);
		this.setVisible(true);
		
		
		
		enrollmentButton.addActionListener(this);
		queryUserInfoButton.addActionListener(this);
		managerDockSelectButton.addActionListener(this);
		scooperStateButton.addActionListener(this);
		usageInformationButton.addActionListener(this);
		sendEmailButton.addActionListener(this);
		
		closeButton.addActionListener(this);
		
		

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton eventSource = (JButton) e.getSource();
		if(eventSource.equals(this.closeButton)) {
			this.dispose();
		}
		else if(eventSource.equals(this.enrollmentButton)) {
			enrollmentGUI = new EnrollmentGUI(userList);
		}
		else if(eventSource.equals(this.queryUserInfoButton)) {
			queryUserInfoGUI = new QueryUserInfoGUI(userList);
		}
		else if(eventSource.equals(this.managerDockSelectButton)) {
			managerDockSelectGUI = new ManagerDockSelectGUI(dockList);
		}
		else if(eventSource.equals(this.scooperStateButton)) {
			scooperStateGUI = new ScooperStateGUI(dockList);
		}
		else if(eventSource.equals(this.usageInformationButton)) {
			usageInfoGUI = new UsageInfoGUI(userList,transactionList);
		}
		else if(eventSource.equals(this.sendEmailButton)) {
			sendEmailGUI = new SendEmailGUI(userList,transactionList);
		}
			

	}
}
