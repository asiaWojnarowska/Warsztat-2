package pl.coderslab.model;

import java.sql.Connection;
import java.sql.Date;
// (System.currentTimeMillis())
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Solution {

	
	private int id;
	private Date created;
	private Date updated;
	private String description;
	private static Integer exerciseId;
	private static Integer usersId;
	
	
	public void saveToDB(Connection conn) throws SQLException {
		if (this.id == 0) {
			String sql = "INSERT INTO solution (created, description, exerecise_id, users_id) VALUES (?, ?, ?, ?)";
			String generatedColumns[] = { "ID" };
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql, generatedColumns);
			Date date = new Date (System.currentTimeMillis());
			preparedStatement.setDate(1, date);
			preparedStatement.setString(2, this.description);
			if (this.exerciseId == null) {
				preparedStatement.setNull(3, java.sql.Types.INTEGER);
			} else {
				preparedStatement.setInt(3, this.exerciseId);
			}
			if (this.usersId == null) {
				preparedStatement.setNull(4, java.sql.Types.INTEGER);
			} else {
				preparedStatement.setInt(4, this.usersId);
			}
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
				created = date;
			}
		} else {
			String sql1 = "UPDATE solution SET updated=?, description=?, exercise_id=?, users_id=? where id = ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql1);
			Date date = new Date (System.currentTimeMillis());
			preparedStatement.setDate(1, updated);
			preparedStatement.setString(2, this.description);
			if (this.exerciseId == null) {
				preparedStatement.setNull(3, java.sql.Types.INTEGER);
			} else {
				preparedStatement.setInt(3, this.exerciseId);
			}
			if (this.usersId == null) {
				preparedStatement.setNull(4, java.sql.Types.INTEGER);
			} else {
				preparedStatement.setInt(4, this.usersId);
			}
			preparedStatement.setInt(5, this.id);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				updated = date;
			}
		}

	}

	public void delete(Connection conn) throws SQLException {
		if (this.id != 0) {
			String sql2 = "DELETE FROM solution WHERE id = ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql2);
			preparedStatement.setInt(1, this.id);
			preparedStatement.executeUpdate();
			this.id = 0;
		}

	}

	static public Solution loadSolutionById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM solution where id=?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Solution loadedSolution = new Solution ();
			loadedSolution.id = resultSet.getInt("id");
			loadedSolution.created = resultSet.getDate("created");
			loadedSolution.updated = resultSet.getDate("updated");
			loadedSolution.description = resultSet.getString("description");
			int exerciseId = resultSet.getInt("exercise_id");
			if (exerciseId == 0) {
				loadedSolution.exerciseId = null;
			} else {
				loadedSolution.exerciseId = exerciseId;
			}
			int usersId = resultSet.getInt("users_id");
			if (usersId == 0) {
				loadedSolution.usersId = null;
			} else {
				loadedSolution.usersId = usersId;
			}
			return loadedSolution;
		}
		return null;
	}

	static public Solution [] loadAllSolutions (Connection conn) throws SQLException {
		ArrayList<Solution> solution = new ArrayList<Solution>();
		String sql = "SELECT * FROM solution";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Solution loadedSolution = new Solution();
			loadedSolution.id = resultSet.getInt("id");
			loadedSolution.created = resultSet.getDate("created");
			loadedSolution.updated = resultSet.getDate("updated");
			loadedSolution.description = resultSet.getString("description");
			int exerciseId = resultSet.getInt("exercise_id");
				if (exerciseId == 0) {
					loadedSolution.exerciseId = null;
				} else {
					loadedSolution.exerciseId = exerciseId;
				}
				int usersId = resultSet.getInt("users_id");
				if (usersId == 0) {
					loadedSolution.usersId = null;
				} else {
					loadedSolution.usersId = usersId;
				}
			solution.add(loadedSolution);
		}
		Solution[] sArray = new Solution[solution.size()];
		sArray = solution.toArray(sArray);
		return sArray;
		
	}
	
	static public Solution loadAllByUserId(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM solution where user_id=?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, usersId);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Solution loadedAllSolutions = new Solution ();
			loadedAllSolutions.id = resultSet.getInt("id");
			loadedAllSolutions.created = resultSet.getDate("created");
			loadedAllSolutions.updated = resultSet.getDate("updated");
			loadedAllSolutions.description = resultSet.getString("description");
			int exerciseId = resultSet.getInt("exercise_id");
			if (exerciseId == 0) {
				loadedAllSolutions.exerciseId = null;
			} else {
				loadedAllSolutions.exerciseId = exerciseId;
			}
			int usersId = resultSet.getInt("users_id");
			if (usersId == 0) {
				loadedAllSolutions.usersId = null;
			} else {
				loadedAllSolutions.usersId = usersId;
			}
			return loadedAllSolutions;
		}
		return null;
	}
	
	
	static public Solution loadAllByExerciseId(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM solution WHERE exercise_id=? ORDER BY created DESC";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, exerciseId);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Solution loadedAllSolutions = new Solution ();
			loadedAllSolutions.id = resultSet.getInt("id");
			loadedAllSolutions.created = resultSet.getDate("created");
			loadedAllSolutions.updated = resultSet.getDate("updated");
			loadedAllSolutions.description = resultSet.getString("description");
			int exerciseId = resultSet.getInt("exercise_id");
			if (exerciseId == 0) {
				loadedAllSolutions.exerciseId = null;
			} else {
				loadedAllSolutions.exerciseId = exerciseId;
			}
			int usersId = resultSet.getInt("users_id");
			if (usersId == 0) {
				loadedAllSolutions.usersId = null;
			} else {
				loadedAllSolutions.usersId = usersId;
			}
			return loadedAllSolutions;
		}
		return null;
	}
	
	
	
	public int getId() {
		return id;
	}
	public Date getCreated() {
		return created;
	}
	public Date getUpdated() {
		return updated;
	}
	public String getDescription() {
		return description;
	}
	public Integer getExerciseId() {
		return exerciseId;
	}
	public Integer getUsersId() {
		return usersId;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public void setExerciseId(Integer exerciseId) {
		this.exerciseId = exerciseId;
	}
	public void setUsersId(Integer usersId) {
		this.usersId = usersId;
	}
	
	
	
}
