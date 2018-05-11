package pl.coderslab.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import pl.coderslab.model.User;

public class testApp {

	public static void main(String[] args) {

		try (Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/warsztaty?useSSL=false&characterEncoding=utf8", "root", "coderslab")) {

			User testUser = new User();
			testUser.setUsername("asia");
			testUser.setEmail("wojnarowska.asia@gmail.com");
			testUser.setPassoword("alamakota");
			testUser.saveToDB(conn);
			System.out.println("ID zapisanego użytkownika: " + testUser.getId());
			testUser.setPassoword("alamakota");
			testUser.saveToDB(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
