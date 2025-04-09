package punto5;

import punto1.RegistroInscriptos;

import java.time.LocalDate;

public class RegistroInscriptosFake implements RegistroInscriptos {

    public boolean fueRegistrado = false;
    public LocalDate fechaRegistrada;
    public int idConcursanteRegistrado;
    public int puntosRegistrados;

    @Override
    public void registrarInscripto(LocalDate fechaInscripcion, int idConcursante, int puntos) {
        this.fueRegistrado = true;
        this.fechaRegistrada = fechaInscripcion;
        this.idConcursanteRegistrado = idConcursante;
        this.puntosRegistrados = puntos;
    }
}