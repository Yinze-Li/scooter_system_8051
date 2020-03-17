package boundary;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
/**
 * GUI for reminding users that the dock they chose has no scooter/slot available.
 * @author SunYu
 */
public class FtoSelectDock extends JFrame {

	private JPanel contentPane;

	public FtoSelectDock() {
		JFrame jf = new JFrame("Fail");
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
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 416, 145);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblx = new JLabel("<html><body><p>This dock station has no scooter/slot available, please go to another dock station or wait for some time.");
		panel.add(lblx, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(61, 194, 290, 57);
		contentPane.add(panel_1);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton)
		        {
					jf.dispose();
		        }
			}
		});
		panel_1.setLayout(new BorderLayout(0, 0));
		panel_1.add(btnNewButton);
		jf.setVisible(true);
	}
}
