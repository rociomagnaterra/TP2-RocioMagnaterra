package punto5;

import punto1.Concursante;
import punto4.Notificador;
import java.time.LocalDate;

public class NotificadorFake implements Notificador {

    public boolean fueNotificado = false;
    public Concursante concursanteNotificado;
    public String nombreConcursoNotificado;
    public LocalDate fechaNotificacion;

    @Override
    public void enviar(Concursante concursante, String nombreConcurso, LocalDate fechaInscripcion) {
        this.fueNotificado = true;
        this.concursanteNotificado = concursante;
        this.nombreConcursoNotificado = nombreConcurso;
        this.fechaNotificacion = fechaInscripcion;
    }
}