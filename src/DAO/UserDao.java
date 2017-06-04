/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import model.User;
import connexion.Connexion;

/**
 *
 * @author Louis
 */
public class UserDao extends Dao<User> {

	private Connection connexionDB;

	public UserDao(Connection conn) {
		super(conn);
	}

	@Override
	public HashMap<String, User> selectAll() throws Exception {
		connexionDB = Connexion.getInstance();
		ResultSet rs;
		PreparedStatement ps = connexionDB
				.prepareStatement("SELECT * FROM u$eR");
		rs = ps.executeQuery();
		HashMap<String, User> hs = new HashMap<>();
		while (rs.next()) {
			hs.put(rs.getString(1),
					new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("birthday"),
							rs.getString("email"), rs.getString("login"), rs.getString("password"), rs.getBoolean("isAdmin")));
		}
		rs.close();
		connexionDB.close();
		return hs;
	}

	@Override
	public User select(Object id) throws SQLException, IOException {
		connexionDB = Connexion.getInstance();
		ResultSet rs;
		User u;
		try (PreparedStatement ps = connexionDB.prepareStatement("SELECT * FROM u$eR WHERE login=?")) {
			ps.setString(1, (String) id);
			rs = ps.executeQuery();
			rs.next();
			u = new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("birthday"),
					rs.getString("email"), rs.getString("login"), rs.getString("pa$$woRd"), rs.getBoolean("isAdmin"));
					
		}
		rs.close();
		connexionDB.close();
		return u;
	}

	@Override
	public boolean insert(User u) throws SQLException, IOException {
		boolean res = true;
		connexionDB = Connexion.getInstance();
		try (PreparedStatement ps = connexionDB
				.prepareStatement("INSERT INTO u$eR(firstName, lastName, birthday, email,login,	pa$$woRd, isAdmin) values(?,?,?,?,?,?,?)")) {
			ps.setString(1, u.getFirstName());
			ps.setString(2, u.getLastName());
			ps.setString(3, u.getAge());
			ps.setString(4, u.getEmail());
			ps.setString(5, u.getLogin());
			ps.setString(6, u.getPassword());
			ps.setBoolean(7, u.isAdmin());
			try {
				ps.executeUpdate();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				ps.cancel();
				res = false;
			}
			connexionDB.commit();
			ps.close();
			connexionDB.close();
		}
		return res;
	}

	@Override
	public boolean delete(User u) throws SQLException, IOException {
		boolean res = true;
		connexionDB = Connexion.getInstance();
		try (PreparedStatement ps = connexionDB
				.prepareStatement("DELETE FROM u$eR where login=?")) {
			ps.setString(1, u.getLogin());
			try {
				ps.executeUpdate();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				ps.cancel();
				res = false;
			}
			connexionDB.commit();
			ps.close();
			connexionDB.close();
		}
		return res;
	}

	@Override
	public boolean update(User u) throws SQLException, IOException {
		boolean res = true;
		connexionDB = Connexion.getInstance();
		try (PreparedStatement ps = connexionDB
				.prepareStatement("UPDATE u$eR SET firstName=?, lastName=?, birthday=?, email=?, pa$$word=?, isAdmin=? where login=?")) {
			ps.setString(1, u.getFirstName());
			ps.setString(2, u.getLastName());
			ps.setString(3, u.getAge());
			ps.setString(4, u.getEmail());
			ps.setString(5, u.getPassword());
			ps.setBoolean(6, u.isAdmin());
			ps.setString(7, u.getLogin());
			try {
				ps.executeUpdate();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				ps.cancel();
				res = false;
			}
			ps.close();
			connexionDB.close();
		}
		return res;
	}

}