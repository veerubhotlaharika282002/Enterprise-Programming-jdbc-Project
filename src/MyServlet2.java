import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class MyServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MyServlet2() 
    {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("uname");
		String password = request.getParameter("n3");
		try
		{
		    Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","harika");
			PreparedStatement  pstmt= con.prepareStatement("select * from registrationf");
			ResultSet rs = pstmt.executeQuery();
				while(rs.next())
				{
					if(rs.getString(3).equals(username) && rs.getString(5).equals(password)) 
					{
						out.println("Hey"+username);
					    response.sendRedirect("afterlogin.html");
						break;
					}
					else
					{
						System.out.println("Login not successfull");
						response.addHeader("REFRESH", "1;URL=afterlogin.html");
						break;
					}
				}
			con.close();
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