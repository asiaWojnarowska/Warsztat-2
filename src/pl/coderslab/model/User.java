package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {

	private int id;
	private String username;
	private String email;
	private String password;
	private static Integer userGroupId;

	public void saveToDB(Connection conn) throws SQLException {
		if (this.id == 0) {
			String sql = "INSERT INTO users(username, email, password, user_group_id) VALUES (?, ?, ?, ?)";
			String generatedColumns[] = { "ID" };
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql, generatedColumns);
			preparedStatement.setString(1, this.username);
			preparedStatement.setString(2, this.email);
			preparedStatement.setString(3, this.password);
			if (this.userGroupId == null) {
				preparedStatement.setNull(4, java.sql.Types.INTEGER);
			} else {
				preparedStatement.setInt(4, this.userGroupId);
			}
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		} else {
			String sql1 = "UPDATE users SET username=?, email=?, password=?, user_group_id=? where id = ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql1);
			preparedStatement.setString(1, this.username);
			preparedStatement.setString(2, this.email);
			preparedStatement.setString(3, this.password);
			if (this.userGroupId == null) {
				preparedStatement.setNull(4, java.sql.Types.INTEGER);
			} else {
				preparedStatement.setInt(4, this.userGroupId);
			}
			preparedStatement.setInt(5, this.id);
			preparedStatement.executeUpdate();
		}

	}

	public void delete(Connection conn) throws SQLException {
		if (this.id != 0) {
			String sql2 = "DELETE FROM users WHERE id = ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql2);
			preparedStatement.setInt(1, this.id);
			preparedStatement.executeUpdate();
			this.id = 0;
		}

	}

	static public User loadUserById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM users where id=?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			User loadedUser = new User();
			loadedUser.id = resultSet.getInt("id");
			loadedUser.username = resultSet.getString("username");
			loadedUser.password = resultSet.getString("password");
			loadedUser.email = resultSet.getString("email");
			int userGroupId = resultSet.getInt("user_group_id");
			if (userGroupId == 0) {
				loadedUser.userGroupId = null;
			} else {
				loadedUser.userGroupId = userGroupId;
			}
			return loadedUser;
		}
		return null;
	}

	static public User[] loadAllUsers(Connection conn) throws SQLException {
		ArrayList<User> users = new ArrayList<User>();
		String sql = "SELECT * FROM users";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			User loadedUser = new User();
			loadedUser.id = resultSet.getInt("id");
			loadedUser.username = resultSet.getString("username");
			loadedUser.password = resultSet.getString("password");
			loadedUser.email = resultSet.getString("email");
			int userGroupId = resultSet.getInt("user_group_id");
			if (userGroupId == 0) {
				loadedUser.userGroupId = null;
			} else {
				loadedUser.userGroupId = userGroupId;
			}
			users.add(loadedUser);
		}
		User[] uArray = new User[users.size()];
		uArray = users.toArray(uArray);
		return uArray;
	}
	
	static public User loadAllByGroupId(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM users where user_group_id=?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, userGroupId);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			User loadedAllUsers = new User();
			loadedAllUsers.id = resultSet.getInt("id");
			loadedAllUsers.username = resultSet.getString("username");
			loadedAllUsers.password = resultSet.getString("password");
			loadedAllUsers.email = resultSet.getString("email");
			int userGroupId = resultSet.getInt("user_group_id");
			if (userGroupId == 0) {
				loadedAllUsers.userGroupId = null;
			} else {
				loadedAllUsers.userGroupId = userGroupId;
			}
			return loadedAllUsers;
		}
		return null;
	}
	
	

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPassoword() {
		return password;
	}
	
	public Integer getUserGroupId() {
		return userGroupId;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassoword(String passoword) {
		this.password = passoword;
	}

	

	public void setUserGroupId(Integer userGroupId) {
		this.userGroupId = userGroupId;
	}

}
