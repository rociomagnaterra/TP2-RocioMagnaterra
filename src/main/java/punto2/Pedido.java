package punto2;

import java.time.LocalDate;
import java.util.List;

public class Pedido {
    private List<Double> bebidas;
    private List<Double> comidas;
    private double propinaPorcentaje;
    private RegistroPedidos registro;

    public Pedido(List<Double> bebidas, List<Double> comidas, double propinaPorcentaje, RegistroPedidos registro) {
        this.bebidas = bebidas;
        this.comidas = comidas;
        this.propinaPorcentaje = propinaPorcentaje;
        this.registro= registro;
    }

    public double calcularTotal() {
        double totalBebidas = bebidas.stream().mapToDouble(Double::doubleValue).sum();
        double totalComidas = comidas.stream().mapToDouble(Double::doubleValue).sum();
        double total = totalBebidas + totalComidas;
        double propina = total * (propinaPorcentaje / 100);
        double totalConPropina = total + propina;

        registro.registrarConsumo(LocalDate.now(), totalConPropina);

        return totalConPropina;
    }
}
