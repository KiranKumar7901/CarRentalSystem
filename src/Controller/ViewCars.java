package Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.sql.*;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Model.*;

public class ViewCars implements Operation {

	@Override
	public void Operation(Database database, JFrame f, User user) {
		
		JFrame frame = new JFrame("Cars");
		frame.setSize(1000,600);
		frame.setLocationRelativeTo(f);
		frame.getContentPane().setBackground(new Color(250,206,27));
		frame.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Cars",35);
		title.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		frame.add(title, BorderLayout.NORTH);
		
		String [] header = new String[] {
			"ID","Brand","Model","Color","Year","Price","Available"	
		};
		
		String select = "SELECT * from `cars`;";
		ArrayList<Car> cars = new ArrayList<>();
		try {
			ResultSet rs = database.getStatement().executeQuery(select);
			while(rs.next()) {
				Car car = new Car();
				car.setID(rs.getInt("ID"));
				car.setBrand(rs.getString("Brand"));
				car.setModel(rs.getString("Model"));
				car.setColor(rs.getString("Color"));
				car.setYear(rs.getInt("Year"));
				car.setPrice(rs.getDouble("Price"));
				int available = rs.getInt("Available");
				if(available<2) {
					car.setAvailable(available);
					cars.add(car);
				}
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, e.getMessage());
		}
		
		
		String[][] carsData = new String[cars.size()][7];
		
		for(int j=0;j<cars.size();j++) {
			Car c = cars.get(j);
			if(c.getAvailable()<2) {
				carsData[j][0] = String.valueOf(c.getID());
				carsData[j][1] = c.getBrand();
				carsData[j][2] = c.getModel();
				carsData[j][3] = c.getColor();
				carsData[j][4] = String.valueOf(c.getYear());
				carsData[j][5] = String.valueOf(c.getPrice())+" $";
				
//				System.out.println("ID: "+c.getID());
//				System.out.println("Brand: "+c.getBrand());
//				System.out.println("Model: "+c.getModel());
//				System.out.println("Color: "+c.getColor());
//				System.out.println("Year: "+c.getYear());
//				System.out.println("Price: "+c.getPrice());
				
				if(c.getAvailable()==0) {
					carsData[j][6] = "Available";
				}
				else{
					carsData[j][6] = "Not Available";
//					System.out.println("Status: \tNot Available");
				}
//				System.out.println("---------------------------------");
			}
		}
		
		Color color2 = new Color(252,242,202);
		JScrollPane panel = new JScrollPane(new JTable(carsData,header,Color.black,color2));
		panel.setBackground(null);
		panel.getViewport().setBackground(null);
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}

}
