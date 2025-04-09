package punto1;

import punto4.Notificador;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Concurso {
    private final Notificador notificador;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDate fechaInscripcion;
    private List<Concursante> concursantes;
    public static final String FECHA_NO_VALIDA = "La fecha de inicio no puede ser posterior a la fecha de fin!!";
    public static final String INSCRIPCION_FUERA_DE_FECHA = "La inscripción está fuera del rango de fechas.";
    private static int contadorId = 1234;
    private final int id;

    private RegistroInscriptos registro;

    public Concurso(String nombre, LocalDate fechaInicio, LocalDate fechaFin, RegistroInscriptos registro, Notificador notificador ) {
        if (fechaInicio.isAfter(fechaFin)) {
            throw new RuntimeException(FECHA_NO_VALIDA);
        }
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.concursantes = new ArrayList<Concursante>();
        this.id= contadorId++;
        this.registro = registro;
        this.notificador = notificador;
    }

    //metodo sobrecargado para ConcursoTestFake
    public Concurso(String nombre, LocalDate fechaInicio, LocalDate fechaFin, RegistroInscriptos registro) {
        this(nombre, fechaInicio, fechaFin, registro, null);
    }

    public void inscribirParticipante(Concursante c, LocalDate fechaInscripcion) {
        if (fechaInscripcion.isBefore(fechaInicio) || fechaInscripcion.isAfter(fechaFin)) {
            throw new RuntimeException(INSCRIPCION_FUERA_DE_FECHA);
        }
        this.concursantes.add(c);
        if (fechaInscripcion.isEqual(fechaInicio)) {
            c.sumarPuntos(10); // Si se inscribe en el primer día, recibe 10 puntos
        }
        registro.registrarInscripto(fechaInscripcion, c.getId(), this.id);
    }

    public boolean estaInscripto(Concursante c) {
        return this.concursantes.contains(c);
    }

}
