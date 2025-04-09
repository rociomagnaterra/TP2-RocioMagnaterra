package punto3;

import punto1.RegistroInscriptos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BDRegistros implements RegistroInscriptos {
    private Connection conexion;

    public BDRegistros(String urlBD) {
        try {
            this.conexion = DriverManager.getConnection(urlBD);
        } catch (SQLException e) {
            throw new RuntimeException("Error al intentar conectar con la base de datos", e);
        }
    }
    @Override
    public void registrarInscripto(LocalDate fecha, int idParticipante, int idConcurso) {
        String sql = "INSERT INTO inscriptos (fecha, id_participante, id_concurso) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            stmt.setInt(2, idParticipante);
            stmt.setInt(3, idConcurso);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar inscripción en la base de datos", e);
        }
    }
}