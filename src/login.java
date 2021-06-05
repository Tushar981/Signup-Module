
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.Resultset;

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PrintWriter pw=response.getWriter();
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/userdata","root","root");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from user where email='"+email+"' and password='"+password+"'");
			
			while(rs.next()) {
				if(rs.getString("email").equals(email) & rs.getString("password").equals(password)) {
					pw.println("Hi "+email+"Ji and your Password is "+password);
				}
			}
		}
		catch(Exception ee) {
			ee.printStackTrace();
		}
}
}