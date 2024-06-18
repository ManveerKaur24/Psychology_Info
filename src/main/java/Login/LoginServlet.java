package Login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MyPackage.MySqlConnection;
import User.User;
import User.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		
		User u=new User();
		u.setUsername(request.getParameter("username"));
		u.setPassword(request.getParameter("password"));
//		LoginService log = new  LoginService();
		UserService serv=new UserService();
		
		try {
		
//			if ( validateUser(username, password)) {
				boolean loginn=serv.login(u);
				if (loginn) {
				session.setAttribute("user",u.getUsername());
				
				response.sendRedirect("TheEvent/home.jsp");
				}
			 else {
				response.sendRedirect("TheEvent/Signup.jsp");

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("TheEvent/Signup.jsp=" + e.getMessage());
		}

	}

	private boolean validateUser(String username, String password) throws Exception {
		try {
			MySqlConnection my_sql = new MySqlConnection();
			Connection con = my_sql.getCon();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM Signup where username = ? AND password = ?");
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}