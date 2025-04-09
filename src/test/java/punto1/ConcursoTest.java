package punto1;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

//
class ConcursoTest {

    @org.junit.jupiter.api.Test
    void test01() throws IllegalAccessException {
        var rocio = new Concursante("Rocio Magnaterra");
        var sofia = new Concursante("Sofia Magnaterra");
        LocalDate fechaInicio = LocalDate.of(2025, 3, 1);
        LocalDate fechaFin = LocalDate.of(2025, 3, 31);
        LocalDate fechaInscripcion = LocalDate.of(2025, 3, 2);
        LocalDate fechaInscripcion2 = LocalDate.of(2025, 3, 9);

        //ubicacion del archivo
        var registro = new ArchivoDeInscriptos("C:\\Users\\mariR\\OneDrive\\Escritorio\\inscriptos.txt");
        Concurso elConcurso = new Concurso("EL CONCURSO", fechaInicio, fechaFin, registro);

        elConcurso.inscribirParticipante(rocio, fechaInscripcion);
        elConcurso.inscribirParticipante(sofia, fechaInscripcion);
    }
}