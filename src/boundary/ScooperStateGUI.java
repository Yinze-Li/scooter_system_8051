package boundary;
import javax.swing.*;

import control.DockList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
    
/** 
* A class representing GUI to display all scooters' state . 
* 
* <p> The class creates GUI object to display the whole states of 15 scoopers,
* the number of scooters in use or in dock can be displayed.
* 
* @author Zou Shiwen 
* @version 1.0 
* @since 26th April, 2019
* @see javax.swing
*/ 
public class ScooperStateGUI {
	
	JFrame frame;
	JPanel panel1,panel2,panel3,panel4;
	JLabel clabel,ulabel,dlabel;
	JButton button;
	DockList dockList;
	private int parkedNum=0;
	private int usedNum=0;
	
	/** 
     * This method is the constructor used for
     * initiate ScoopterStateGUI objects.
     */
	public ScooperStateGUI(DockList docklist) {
		dockList=docklist;
		calculateScooperInUse(dockList);
		
		frame=new JFrame();
		Font Font1=new Font("arial", Font.PLAIN, 27);	
		clabel=new JLabel("Current State of Scooper:");
		clabel.setFont(Font1);
		ulabel=new JLabel(usedNum + " in use");
		ulabel.setFont(Font1);
		dlabel=new JLabel(parkedNum + " in dock");
		dlabel.setFont(Font1);
		button=new JButton("Return");
		button.setFont(Font1);
		button.addActionListener(new ButtonListener());
		panel1=new JPanel();
		panel1.add(clabel);
		panel2=new JPanel();
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
		panel2.add(ulabel);
		panel2.add(dlabel);
		panel3=new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.LEFT, 200, 30));
		panel3.add(button);
		panel4=new JPanel();
		panel4.setLayout(new BoxLayout(panel4,BoxLayout.Y_AXIS));
		panel4.add(panel2);
		panel4.add(panel3);
		frame.getContentPane().add(BorderLayout.NORTH, panel1);
		frame.getContentPane().add(BorderLayout.CENTER, panel4);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(800,800);
		frame.setVisible(true);
	}
	
	/** 
	* A class is the inner class of ScooperStateGUI. 
	* 
	* <p> The class implements the interface of ActionListener
	* and perform accordingly action.
	* 
	* @author Zou Shiwen 
	* @version 1.0 
	* @since 26th April, 2019
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
	
	/**
	 * This method is to calculate how many scooters are in use 
	 * and how many are available.
     * @param dockList All docks' states.
     * @version 1.0
     */
	public void calculateScooperInUse(DockList dockList) {
		boolean[][] scooperState=new boolean[3][8];
		for(int i=0;i<3;i++) {
		    scooperState[i]=dockList.dockState(i);
		}
		for(int i=0;i<3;i++) {
			for(int j=0;j<8;j++) {
				if(scooperState[i][j]==true) 
					parkedNum++;
			}
		}
		usedNum=15-parkedNum;
	}

}
