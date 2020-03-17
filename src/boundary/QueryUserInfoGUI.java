package boundary;

import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;

import control.UserList;

import java.awt.event.ActionListener;

/**
 * 
 * @author liyin
 *
 */
public class QueryUserInfoGUI implements ActionListener {

	private JFrame frame;
	private JTextField field;
	private JButton search;
	private JButton ban;
	private JButton unban;
	private JButton unuse;
	private JButton del;
	private JLabel label;
	private UserList userList;
	private JTextArea area;
	private JPanel sPanel;
	private JPanel nPanel;
	private int ID;

	public QueryUserInfoGUI(UserList userList) {
		this.userList = userList;
		startFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == search) {
			if (field.getText().equals("")) {
				return;
			}
			try {
				ID = Integer.parseInt(field.getText());
			} catch (Exception e1) {
				label.setText("wrong input");
				return;
			}
			label.setText("Information of " + ID);
			area.setText("");
			area.append(userList.oneUserInfo(ID));
		}
		if (userList.oneUserInfo(ID) == null) {
			label.setText("can't find this user!");
		} else {
			if (e.getSource() == ban) {
				userList.ban(ID);
				area.setText("");
				area.append(userList.oneUserInfo(ID));
				userList.save();
			}
			if (e.getSource() == unban) {
				userList.unban(ID);
				area.setText("");
				area.append(userList.oneUserInfo(ID));
				userList.save();
			}
			if (e.getSource() == unuse) {
				userList.unuse(ID);
				area.setText("");
				area.append(userList.oneUserInfo(ID));
				userList.save();
			}
			if (e.getSource() == del) {
				userList.delete(ID);
				area.setText("");
				area.append(userList.oneUserInfo(ID));
				userList.save();
			}
		}

	}

	public void startFrame() {
		ID = 0;
		frame = new JFrame("Query User Information");
		frame.setSize(600, 800);
		field = new JTextField(10);
		label = new JLabel("type ID then search");
		search = new JButton("search");
		search.addActionListener(this);
		ban = new JButton("ban");
		ban.addActionListener(this);
		unban = new JButton("unban");
		unban.addActionListener(this);
		unuse = new JButton("unuse");
		unuse.addActionListener(this);
		del = new JButton("delete");
		del.addActionListener(this);
		frame.setVisible(true);
		area = new JTextArea(300, 400);
		String[] s = userList.allInfo();

		nPanel = new JPanel();
		sPanel = new JPanel();
		nPanel.add(label);
		nPanel.add(field);
		nPanel.add(search);
		sPanel.add(ban);
		sPanel.add(unban);
		sPanel.add(unuse);
		sPanel.add(del);
		frame.setLayout(new BorderLayout());
		frame.add(BorderLayout.NORTH, nPanel);
		frame.add(BorderLayout.CENTER, area);
		frame.add(BorderLayout.SOUTH, sPanel);
		if (s == null) {
			area.append("no user at all");
		} else {
			for (int i = 0; i < s.length; i++) {
				area.append(s[i] + "\n");
			}
		}
	}

}
