import java.io.*;
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*;
import java.util.*;
public class MyServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public MyServlet1() {
        super();   
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String firstname = request.getParameter("fname");
		String lastname = request.getParameter("lname");
		String username = request.getParameter("uname");
		String email = request.getParameter("n2");
		String password = request.getParameter("n3");
		System.out.println("Firstname"+firstname+"username : "+username +" "+"password : "+password+"Email:"+email+"lastname"+lastname);
		try
		{
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","harika");
    		PreparedStatement pstmt=con.prepareStatement("insert into registrationf values(?,?,?,?,?)");
    		pstmt.setString(1, firstname);
    		pstmt.setString(2, lastname);
    		pstmt.setString(3, username);
    		pstmt.setString(4, email);
    		pstmt.setString(5, password);
    		int n = pstmt.executeUpdate();
    		if(n>=1)
    		{
    			System.out.println("Data Insertion Successfull");
    			response.sendRedirect("login.html");
    		}
    		else
    		{
    			response.addHeader("REFRESH", "1;URL=login.html");
    		}
		}
		catch(Exception e)
		{		
			System.out.println(e);  
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}
}