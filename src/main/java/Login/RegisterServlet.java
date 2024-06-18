package Login;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MyPackage.MySqlConnection;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			MySqlConnection ms = new MySqlConnection();

			Connection con = ms.getCon();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Signup where email='" + req.getParameter("email") + "'");
			if (rs.next()) {
				resp.sendRedirect("TheEvent/Signup.jsp?message=Email Id is already in Use");
			} else {
				PreparedStatement ps = con
						.prepareStatement("insert into Signup(username,email,password) values(?,?,?) ");
				ps.setString(1, req.getParameter("username"));
				ps.setString(2, req.getParameter("email"));
				ps.setString(3, req.getParameter("password"));

				int count = ps.executeUpdate();
				if (count > 0) {

					resp.sendRedirect("TheEvent/Signup.jsp");
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("Signup.jsp?message=" + e.getMessage());
		}
	}
}