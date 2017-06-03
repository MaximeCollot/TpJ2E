package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.CookType;
import model.Recette;
import connexion.Connexion;

public class RecetteDao extends Dao<Recette> {

	private Connection connexionDB;

	public RecetteDao(Connection conn) {
		super(conn);
	}

	@Override
	public HashMap<String, Recette> selectAll() throws Exception {
		connexionDB = Connexion.getInstance();
		ResultSet rs;
		PreparedStatement ps = connexionDB
				.prepareStatement("SELECT * FROM meal");
		rs = ps.executeQuery();
		HashMap<String, Recette> hs = new HashMap<>();
		while (rs.next()) {
			hs.put(rs.getString(1),
					new Recette(rs.getInt("id_meal"), rs.getString("name"), rs.getString("recipe"),rs.getInt("preparation_time_min"),rs.getInt("level"), rs.getInt("number_person"),rs.getString("type")));
 		}
		rs.close();
		connexionDB.close();
		return hs;
	}

	@Override
	public Recette select(Object id) throws SQLException, IOException {
		connexionDB = Connexion.getInstance();
		ResultSet rs;
		Recette r;
		try (PreparedStatement ps = connexionDB
				.prepareStatement("SELECT * FROM meal WHERE id_meal=?")) {
			ps.setInt(1, (int) id);
			rs = ps.executeQuery();
			rs.next();
			r = new Recette(rs.getInt("id_meal"), rs.getString("name"), rs.getString("recipe"),rs.getInt("preparation_time_min"),rs.getInt("level"), rs.getInt("number_person"),rs.getString("type"));

		}
		rs.close();
		connexionDB.close();
		return r;
	}

	@Override
	public boolean insert(Recette r) throws SQLException, IOException {
		boolean res = true;
		connexionDB = Connexion.getInstance();
		try (PreparedStatement ps = connexionDB
				.prepareStatement("INSERT INTO meal (name, recipe, number_person, type, preparation_time_min, level)values(?,?,?,?,?,?)")) {
			ps.setString(1, r.getName());
			ps.setString(2, r.getRecipe());
			ps.setInt(3, r.getNbPeople());
			ps.setString(4, r.getCooktype());
			ps.setInt(5, r.getPreparationTime());
			ps.setInt(6, r.getLevel());
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

	@Override
	public boolean delete(Recette u) throws SQLException, IOException {
		boolean res = true;
		connexionDB = Connexion.getInstance();
		try (PreparedStatement ps = connexionDB
				.prepareStatement("DELETE FROM meal where id_meal=?")) {
			ps.setInt(1, u.getId());
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

	@Override
	public boolean update(Recette r) throws SQLException, IOException {
		boolean res = true;
		connexionDB = Connexion.getInstance();
		try (PreparedStatement ps = connexionDB
				.prepareStatement("UPDATE meal SET name=?, recipe=?, number_person=?, type=?, preparation_time_min=?, level=? where id_meal=?")) {
			ps.setString(1, r.getName());
			ps.setString(2, r.getRecipe());
			ps.setInt(3, r.getNbPeople());
			ps.setString(4, r.getCooktype());
			ps.setInt(5, r.getPreparationTime());
			ps.setInt(6, r.getLevel());
			ps.setInt(7, r.getId());
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
	
	
	public List<Recette> search(Recette r) throws Exception {
		connexionDB = Connexion.getInstance();
		ResultSet rs;
		String requete;
		List <Recette> hs = new ArrayList<Recette>();
		
		requete = "SELECT * FROM meal WHERE ";
		
		if(!r.getName().equals("INIT")){
			requete= requete + "name = \""+r.getName().toString()+"\" AND ";
		}
		if(!r.getRecipe().equals("INIT")){
			requete= requete + "recipe = \""+r.getRecipe().toString()+"\" AND ";
		}
		if(r.getNbPeople() != -1){
			requete= requete + "number_person = " + r.getNbPeople() +" AND ";
		}
		
		if(r.getPreparationTime() != -1){
			requete= requete + "preparation_time_min = " + r.getPreparationTime() +" AND ";
		}
		if(r.getLevel() != -1){
			requete= requete + "level = " + r.getLevel() +" AND ";
		}
		if(r.getId() != -1){
			requete= requete + "id_meal = " + r.getId() +" AND ";
		}
		if(!r.getCooktype().equals(" ")){
			requete= requete + "type = \""+r.getCooktype().toString()+"\" AND ";
		}
		
		//fonction un peu crade permettant la recherche, le "1=1;" permet de finir la requete après un "AND"
		requete =requete+ " 1=1 ;";
		
		System.out.println(requete);
		PreparedStatement ps = connexionDB.prepareStatement(requete);
		
			rs = ps.executeQuery();
			while (rs.next()) {
				hs.add(
						new Recette(rs.getInt("id_meal"), rs.getString("name"), rs.getString("recipe"),rs.getInt("preparation_time_min"),rs.getInt("level"), rs.getInt("number_person"),rs.getString("type")));
	 		}
			rs.close();
			connexionDB.close();
		
		
		return  hs;
	}

}

