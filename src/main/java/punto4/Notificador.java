package punto4;

import punto1.Concursante;
import java.time.LocalDate;

public interface Notificador {
    void enviar(Concursante concursante, String nombreConcurso, LocalDate fechaInscripcion);
}
