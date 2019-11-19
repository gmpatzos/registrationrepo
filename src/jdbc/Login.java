package jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String name = request.getParameter("user");
			String password = request.getParameter("password");
			String dbName = null;
			String dbPassword = null;
			String sql = "select * from registration where name=? and password=?";
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/portal","root","");
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,name);
			ps.setString(2,password);
			ResultSet rs = ps.executeQuery();
			PrintWriter out = response.getWriter();
			while (rs.next()) {
				dbName = rs.getString("name");
				dbPassword = rs.getString("password");
			}
			if (name.equals(dbName)&&password.equals(dbPassword)) {
				out.println("You have successfully logged in");
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.include(request,response);
			}
			
			
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
