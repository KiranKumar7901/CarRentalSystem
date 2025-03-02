package Model;

import java.sql.*;

public class Database {
	private String user = "root";
	private String password = "";
	private String url = "jdbc:mysql://localhost:3307/carrentalsystem";
	private Statement statement;
	
	public Database() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,user,password);
			statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
		}catch(SQLException | ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	public Statement getStatement() {
		return statement;
	}
}
