package jcolonia.daw2025.world;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de acceso a datos (DAO) para la base de datos World.
 * Proporciona los métodos necesarios para realizar consultas sobre la tabla Country
 * utilizando una conexión JDBC con SQLite.
 * 
 * @author manueld.garpra
 * @version 1.0
 */
public class AccesoBDWorld {
    /** Ruta de conexión a la base de datos SQLite. */
    private static final String URL = "jdbc:sqlite:world2025.db";

    /**
     * Realiza una consulta a la base de datos para obtener nombres de países 
     * que coincidan con un patrón determinado.
     * 
     * @param patron El texto de búsqueda. Puede contener comodines de SQL como '%' o '_'.
     * @return Una lista de cadenas {@link List} con los nombres de los países encontrados, 
     *         ordenados alfabéticamente.
     * @throws SQLException Si ocurre un error durante la conexión o la ejecución de la consulta SQL.
     */
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

