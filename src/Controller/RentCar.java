package Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Model.Car;
import Model.Database;
import Model.JButton;
import Model.JLabel;
import Model.JTextField;
import Model.Operation;
import Model.Rent;
import Model.User;

public class RentCar implements Operation {

	private JTextField brand,model,color,year,price;
	private Database database;
	private JFrame frame;
	
	@Override
	public void Operation(Database database, JFrame f, User user) {
		
		this.database = database;
		frame = new JFrame("Rent Car");
		frame.setSize(600,650);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(250,206,27));
		frame.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Rent Car",35);
		title.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		frame.add(title, BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new GridLayout(8,2,15,15));
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
		brand.setEditable(false);
		panel.add(brand);
		
		panel.add(new JLabel("Model",22));
		model = new JTextField(22);
		model.setEditable(false);
		panel.add(model);

		panel.add(new JLabel("Color",22));
		color = new JTextField(22);
		color.setEditable(false);
		panel.add(color);
		
		panel.add(new JLabel("Year",22));
		year = new JTextField(22);
		year.setEditable(false);
		panel.add(year);
		
		panel.add(new JLabel("Price per hour",22));
		price = new JTextField(22);
		price.setEditable(false);
		panel.add(price);
		
		panel.add(new JLabel("Hours",22));
		JTextField hours = new JTextField(22);
		panel.add(hours);
		
		JButton showCars = new JButton("Show All Cars",22);
		showCars.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ViewCars().Operation(database, f, user);
			}
			
		});
		panel.add(showCars);
		
		JButton confirm = new JButton("Confirm",22);
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(id.getSelectedItem().toString().equals("")){
					JOptionPane.showMessageDialog(frame, "ID cannot be empty");
					return;
				}
				if(hours.getText().equals("")){
					JOptionPane.showMessageDialog(frame, "Hours cannot be empty");
					return;
				}
				
				int hoursI;
				try {
					hoursI = Integer.parseInt(hours.getText());
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(frame, "Hours must be of type Integer");
					return;
				}
				
				try {			
					ResultSet rs0 = database.getStatement().executeQuery("SELECT * from `cars` where `ID`='"+id.getSelectedItem().toString()+"';");
					rs0.next();
					Car car = new Car();
					car.setID(rs0.getInt("ID"));
					car.setBrand(rs0.getString("Brand"));
					car.setModel(rs0.getString("Model"));
					car.setColor(rs0.getString("Color"));
					car.setYear(rs0.getInt("Year"));
					car.setPrice(rs0.getInt("Price"));
					car.setAvailable(rs0.getInt("Available"));
					if(car.getAvailable()!=0) {
//						System.out.println("Car is not available");
						JOptionPane.showMessageDialog(frame, "Car is not availabel");
						return;
					}
					ResultSet rs1 = database.getStatement().executeQuery("SELECT Count(*) from `rents`;");
					rs1.next();
						
					int ID = rs1.getInt("Count(*)");	
					
					double total = car.getPrice()*hoursI;
					Rent rent = new Rent();
					
					String insert = "INSERT INTO `rents`(`ID`, `User`, `Car`, `DateTime`, `Hours`, `Total`, `Status`) VALUES ('"+ID+"','"+user.getID()+"','"+car.getID()+"','"+rent.getDateTime()+"','"+hoursI+"','"+total+"','0')";
					database.getStatement().execute(insert);
//					System.out.println("Car Rented Successfully!");
					JOptionPane.showMessageDialog(frame, "Car Rented Successfully"+"\nTotal: "+total+"$");
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
		frame.requestFocus();
		
//		System.out.println("Enter Car ID(int): (-1 to show all cars");
//		int carID = scan.nextInt();
//		
//		while(carID==-1) {
//			new ViewCars().Operation(database, scan, user);
//			System.out.println("Enter Car ID(int): (-1 to show all cars");
//			carID = scan.nextInt();
//		}
//		
//		System.out.println("Enter hours(int):");
//		int hours = scan.nextInt();
//		
//		
		
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
				price.setText(String.valueOf(rs1.getDouble("Price"))+" $");
				
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
