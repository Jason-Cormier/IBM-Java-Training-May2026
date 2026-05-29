package IBM.Training.com.Day6;

import java.sql.*;
import java.util.Scanner;

public class Main {
	private static String URL = "jdbc:postgresql://localhost:5432/day6";
	private static String USER = "postgres";
	private static String PASS = "cormier";
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS)){
			String sql = "SELECT * FROM student WHERE studentid = ?";
			
			while (true) {
				System.out.println("=== MENU ===");
				System.out.println("[A]dd");
				System.out.println("[V]iew");
				System.out.println("[U]pdate Password");
				System.out.println("[D]elete");
				System.out.println("[Q]uit");
				System.out.println();
				System.out.print("Enter choice: ");
				String choice = sc.nextLine();
				
				switch (choice) {
					case "A":
						addStudent(con);
						break;
					case "V":
						viewStudents(con);
						break;
					case "U":
						updatePassword(con);
						break;
					case "D":
						deleteStudent(con);
						break;
					case "Q":
						System.out.println("Program Quiting...");
						return;
					default:
						System.out.println("Invalid choice");
				}
			}
		} catch (SQLException e) {
			System.out.println("Database Error");
			e.printStackTrace();
		}
	}
	
	static void addStudent(Connection con) throws SQLException {
		String sql = "INSERT INTO student (email, password, firstname, lastname, dateadded, dateupdated) VALUES (?, ?, ?, ?, NOW(), NOW())";
		PreparedStatement ps = con.prepareStatement(sql);
		
		System.out.print("Email: ");
		String email = sc.nextLine();
		
		System.out.print("First Name: ");
		String fname = sc.nextLine();
		
		System.out.print("Last Name: ");
		String lname = sc.nextLine();
		
		System.out.print("Password: ");
		String pass = sc.nextLine();
		
		ps.setString(1, email);
		ps.setString(2, pass);
		ps.setString(3, fname);
		ps.setString(4, lname);
		
		int rows = ps.executeUpdate();
		System.out.println(rows + " student added.");
		System.out.println();
	}
	
	static void viewStudents(Connection con) throws SQLException {
		String sql = "SELECT * FROM student";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		System.out.println();
		System.out.println("=== STUDENT LIST ===");
		
		while (rs.next()) {
			System.out.println("Student ID: " + rs.getInt("studentid"));
			System.out.println("Email: " + rs.getString("email"));
			System.out.println("Password: " + rs.getString("password"));
			System.out.println("First Name: " + rs.getString("firstname"));
			System.out.println("Last Name: " + rs.getString("lastname"));
			System.out.println("Date Added : " + rs.getTimestamp("dateadded"));
			System.out.println("Updated At : " + rs.getTimestamp("dateupdated"));
			System.out.println();
		}
	}
	
	static void updatePassword(Connection con) throws SQLException{
		String sql = "UPDATE student SET password = ?, dateupdated = NOW() WHERE email = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		
		System.out.print("Enter Email: ");
		String email = sc.nextLine();
		System.out.print("Enter New Password: ");
		String newPass = sc.nextLine();
		
		ps.setString(1, newPass);
		ps.setString(2, email);
		
		int rows = ps.executeUpdate();
		
		if (rows > 0) {
			System.out.println("Password updated successfully.");
		} else {
			System.out.println("Email not found.");
		}
		System.out.println();
	}
	
	static void deleteStudent(Connection con) throws SQLException {
		String sql = "DELETE FROM student WHERE email = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		
		System.out.print("Enter Email: ");
	    String email = sc.nextLine();

	    ps.setString(1, email);

	    int rows = ps.executeUpdate();

	    if (rows > 0) {
	        System.out.println("Student deleted successfully.");
	    } else {
	        System.out.println("Email not found.");
	    }
	    System.out.println();
	}
}
