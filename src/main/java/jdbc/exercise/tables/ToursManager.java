package jdbc.exercise.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.exercise.ConnectionManager;

public class ToursManager {

	private static Connection conn = ConnectionManager.getInstance().getConnection();

	public static void displayAllRows() throws SQLException {
		String sql = "SELECT tourId, packageId, tourName, blurb, description, price, difficulty," +
				"graphic, length, region, keywords FROM tours";
		try (
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)
		) {

			System.out.println("Tour Table:");
			while (rs.next()) {
				StringBuffer bf = new StringBuffer();
				bf.append(rs.getInt("tourId") + ": ");
				bf.append(rs.getInt("packageId") + ", ");
				bf.append(rs.getString("tourName") + ", ");
				bf.append(rs.getString("blurb") + ", ");
				bf.append(rs.getString("description") + ", ");
				bf.append(rs.getDouble("price") + ", ");
				bf.append(rs.getString("difficulty") + ", ");
				bf.append(rs.getString("graphic") + ", ");

				bf.append(rs.getString("region") + ", ");




				System.out.println(bf.toString());
			}
		}
	}


}
