package jdbc.example;

import java.sql.*;

public class Main {

	public static void main(String[] args) {

		try (Connection conn = DBUtil.getConnection(DBType.HSQLDB);
			 Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			 ResultSet rs = stmt.executeQuery("SELECT * FROM admins");
		) {
			while(rs.next()) {
				String stateFullName = rs.getString("username") + ": " + rs.getString("password");
				System.out.println(stateFullName);
			}
		} catch (SQLException e) {
			DBUtil.processException(e);
		}
	}

	private static void conn2() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try  {
			conn = DBUtil.getConnection(DBType.MYSQL);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT * FROM admins");
            while(rs.next()) {
                String stateFullName = rs.getString("username") + ": " + rs.getString("password");
                System.out.println(stateFullName);
            }
		} catch (SQLException e) {
			DBUtil.processException(e);
		} finally {
			try {
				rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

/*
 * Poruszanie sie kursorem:
 *
 * rs.beforeFirst();
 * rs.first();
 * rs.last();
 * rs.afterLast();
 * rs.absolute(int row);
 *
 * Sprawdzenie pozycji:
 *
 * rs.isAfterLast();
 * rs.isFirst();
 * rs.isLast();
 * rs.isAfterLast();
 *
 * Limit otrzymanych elementow:
 * stmt.setMaxRows(int rows); - ale lepiej dodac klauzule LIMIT do zapytania SQL(stmt wyciaga wszystko i w javie robi limit)
 *
 *
 */