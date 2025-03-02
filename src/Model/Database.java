package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private String user = "user";
	private String password = "1234";
	private String url = "jdbc:mysql://localhost/carrentalsystem";
	private Statement statement;
	
	public Database() {
		try {
			Connection con = DriverManager.getConnection(url,user,password);
			statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public Statement getStatement() {
		return statement;
	}
}
