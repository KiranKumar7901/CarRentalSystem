package Model;

import java.awt.Font;

public class JLabel extends javax.swing.JLabel {
	
	public JLabel(String text, int fontSize) {
		super(text);
		setFont(new Font("SensSerif",Font.BOLD,fontSize));
		setBackground(null);
		setHorizontalAlignment(JLabel.CENTER);
		
	}
}
