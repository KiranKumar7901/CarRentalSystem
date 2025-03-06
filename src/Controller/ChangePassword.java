package Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Model.Database;
import Model.JButton;
import Model.JLabel;
import Model.JPasswordField;
import Model.Operation;
import Model.User;

public class ChangePassword implements Operation{

	@Override
	public void Operation(Database database, JFrame f, User user) {
		
		JFrame frame = new JFrame("Edit User Data");
		frame.setSize(600,380);
		frame.setLocationRelativeTo(f);
		frame.getContentPane().setBackground(new Color(250,206,27));
		frame.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Change Password",35);
		title.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		frame.add(title, BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new GridLayout(4, 2, 15, 15));
		panel.setBackground(null);
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		panel.add(new JLabel("Old Password",22));
		JPasswordField oldPassword = new JPasswordField(22);
		panel.add(oldPassword);
		
		panel.add(new JLabel("New Password",22));
		JPasswordField newPassword = new JPasswordField(22);
		panel.add(newPassword);

		panel.add(new JLabel("Confirm Password",22));
		JPasswordField confirmPassword = new JPasswordField(22);
		panel.add(confirmPassword);
		
		JButton cancel = new JButton("Cancel",22);
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
			
		});
		panel.add(cancel);
		
		JButton confirm = new JButton("Confirm",22);
		confirm.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent ev) {
				
				if(oldPassword.getText().equals("")){
					JOptionPane.showMessageDialog(frame, "Old Password Cannot be empty");
					return;
				}
				if(newPassword.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "New Password Cannot be empty");
					return;
				}
				if(confirmPassword.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Confirm Password Cannot be empty");
					return;
				}
				if(!oldPassword.getText().equals(user.getPassword())) {
					JOptionPane.showMessageDialog(frame, "Incorrect Password");
					return;
				}
				if(!newPassword.getText().equals(confirmPassword.getText())) {
					JOptionPane.showMessageDialog(frame, "Password doesn't match");
					return;
				}
				
				try {
					String update = "UPDATE `users` SET `Password`='"+newPassword.getText()+"' where `ID`='"+user.getID()+"';";
//					System.out.println("Password Changed Successfully");
					JOptionPane.showMessageDialog(frame, "Password Changed Successfully");
					user.setPassword(newPassword.getText());
					database.getStatement().execute(update);
					frame.dispose();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(frame, e.getMessage());
//					e.printStackTrace();
				}
			}
			
		});
		panel.add(confirm);
		
		frame.add(panel,BorderLayout.CENTER);
		frame.setVisible(true);
		
//		System.out.println("Enter Old Password: ");
//		String oldPassword = scan.next();
//		if(!oldPassword.equals(user.getPassword())) {
//			System.out.println("Password doesn't match");
//			return;
//		}
//		
//		System.out.println("Enter New Password: ");
//		String newPassword = scan.next();
//		System.out.println("Confirm Password: ");
//		String confirmPassword = scan.next();
//		while(!newPassword.equals(confirmPassword)) {
//			System.out.println("Enter New Password: ");
//			newPassword = scan.next();
//			System.out.println("Confirm Password: ");
//			confirmPassword = scan.next();	
//		}
//		
//		
		
	}

}
