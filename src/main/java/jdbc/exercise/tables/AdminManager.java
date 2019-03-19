package jdbc.exercise.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.exercise.ConnectionManager;
import jdbc.exercise.beans.Admin;

public class AdminManager {

    private static Connection conn = ConnectionManager.getInstance().getConnection();

    public static void displayAllRows() throws SQLException {
        String sql = "SELECT id, userName, password FROM admins";
        try (
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {

            System.out.println("Admin Table:");
            while (rs.next()) {
                StringBuffer bf = new StringBuffer();
                bf.append(rs.getInt("id") + ": ");
                bf.append(rs.getString("userName") + ", ");
                bf.append(rs.getString("password"));
                System.out.println(bf.toString());
            }
        }
    }

    public static Admin getRow(int id) throws SQLException {
        String sql = "SELECT * FROM admins WHERE id = ?";
        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Admin bean = new Admin();
                bean.setId(id);
                bean.setUserName(rs.getString("userName"));
                bean.setPassword(rs.getString("password"));
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

    public static boolean insert(Admin bean) throws Exception {
        String sql = "INSERT into admins (userName, password) VALUES (?, ?)";
        ResultSet keys = null;
        try (
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {

            stmt.setString(1, bean.getUserName());
            stmt.setString(2, bean.getPassword());
            int affected = stmt.executeUpdate();

            if (affected == 1) {
                keys = stmt.getGeneratedKeys();
                keys.next();
                int newKey = keys.getInt(1);
                bean.setId(newKey);
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

    public static boolean update(Admin bean) throws Exception {

        String sql =
                "UPDATE admins SET " +
                        "userName = ?, password = ? " +
                        "WHERE id = ?";
        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {

            stmt.setString(1, bean.getUserName());
            stmt.setString(2, bean.getPassword());
            stmt.setInt(3, bean.getId());

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