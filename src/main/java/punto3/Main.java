package punto3;

import punto1.Concursante;
import punto1.Concurso;
import punto1.RegistroInscriptos;
import punto4.Notificador;
import punto4.NotificadorEmail;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        RegistroInscriptos registro = new BDRegistros("jdbc:sqlite:concursos.db");
        Notificador notificador = new NotificadorEmail();
        Concurso concurso = new Concurso("EL CONCURSO", LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 30), registro, notificador);
        Concursante rocio = new Concursante("Rocio Magnaterra");
        concurso.inscribirParticipante(rocio, LocalDate.of(2025, 4, 1));
    }
}