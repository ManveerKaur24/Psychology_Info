package User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import MyPackage.MySqlConnection;

public class UserService implements UserRepo{

	@Override
	public void register(User user) throws Exception {
		// TODO Auto-generated method stub
	    MySqlConnection p= new MySqlConnection();
        Connection con=p.getCon();
        PreparedStatement ps=con.prepareStatement("insert into register(name,password)values(?,?)");
     
      ps.setString(1,user.getUsername());
      ps.setString(2,user.getPassword());
      
      ps.executeUpdate();
 
		
	}

	@Override
	public boolean checkUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean login(User user) throws Exception {
		// TODO Auto-generated method stub
		 MySqlConnection p=new MySqlConnection();
         Connection con=p.getCon();
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery("select * from Signup where Username='"+user.getUsername()+"' and Password='"+user.getPassword()+"'");
         
         if(rs.next())
         {
             return true;
             
         }
         else
         {
             return false;
      
	   }
	}
}
