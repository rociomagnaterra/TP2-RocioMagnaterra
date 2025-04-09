package punto2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class ArchivoDePedidos implements RegistroPedidos {
    private String path;

    public ArchivoDePedidos(String path) {
        this.path = path;
    }

    @Override
    public void registrarConsumo(LocalDate fecha, double monto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String linea = formatter.format(fecha) + " || " + monto;

        final Path filePath = Paths.get(this.path);
        try {
            Files.write(
                    filePath,
                    Arrays.asList(linea),
                    StandardCharsets.UTF_8,
                    Files.exists(filePath) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

