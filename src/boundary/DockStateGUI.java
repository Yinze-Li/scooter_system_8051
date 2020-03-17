package boundary;
import javax.swing.*;

import control.DockList;

import java.awt.*;
import java.awt.event.*;

/** 
* A class representing GUI to display all slots' state in a particular dock . 
* 
* <p> The class creates GUI object to display each state of 8 slots
* in a given dock,
* the slot state can be parked or empty.
* 
* @author Zou Shiwen 
* @version 1.0 
* @since 24th April, 2019
* @see javax.swing
*/ 
public class DockStateGUI {

	JFrame frame;
	JButton button;
	JLabel[] label;
	JPanel panel1,panel2,panel3,panel4;
	String[] state;
	int dockNum;
	DockList dockList;
	
	/** 
     * This method is the constructor used for
     * initiate DockStateGUI objects.
     */
	 public DockStateGUI(int dock, DockList docklist) {
		 dockNum=dock;
		 dockList=docklist;
		 this.checkSlotsState(dock);
		 
		 frame=new JFrame();
			Font Font1=new Font("arial", Font.PLAIN, 27);
			panel1=new JPanel();
			panel2=new JPanel();
			panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
			label= new JLabel[8];
			for(int i=0;i<8;i++) 
	            label[i]=new JLabel("Slot"+(i+1)+": "+ state[i]); 
			for(int i=0;i<8;i++) 
	            label[i].setFont(Font1);
			for(int i=0;i<8;i++) 
	            panel2.add(label[i]);
			panel1.add(BorderLayout.CENTER,panel2);
			button=new JButton("Return");
			button.setFont(Font1);
			button.addActionListener(new ButtonListener());
			panel3=new JPanel();
			panel3.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 100));
	        panel3.add(button);
	        panel4=new JPanel();
	        panel4.add(BorderLayout.CENTER, panel3);
			frame.getContentPane().add(BorderLayout.CENTER,panel1);
			frame.getContentPane().add(BorderLayout.EAST,panel4);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setSize(800,800);
			frame.setVisible(true);
	 }
	 
	   /**
		 * This method is to check each slot's state in a particular dock. 
	     * @param dock The no. of dock to check.
	     * @version 1.0
	     */
	public void checkSlotsState(int dock) {
		boolean[] dockState=new boolean[8];
		dockState=dockList.dockState(dock);
		state=new String[8];
		for(int i=0;i<8;i++) {
			    if(dockState[i]==true)
				   state[i]="Parked";
			    else
				   state[i]="Empty";
	    }
	}
	
	/** 
	* A class is the inner class of DockStateGUI. 
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
	
}
