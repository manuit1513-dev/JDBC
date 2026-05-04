package jcolonia.daw2025.world;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccesoBDWorld{
	private static final String URL = "jdbc:sqlite:world2025.db";

    public List<String> buscarPaises(String patron) throws SQLException {
        List<String> resultados = new ArrayList<>();
        String sql = "SELECT DISTINCT Name FROM 'Country' WHERE Name LIKE ? ORDER BY Name";


        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, patron);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    resultados.add(rs.getString("Name"));
                }
            }
        }
        return resultados;
    }
}
