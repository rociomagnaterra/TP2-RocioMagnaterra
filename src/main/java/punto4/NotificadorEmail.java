package punto4;

import io.mailtrap.client.MailtrapClient;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.emails.Address;
import io.mailtrap.model.request.emails.MailtrapMail;
import punto1.Concursante;
import io.github.cdimascio.dotenv.Dotenv;

import java.time.LocalDate;
import java.util.List;

public class NotificadorEmail implements Notificador {

    private static final Dotenv dotenv = Dotenv.load();

    private static final String TOKEN = dotenv.get("MAILTRAP_TOKEN");
    private static final Long INBOX_ID = Long.parseLong(dotenv.get("MAILTRAP_INBOX_ID"));
    private static final String DESTINATARIO = dotenv.get("MI_CORREO");

    @Override
    public void enviar(Concursante concursante, String nombreConcurso, LocalDate fechaInscripcion) {
        MailtrapConfig config = new MailtrapConfig.Builder()
                .sandbox(true)
                .inboxId(INBOX_ID)
                .token(TOKEN)
                .build();

        MailtrapClient client = MailtrapClientFactory.createMailtrapClient(config);

        MailtrapMail mail = MailtrapMail.builder()
                .from(new Address("no-reply@concursos.com", "Sistema de Concursos"))
                .to(List.of(new Address(DESTINATARIO)))
                .subject("Inscripción confirmada")
                .text(String.format("Hola %s!\nEstás inscripto en el concurso \"%s\" con fecha: %s.",
                        concursante.getNombre(), nombreConcurso, fechaInscripcion))
                .category("Inscripcion")
                .build();

        try {
            client.send(mail);
            System.out.println("📧 Email enviado con éxito!");
        } catch (Exception e) {
            System.out.println("Error al enviar el mail: " + e);
        }
    }
}