package boundary;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

/**
 * This class is to inform users that a scooter has been successfully returned.
 * @author hp
 *
 */
public class SuccessfullyReturned {

	private JFrame frame;

	public SuccessfullyReturned() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.control);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("RETURN SUCCESSFULLY!");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(92, 100, 242, 35);
		panel.add(lblNewLabel);
	}

}
