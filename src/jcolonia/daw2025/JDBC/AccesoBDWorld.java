package jcolonia.daw2025.JDBC;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccesoBDWorld{
	private static final String URL = "jdbc:sqlite:world.db";
	
	public List<String> buscarPaises (String patron) throws SQLException {
			List<String> lista = new ArrayList<>();
			String sql = "SELECT Name FROM Country WHERE Name LIKE ? ORDER BY Name";
	}
}
