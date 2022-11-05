package com.myservlet;


import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.DriverManager;
import java.sql.*;


public class RegisterServlet extends HttpServlet{
	
	
public void doPost(HttpServletRequest req , HttpServletResponse res) throws ServletException , IOException {
	
	res.setContentType("text/html");

	PrintWriter pw = res.getWriter();
	
	
	
	
	String name = req.getParameter("username");

	
	String password = req.getParameter("psswd");

	
	String email = req.getParameter("usermail");

	
	String gender = req.getParameter("gender");

	int l1 = name.length();
	int l2 = password.length();
	int l3 = email.length();
	
	


	if(l1!=0 && l2!=0 && l3!=0) {

		pw.println("<h1><center> Given Data Are Ready to Store in DataBase...........</center></h1>");
		System.out.println("Given Data Are Ready to Store in DataBase.....");

		
		// Inserting Users Data in Database..
		
		try {
			
			// loading JDBC DRiver 
			
			Class.forName("com.mysql.cj.jdbc.Driver");

			
			// Creating Connection
			
			String url = "jdbc:mysql://localhost:3306/ServletAndJdbcSubmitForm";
			String userN = "root";
			String passW = "toor";
			final Connection con = DriverManager.getConnection(url,userN,passW);
			
			if(con.isClosed()) {
				System.out.println("Connection Closed......");
			}else {
				System.out.println("Connection Established...");
			}
			
			
	
			// Inserting data ;
			
			
			String insertQuery = "INSERT INTO users_data(users_name , users_password , users_mail , users_gender) Values(?,?,?,?);";
			
			
			PreparedStatement ps = con.prepareStatement(insertQuery);
			
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, gender);
	
			
			int result = 0;
			
	result =	ps.executeUpdate();
	
			
		

		
			if(result == 0) {
				System.out.println("......Error Encountered While Inserting Data in Database.... .....");
			}else {
				System.out.println("......Data Inserted in Database.... .....");
			}
			
			
		
			
			con.close();
		
			
			
			}catch(Exception e){
				e.printStackTrace();
			}
		
		
		
		
		
		
		
	}else {
		pw.println("<h1><center> PLease Fill Every Field..........</center></h1>");
		System.out.println(" Empty Request Recieved.....");
	}
	
	

	

	
}	
	
}
