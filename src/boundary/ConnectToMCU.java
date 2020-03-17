package boundary;

import control.*;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import connect.*;

public class ConnectToMCU implements ActionListener {

	private UserList userList;
	private DockList dockList;
	private TransactionList transList;
	private boolean isBor;
	private JFrame frame;
	private JButton bor;
	private JButton ret;
	private JLabel txt;
	private JLabel hint;
	private String inputID;
	private int ID;
	private int dock;
	private Connector connector;

	public ConnectToMCU(int dockNum, DockList dl, UserList ul, TransactionList tl) {
		this.userList = ul;
		this.dockList = dl;
		this.transList = tl;
		this.connector = new Connector();
		this.dock = dockNum;
		this.startFrame();
	}

	private void startFrame() {
		frame = new JFrame("mcu");
		frame.setLayout(new GridLayout(4, 0));
		frame.setVisible(true);
		frame.setSize(500, 500);

		frame.addWindowListener(new WindowListener() {
			@Override
			public void windowActivated(WindowEvent arg0) {
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Closing the serial port...");
				try {
					connector.close();
				} catch (Exception e) {

				}

			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
			}
		});
		txt = new JLabel("");
		hint = new JLabel("Please choose borrow or return");
		bor = new JButton("borrow");
		ret = new JButton("return");

		bor.addActionListener(this);
		ret.addActionListener(this);

		frame.add(hint);
		frame.add(txt);
		frame.add(bor);
		frame.add(ret);

//		dockList.dockSave();
//		userList.save();
//		transList.allTrans();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == bor) {
			isBor = true;
//			bor.removeActionListener(this);
//			ret.removeActionListener(this);
			if (dockList.howManyScooter(dock) == 0) {
				hint.setText("No scooter in this dock!!!");
				return;
			}
			hint.setText("Enter your ID on MCU");
			getID();
		} else if (arg0.getSource() == ret) {
			isBor = false;
//			bor.removeActionListener(this);
//			ret.removeActionListener(this);
			if (dockList.howManyScooter(dock) == 8) {
				hint.setText("No empty slot in this dock!!!");
				return;
			}
			hint.setText("Enter your ID on MCU");
			getID();
		}
	}

	private void getID() {
		connector.sendByte((byte) 10);
		inputID = "";
		byte b;
		do {
			b = connector.receive();
			if (b == 10) {
				break;
			}
			if (b != -1) {
				String n = "" + b;
				inputID = inputID + n;
				System.out.println(inputID);
				txt.setText(inputID);
			}
		} while (true);
		System.out.println("ID end");
		try {
			ID = Integer.parseInt(inputID);
		} catch (Exception e) {
			hint.setText("wrong input or invalid ID number");
			connector.sendByte((byte) 9);
			wait(1000);
			connector.sendByte((byte) 9);
			wait(1000);
			connector.sendByte((byte) 9);
			wait(1000);
			return;
		}

		if (!userList.checkIDExist(ID)) {
			System.out.println("ID not exists");
			hint.setText("ID not exists, close the window and try again");
			txt.setText("");
			inputID = "";
			connector.sendByte((byte) 9);
			wait(1000);
			connector.sendByte((byte) 9);
			wait(1000);
			connector.sendByte((byte) 9);
			wait(1000);
			return;
		}

		if (userList.isBan(ID)) {
			System.out.println("Sorry! You are banned");
			hint.setText("Sorry! You are banned, close the window");
			txt.setText("");
			inputID = "";
			connector.sendByte((byte) 9);
			wait(1000);
			connector.sendByte((byte) 9);
			wait(1000);
			connector.sendByte((byte) 9);
			wait(1000);
			return;
		}

		if (userList.isUsing(ID) && isBor) {
			System.out.println("Please return your scooter first");
			hint.setText("Please return your scooter first, close the window and try again");
			txt.setText("");
			inputID = "";
			connector.sendByte((byte) 9);
			wait(1000);
			connector.sendByte((byte) 9);
			wait(1000);
			connector.sendByte((byte) 9);
			wait(1000);
			return;
		}

		if (!userList.isUsing(ID) && !isBor) {
			System.out.println("Please pick up a scooter first");
			hint.setText("Please pick up a scooter first, close the window and try again");
			txt.setText("");
			inputID = "";
			connector.sendByte((byte) 9);
			wait(1000);
			connector.sendByte((byte) 9);
			wait(1000);
			connector.sendByte((byte) 9);
			wait(1000);
			return;
		}

		if (isBor) {
			borrow();
		} else {
			ret();
		}

	}

	private void borrow() {
		int slot = dockList.pickWhich(dock) + 1;
		connector.sendByte((byte) slot);
		wait(1000);
		connector.sendByte((byte) slot);
		wait(1000);
		connector.sendByte((byte) slot);
		hint.setText("Please pick up at slot " + slot);
		byte flag = connector.receive();
		if (flag == -1) {
			System.out.println("error...");
		} else if (flag == 100) {
			dockList.pickScooter(dock, slot);
			hint.setText("Pick up successfully");
			userList.pickRecord(ID);
			transList.newTrans(ID, dock, slot, true, false);
			userList.save();
			dockList.dockSave();
		} else if (flag == 99) {
			hint.setText("Time out! Fail !");
		}
	}

	private void ret() {
		int slot = dockList.returnWhich(dock) + 1;
		connector.sendByte((byte) slot);
		wait(1000);
		connector.sendByte((byte) slot);
		wait(1000);
		connector.sendByte((byte) slot);
		hint.setText("Please return at slot " + slot);
		byte flag = connector.receive();
		if (flag == -1) {
			System.out.println("error...");
		} else if (flag == 100) {
			dockList.returnScooter(dock, slot);
			hint.setText("Return successfully");
			userList.unuse(ID);
			transList.newTrans(ID, dock, slot, false, false);
			userList.save();
			dockList.dockSave();
		} else if (flag == 99) {
			hint.setText("Time out! Fail !");
		}
	}

	private void wait(int time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {

		}
	}

}
