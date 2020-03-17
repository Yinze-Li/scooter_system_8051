package boundary;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.DockList;
import control.TransactionList;
import control.UserList;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
/**
 * GUI for entering the QM number then choose next operations.
 * @author SunYu
 */
public class DockFunctionGUI extends JFrame {

	private JPanel contentPane;
	private JTextField tf1;
	private PayaFine payaFine;
    private int Qmnum;
    private FtoSelectDock ftos;
    private Brep brep;
    private Rrep rrep;
    private ReturnScootersGUI ret;
    private SelectScootersGUI sel;


	public DockFunctionGUI(int dockNum,DockList docklist, UserList userlist, TransactionList transactionlist) {
		JFrame jf = new JFrame("Enter QM number");
		Toolkit tk = this.getToolkit();
		int width = 650;
		int height = 500;
		Dimension dm = tk.getScreenSize();
		jf.setLocation((int) (dm.getWidth() - width) / 2,
				(int) (dm.getHeight() - height) / 2);
		
		jf.setSize(450, 300);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
		contentPane = new JPanel();
		jf.setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnReturn = new JButton("Return"); 
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnReturn)
		        {
		            if((docklist.howManyScooter(dockNum))>7) {
		            	 ftos = new FtoSelectDock();
		            	 }
				    else{
				    	if(userlist.isUsing(Qmnum)==true) {
			           ret = new ReturnScootersGUI(Qmnum,dockNum,userlist,docklist);
					   }
				    	else {
				    		rrep = new Rrep();
				    	} 
					   }  
		        }
			}
		});
		btnReturn.setBounds(329, 167, 97, 47);
		contentPane.add(btnReturn);
		btnReturn.setVisible(false);
		
		JButton btnPay = new JButton("Pay Fine");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnPay)
		        {				 
		           payaFine = new PayaFine(Qmnum,userlist);
		        }
			}
		});
		btnPay.setBounds(10, 167, 97, 47);
		contentPane.add(btnPay);
		btnPay.setVisible(false);
	
		JButton btnBorrow = new JButton("Borrow");
		btnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnBorrow)
     	        {
					if(userlist.isBan(Qmnum)==true) {
						payaFine = new PayaFine(Qmnum,userlist);
					}
					else if(userlist.isBan(Qmnum)==false){
					 if((docklist.howManyScooter(dockNum))<1) {
		            	 ftos = new FtoSelectDock();
		            	 }		            	
		            else{
		            	if(userlist.isUsing(Qmnum)==false) {
		            	sel = new SelectScootersGUI(Qmnum,dockNum,userlist,docklist);
                        }
		            	else {
		            		brep = new Brep();
		            	}
					 }
					}
		        }
			}
		});
		btnBorrow.setBounds(168, 167, 97, 47);
		contentPane.add(btnBorrow);
		btnBorrow.setVisible(false);
		
		JLabel lblTitle = new JLabel("Please enter your QM number to borrow, return or pay fines");
		lblTitle.setBounds(10, 15, 416, 47);
		contentPane.add(lblTitle);
		
		JLabel lblWar1 = new JLabel("\u221APlease select the next step!");
		lblWar1.setForeground(Color.GREEN);
		lblWar1.setBounds(150, 97, 226, 15);
		contentPane.add(lblWar1);
		lblWar1.setVisible(false);
		
		JLabel lblWar2 = new JLabel("\u00D7Please check your number again!");
		lblWar2.setForeground(Color.RED);
		lblWar2.setBounds(150, 122, 248, 15);
		contentPane.add(lblWar2);
		lblWar2.setVisible(false);
		
		tf1 = new JTextField();
		tf1.setBounds(10, 96, 87, 41);
		contentPane.add(tf1);
		tf1.setColumns(10);		
		tf1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==tf1)
				  {
				    Qmnum = Integer.parseInt(tf1.getText());
				    
					   if(userlist.search(Qmnum)>=0) {
						   lblWar1.setVisible(true);
						   lblWar2.setVisible(false);
						//   tf1.setText(Qmnum+" is a right QM number in system");
						   btnPay.setVisible(true);
						   btnReturn.setVisible(true);
						   btnBorrow.setVisible(true);
					   }
					   else {
						   tf1.setText("");
						   lblWar1.setVisible(false);
						   lblWar2.setVisible(true);
				      //     tf1.setText("Wrong number or not enroll, please check again!");
				           btnPay.setVisible(false);
						   btnReturn.setVisible(false);
						   btnBorrow.setVisible(false);
					   }
				  }
			};
		});
				
		JLabel lblRem = new JLabel("(Press \"Enter\" to check your QM number with 9 digits)");
		lblRem.setBounds(48, 72, 340, 15);
		contentPane.add(lblRem);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(168, 228, 97, 23);
		contentPane.add(btnBack);		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnBack)
		        {
					jf.dispose();
		        }
            }
		});
		
		jf.setVisible(true);
	}
}
