package punto2;

import java.time.LocalDate;

public interface RegistroPedidos {
    void registrarConsumo(LocalDate fecha, double monto);
}

