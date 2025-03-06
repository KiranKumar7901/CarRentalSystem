package Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Model.*;

public class AddNewCar implements Operation {
	
	@Override
	public void Operation(Database database,JFrame f, User user) {
		
		JFrame frame = new JFrame("Add New Car");
		frame.setSize(600,525);
		frame.setLocationRelativeTo(f);
		frame.getContentPane().setBackground(new Color(250,206,27));
		frame.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Add New Car",35);
		title.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		frame.add(title, BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new GridLayout(6, 2, 15, 15));
		panel.setBackground(null);
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		panel.add(new JLabel("Brand",22));
		JTextField brand = new JTextField(22);
		panel.add(brand);
		
		panel.add(new JLabel("Model",22));
		JTextField model = new JTextField(22);
		panel.add(model);

		panel.add(new JLabel("Color",22));
		JTextField color = new JTextField(22);
		panel.add(color);
		
		panel.add(new JLabel("Year",22));
		JTextField year = new JTextField(22);
		panel.add(year);
		
		panel.add(new JLabel("Price per hour",22));
		JTextField price = new JTextField(22);
		panel.add(price);
		
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

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(brand.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Brand cannot be empty");
					return;
				}
				if(model.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Model cannot be empty");
					return;
				}
				if(color.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Color cannot be empty");
					return;
				}
				if(year.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Year cannot be empty");
					return;
				}
				if(price.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Price cannot be empty");
					return;
				}
				
				double priceD;
				int yearI;
				
				try {
					yearI = Integer.parseInt(year.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(frame, "Year must be of type Integer");
					return;
				}
				try {
					priceD = Double.parseDouble(price.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(frame, "Price must be of type Double");
					return;
				}
				
				
				int available = 0;		
				try {
					ResultSet rs = database.getStatement().executeQuery("SELECT Count(*) from `cars`;");
					rs.next();
					
					int ID = rs.getInt("Count(*)");
					String insert = "INSERT INTO `cars`(`ID`, `Brand`, `Model`, `Color`, `Year`, `Price`, `Available`) VALUES ('"+ID+"','"+brand.getText()+"','"+model.getText()+"','"+color.getText()+"','"+yearI+"','"+priceD+"','"+available+"')";
					database.getStatement().execute(insert);
//					System.out.println("Car Added");
					JOptionPane.showMessageDialog(frame, "Car Added Successfully");
					frame.dispose();
				} catch (SQLException e1) {
//					e.printStackTrace();
					JOptionPane.showMessageDialog(frame, e1.getMessage());
				}
			}
			
		});
		panel.add(confirm);
		
		frame.add(panel,BorderLayout.CENTER);
		frame.setVisible(true);
		
//		System.out.println("Enter Brand Name: ");
//		String brand = scan.next();
//		System.out.println("Enter Model Name: ");
//		String model = scan.next();
//		System.out.println("Enter Color Name: ");
//		String color = scan.next();
//		System.out.println("Enter Year: ");
//		int year = scan.nextInt();
//		System.out.println("Enter Price per hour(in double): ");
//		double price = scan.nextInt();
//		
		
	}

}
