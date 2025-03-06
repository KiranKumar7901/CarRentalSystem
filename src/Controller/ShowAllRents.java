package Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import Model.Car;
import Model.Client;
import Model.Database;
import Model.JLabel;
import Model.JTable;
import Model.Operation;
import Model.Rent;
import Model.User;

public class ShowAllRents implements Operation{

	@Override
	public void Operation(Database database, JFrame f, User user) {
		
		JFrame frame = new JFrame("Rents");
		frame.setSize(1200,600);
		frame.setLocationRelativeTo(f);
		frame.getContentPane().setBackground(new Color(250,206,27));
		frame.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Rents",35);
		title.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		frame.add(title, BorderLayout.NORTH);
		
		String[] header = new String[] {
			"ID","Name","Email","Phone Number","Car ID","Car","Date Time","Hours","Total","Status"	
		};
		
		
		ArrayList<Rent> rents = new ArrayList<>();
		ArrayList<Integer> carIDs = new ArrayList<>();
		ArrayList<Integer> userIDs = new ArrayList<>();
		try {
			String select = "SELECT * from `rents`;";
			ResultSet rs = database.getStatement().executeQuery(select);
			
			while(rs.next()) {
				Rent rent = new Rent();
				rent.setID(rs.getInt("ID"));
				userIDs.add(rs.getInt("User"));
				carIDs.add(rs.getInt("Car"));
				rent.setDateTime(rs.getString("DateTime"));
				rent.setHours(rs.getInt("Hours"));
				rent.setTotal(rs.getDouble("Total"));
				rent.setStatus(rs.getInt("Status"));
				rents.add(rent);
			}
			
			for(int j=0;j<rents.size();j++) {
				Rent r = rents.get(j);
				
				String selectUser = "SELECT * from `users` where `ID`='"+userIDs.get(j)+"';";
				ResultSet rs2 = database.getStatement().executeQuery(selectUser);
				rs2.next();
				User u = new Client();
				u.setID(rs2.getInt("ID"));
				u.setFirstName(rs2.getString("FirstName"));
				u.setLastName(rs2.getString("LastName"));
				u.setEmail(rs2.getString("Email"));
				u.setPhoneNumber(rs2.getString("PhoneNumber"));
				u.setPassword(rs2.getString("Password"));
				r.setUser(u);
				
				ResultSet rs3 = database.getStatement().executeQuery("SELECT * from `cars` where `ID`='"+carIDs.get(j)+"';");
				rs3.next();
				Car car = new Car();
				car.setID(rs3.getInt("ID"));
				car.setBrand(rs3.getString("Brand"));
				car.setModel(rs3.getString("Model"));
				car.setColor(rs3.getString("Color"));
				car.setYear(rs3.getInt("Year"));
				car.setPrice(rs3.getDouble("Price"));
				car.setAvailable(rs3.getInt("Available"));
				r.setCar(car);
				
//				System.out.println("ID\t\t: "+r.getID());
//				System.out.println("Name\t\t: "+r.getUser().getFirstName()+" "+r.getUser().getLastName());
//				System.out.println("Email\t\t: "+r.getUser().getEmail());
//				System.out.println("Phone Number\t: "+r.getUser().getPhoneNumber());
//				System.out.println("Car ID\t\t: "+r.getCar().getID());
//				System.out.println("Car\t\t: "+r.getCar().getBrand()+" "+r.getCar().getModel()+" "+r.getCar().getColor());
//				System.out.println("Date Time\t: "+r.getDateTime());
//				System.out.println("Hours\t\t: "+r.getHours());
//				System.out.println("Total\t\t: "+r.getTotal());
//				System.out.println("Status\t\t: "+r.getStatusToString());
//				System.out.println("------------------------------------");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, e.getMessage());
		}
		
		String rentsData[][] = new String[rents.size()][10];
		for(int j=0;j<rents.size();j++) {
			Rent r = rents.get(j);
			rentsData[j][0]=String.valueOf(r.getID());
			rentsData[j][1]= r.getUser().getFirstName()+" "+r.getUser().getLastName();
			rentsData[j][2]= r.getUser().getEmail();
			rentsData[j][3]= r.getUser().getPhoneNumber();
			rentsData[j][4]= String.valueOf(r.getCar().getID());
			rentsData[j][5]= r.getCar().getBrand()+" "+r.getCar().getModel()+" "+r.getCar().getColor();
			rentsData[j][6]= r.getDateTime();
			rentsData[j][7]= String.valueOf(r.getHours());
			rentsData[j][8]= String.valueOf(r.getTotal());
			rentsData[j][9]= r.getStatusToString();
		}
		
		Color color2 = new Color(252,242,202);
		JScrollPane panel = new JScrollPane(new JTable(rentsData,header,Color.black,color2));
		panel.setBackground(null);
		panel.getViewport().setBackground(null);
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		
	}

}
