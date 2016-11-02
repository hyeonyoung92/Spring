package spring.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import spring.user.domain.User;

public class UserDao {
 
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/users","root","12345678");
		return c;
		
	}
	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection con = getConnection();

		PreparedStatement ps = con.prepareStatement("insert into users(id, name, password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());

		ps.executeUpdate();

		ps.close();
		con.close();
	}

	public User get(String id) throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		
		PreparedStatement ps = con.prepareStatement("select * from users where id = ?");
		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();
		rs.next();

		User user = new User();
		user.setId(rs.getString("id"));
		user.setId(rs.getString("name"));
		user.setId(rs.getString("password"));

		rs.close();
		ps.close();
		con.close();

		return user;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao dao = new UserDao();
		User user = new User();
		user.setId("weelde");
		user.setName("«Ù≥Á¿Ã");
		user.setPassword("racheal");
		
		dao.add(user);
		
		System.out.println(user.getId()+"µÓ∑œOK");
		
		
	}

}
