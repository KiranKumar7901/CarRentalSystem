package Controller;

import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import Model.*;

public class UpdateCar implements Operation 
{

	private JTextField brand,model,color,year,price;
	private Database database;
	private JFrame frame;
	@Override
	public void Operation(Database database, JFrame f, User user) {
		
		this.database = database;
		frame = new JFrame("Update Car");
		frame.setSize(600,600);
		frame.setLocationRelativeTo(f);
		frame.getContentPane().setBackground(new Color(250,206,27));
		frame.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Update Car",35);
		title.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		frame.add(title, BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new GridLayout(7, 2, 15, 15));
		panel.setBackground(null);
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		panel.add(new JLabel("ID",22));
		String[] ids = new String[] {" "};
		ArrayList<Integer> idsArray = new ArrayList<>();
		try {
			ResultSet rs0 = database.getStatement().executeQuery("SELECT `ID`,`Available` FROM `cars`;");
			while(rs0.next()) {
				if(rs0.getInt("Available")<2) idsArray.add(rs0.getInt("ID"));
			}
			
		} catch (Exception e0) {
			JOptionPane.showMessageDialog(frame, e0.getMessage());
			frame.dispose();
		}
		
		ids = new String[idsArray.size()+1];
		ids[0]=" ";
		for(int i=1;i<=idsArray.size();i++) {
			ids[i] = String.valueOf(idsArray.get(i-1));
		}
		Model.JComboBox id = new Model.JComboBox(ids, 22);
		id.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateData(id.getSelectedItem().toString());
			}
			
		});
		panel.add(id);
		
		panel.add(new JLabel("Brand",22));
		brand = new JTextField(22);
		panel.add(brand);
		
		panel.add(new JLabel("Model",22));
		model = new JTextField(22);
		panel.add(model);

		panel.add(new JLabel("Color",22));
		color = new JTextField(22);
		panel.add(color);
		
		panel.add(new JLabel("Year",22));
		year = new JTextField(22);
		panel.add(year);
		
		panel.add(new JLabel("Price per hour",22));
		price = new JTextField(22);
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
				
				if(id.getSelectedItem().toString().equals("")) {
					JOptionPane.showMessageDialog(frame, "ID cannot be empty");
					return;
				}
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
				
				try{
					String update = "UPDATE `cars` SET `Brand`='"+brand.getText()+"',`Model`='"+model.getText()+"',`Color`='"+color.getText()+"',`Year`='"+yearI+"',`Price`='"+priceD+"' WHERE `ID`='"+id.getSelectedItem().toString()+"';";	
					database.getStatement().execute(update);
//					System.out.println("Car Updated Successfully");
					JOptionPane.showMessageDialog(frame, "Car Updated Successfully");
					frame.dispose();
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(frame, e1.getMessage());
				}
				
			}
			
		});
		panel.add(confirm);
		
		frame.add(panel,BorderLayout.CENTER);
		frame.setVisible(true);
		frame.requestFocus();
		
//		System.out.println("Enter car ID(int): (-1 to show all cars");
//		int ID = scan.nextInt();
//		while(ID==-1) {
//			new ViewCars().Operation(database, scan, user);
//			System.out.println("Enter car ID(int): (-1 to show all cars");
//			ID = scan.nextInt();			
//		}
//		
//		try {
//			ResultSet rs1 = database.getStatement().executeQuery("SELECT * from `cars` where `ID`='"+ID+"';");
//			rs1.next();
//			Car car = new Car();
//			car.setID(rs1.getInt("ID"));
//			car.setBrand(rs1.getString("Brand"));
//			car.setModel(rs1.getString("Model"));
//			car.setColor(rs1.getString("Color"));
//			car.setYear(rs1.getInt("Year"));
//			car.setPrice(rs1.getInt("Price"));
//			car.setAvailable(rs1.getInt("Available"));
//			if(car.getAvailable()>1) {
//				System.out.println("Car doesn't exist");
//				return;
//			}
//			
//			System.out.println("Enter Brand: (-1: "+car.getBrand()+" )");
//			String brand = scan.next();
//			if(brand.equals("-1")) brand = car.getBrand();
//			
//			System.out.println("Enter Model: (-1: "+car.getModel()+" )");
//			String model = scan.next();
//			if(model.equals("-1")) model = car.getModel();
//			
//			System.out.println("Enter Color: (-1: "+car.getColor()+" )");
//			String color = scan.next();
//			if(color.equals("-1")) color = car.getColor();
//			
//			System.out.println("Enter Year: (-1: "+car.getYear()+" )");
//			int year = scan.nextInt();
//			if(year==-1) year = car.getYear();
//			
//			System.out.println("Enter Price: (-1: "+car.getPrice()+" )");
//			double price = scan.nextDouble();
//			if(price==-1) price = car.getPrice();
//			
//			String update = "UPDATE `cars` SET `Brand`='"+brand+"',`Model`='"+model+"',`Color`='"+color+"',`Year`='"+year+"',`Price`='"+price+"' WHERE `ID`='"+ID+"';";	
//			database.getStatement().execute(update);
//			System.out.println("Car Updated Successfully");
//			
//		} catch (SQLException e) {
//			// TODO: handle exception
//		}
		
		
	}
	
	private void updateData(String ID) {
		if(ID.equals(" ")) {
			brand.setText("");
			model.setText("");
			color.setText("");
			price.setText("");
			year.setText("");
		}
		else {
			try {
				ResultSet rs1 = database.getStatement().executeQuery("SELECT * from `cars` where `ID`='"+ID+"';");
				rs1.next();
				Car car = new Car();
				car.setID(rs1.getInt("ID"));
				brand.setText(rs1.getString("Brand"));
				model.setText(rs1.getString("Model"));
				color.setText(rs1.getString("Color"));
				year.setText(String.valueOf(rs1.getInt("Year")));
				price.setText(String.valueOf(rs1.getDouble("Price")));
				
//				car.setAvailable(rs1.getInt("Available"));
//				if(car.getAvailable()>1) {
//					System.out.println("Car doesn't exist");
//					return;
//				}
			}catch(Exception e) {
				JOptionPane.showMessageDialog(frame, e.getMessage());
				frame.dispose();
			}
	}

}
}
