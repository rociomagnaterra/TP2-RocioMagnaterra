package punto5;

import punto1.Concursante;
import punto1.Concurso;
import punto5.NotificadorFake;
import punto5.RegistroInscriptosFake;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ConcursoTestFake {

    @Test
    public void testInscripcionConFakes() {
        var rocio = new Concursante("Rocio Magnaterra");
        var notificador = new NotificadorFake();
        var registro = new RegistroInscriptosFake();

        LocalDate fechaInicio = LocalDate.of(2025, 3, 1);
        LocalDate fechaFin = LocalDate.of(2025, 3, 31);
        LocalDate fechaInscripcion = LocalDate.of(2025, 3, 2);

        Concurso concurso = new Concurso("Concurso de Rocio", fechaInicio, fechaFin, registro, notificador);

        concurso.inscribirParticipante(rocio, fechaInscripcion);
        assertTrue(registro.fueRegistrado);
        assertTrue(notificador.fueNotificado);
        assertEquals("Rocio Magnaterra", notificador.concursanteNotificado.getNombre());
        assertEquals("Concurso de Rocio", notificador.nombreConcursoNotificado);
        assertEquals(fechaInscripcion, notificador.fechaNotificacion);
    }
}
