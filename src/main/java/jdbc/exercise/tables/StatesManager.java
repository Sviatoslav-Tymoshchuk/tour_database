package jdbc.exercise.tables;

import java.sql.*;

import jdbc.exercise.ConnectionManager;
import jdbc.exercise.beans.State;

public class StatesManager {

	private static Connection conn = ConnectionManager.getInstance().getConnection();


	public static void displayAllRows() throws SQLException {
		String sql = "SELECT stateId, stateName FROM states";
		try (
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
		) {

			System.out.println("State Table:");
			while (rs.next()) {
				StringBuffer bf = new StringBuffer();
				bf.append(rs.getInt("stateId") + ": ");
				bf.append(rs.getString("stateName") + ", ");
				System.out.println(bf.toString());
			}
		}
	}


	public static State getRow(int id) throws SQLException {
		String sql = "SELECT * FROM states WHERE id = ?";
		ResultSet rs = null;

		try (PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				State bean = new State();
				bean.setStateId(rs.getString("stateId"));
				bean.setStateName(rs.getString("stateName"));
				return bean;
			} else {
				return null;
			}

		} catch (SQLException e) {
			System.err.println(e);
			return null;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}

	}

	public static boolean insert(State bean) throws Exception {
		String sql = "INSERT into states (stateId, stateName) VALUES (?, ?)";
		ResultSet keys = null;
		try (
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		) {

			stmt.setString(1, bean.getStateId());
			stmt.setString(2, bean.getStateName());
			int affected = stmt.executeUpdate();

			if (affected == 1) {

			} else {
				System.err.println("No rows affected");
				return false;
			}

		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			if (keys != null) keys.close();
		}
		return true;
	}

	public static boolean update(State bean) throws Exception {

		String sql =
				"UPDATE states SET " +
						"stateId = ?, stateName = ? " +
						"WHERE id = ?";
		try (
				PreparedStatement stmt = conn.prepareStatement(sql);
		) {

			stmt.setString(1, bean.getStateId());
			stmt.setString(2, bean.getStateName());

			int affected = stmt.executeUpdate();
			if (affected == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}

	}

}