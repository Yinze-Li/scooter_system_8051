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
 * define a class of a Manager Dock Select GUI
 * 
 * @author dingfan
 *
 */
public class ManagerDockSelectGUI extends JFrame implements ActionListener {
	
	private DockList dockList;

	private DockStateGUI dockStateGUI;

	private JButton closeButton;
	private JButton dockA;
	private JButton dockB;
	private JButton dockC;
	private JPanel buttonJPanel;
	private JLabel infoLabel;
	
	public ManagerDockSelectGUI (DockList docklist) {
		this.dockList = docklist;
		
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
			dockStateGUI = new DockStateGUI(0,dockList);
		}
		else if(eventSource.equals(this.dockB)) {
			System.out.println("dockb");
			dockStateGUI = new DockStateGUI(1,dockList);
		}
		else if(eventSource.equals(this.dockC)) {
			System.out.println("dockc");
			dockStateGUI = new DockStateGUI(2,dockList);
		}
		
	}
}
