package punto1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class ArchivoDeInscriptos implements RegistroInscriptos {
    private String path;

    public ArchivoDeInscriptos (String filePath){
        this.path = filePath;
    }

    public void registrarInscripto(LocalDate fechaInscripcion, int idParticipante, int idConcurso) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String linea = fechaInscripcion.format(formatter) + ", " + idParticipante + ", " + idConcurso;
        final Path path = Paths.get(this.path);
        try {
            Files.write(
                    path,
                    Arrays.asList(linea),
                    StandardCharsets.UTF_8,
                    Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}