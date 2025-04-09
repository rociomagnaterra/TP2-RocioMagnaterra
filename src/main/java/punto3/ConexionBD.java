package punto3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {

    private static final String URL_BD = "jdbc:sqlite:registro_concursos.db";
    private static Connection conexion;

    public static Connection obtenerConexion() {
        if (conexion == null) {
            try {
                conexion = DriverManager.getConnection(URL_BD);
            } catch (SQLException e) {
                System.err.println("No se pudo establecer la conexión: " + e.getMessage());
            }
        }
        return conexion;
    }

    public static void inicializarTablas() {
        String tablaConcursos = """
                CREATE TABLE IF NOT EXISTS concursos (
                    id TEXT PRIMARY KEY,
                    nombre TEXT NOT NULL,
                    inicio DATE NOT NULL,
                    fin DATE NOT NULL
                );
                """;

        String tablaParticipantes = """
                CREATE TABLE IF NOT EXISTS participantes (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    apellido TEXT NOT NULL,
                    dni TEXT NOT NULL UNIQUE,
                    puntos INTEGER NOT NULL DEFAULT 0,
                    concurso_id TEXT NOT NULL,
                    FOREIGN KEY (concurso_id) REFERENCES concursos(id)
                );
                """;

        try (Statement stmt = obtenerConexion().createStatement()) {
            stmt.execute(tablaConcursos);
            stmt.execute(tablaParticipantes);
        } catch (SQLException e) {
            System.err.println("Error al crear las tablas: " + e.getMessage());
        }
    }

    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}