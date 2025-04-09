package punto2;

import org.junit.jupiter.api.Test;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    private double calcularTotalConPropina(List<Double> bebidas, List<Double> comidas, double porcentajePropina) {
        double totalComidas = comidas.stream().mapToDouble(Double::doubleValue).sum();
        double totalBebidas = bebidas.stream().mapToDouble(Double::doubleValue).sum();
        double total = totalBebidas + totalComidas;
        double propina = total * (porcentajePropina / 100);
        return total + propina;
    }

    @Test
    void test01() throws Exception {
        List<Double> bebidas = Arrays.asList(100.0, 50.0, 150.0);
        List<Double> comidas = Arrays.asList(200.0, 1200.0);
        double porcentajePropina = 3.0;

        String rutaArchivo = "C:\\Users\\mariR\\OneDrive\\Escritorio\\pedidos.txt";
        RegistroPedidos registro = new ArchivoDePedidos(rutaArchivo);

        Files.deleteIfExists(Paths.get(rutaArchivo));

        Pedido pedido = new Pedido(bebidas, comidas, porcentajePropina, registro);
        double totalCalculado = pedido.calcularTotal();

        double totalEsperado = calcularTotalConPropina(bebidas, comidas, porcentajePropina);

        assertEquals(totalEsperado, totalCalculado, 0.01);

        List<String> lineas = Files.readAllLines(Paths.get(rutaArchivo), StandardCharsets.UTF_8);
        assertEquals(1, lineas.size());

        String fechaActual = LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String[] partes = lineas.get(0).split(" \\|\\| ");
        assertEquals(fechaActual, partes[0]);
        assertEquals(String.valueOf(totalCalculado), partes[1]);
    }
}