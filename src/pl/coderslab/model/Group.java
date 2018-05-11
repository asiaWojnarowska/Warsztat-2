package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Group {

	private int id;
	private String name;
	
	public void saveToDB(Connection conn) throws SQLException {
		if (this.id == 0) {
			String sql = "INSERT INTO user_group(name) VALUES (?)";
			String generatedColumns[] = { "ID" };
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql, generatedColumns);
			preparedStatement.setString(1, this.name);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		} else {
			String sql1 = "UPDATE user_group SET name=? WHERE id=?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql1);
			preparedStatement.setString(1, this.name);
			preparedStatement.setInt(2, this.id);
			preparedStatement.executeUpdate();
		}

	}

	public void delete(Connection conn) throws SQLException {
		if (this.id != 0) {
			String sql2 = "DELETE FROM user_group WHERE id = ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql2);
			preparedStatement.setInt(1, this.id);
			preparedStatement.executeUpdate();
			this.id = 0;
		}

	}

	static public Group loadGroupById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM user_group where id=?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Group loadedGroup = new Group();
			loadedGroup.id = resultSet.getInt("id");
			loadedGroup.name = resultSet.getString("name");
			return loadedGroup;
		}
		return null;
	}

	static public Group[] loadAllGroup(Connection conn) throws SQLException {
		ArrayList<Group> user_group = new ArrayList<Group>();
		String sql = "SELECT * FROM user_group";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Group loadedGroup = new Group();
			loadedGroup.id = resultSet.getInt("id");
			loadedGroup.name = resultSet.getString("name");
			user_group.add(loadedGroup);
		}
		Group[] gArray = new Group[user_group.size()];
		gArray = user_group.toArray(gArray);
		return gArray;
	}
	
	
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
