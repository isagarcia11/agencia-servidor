package co.edu.uniquindio.agencia.modelo;

import java.util.Properties;

public class ConfiguracionCorreo {

    public static Properties obtenerPropiedadesCorreo() {
        Properties props = new Properties();

        // Configuración del servidor SMTP (Ejemplo: Gmail)
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Usuario y contraseña del correo electrónico desde el que enviarás los mensajes
        props.put("mail.user", "hulbert5789@gmail.com");
        props.put("mail.password", "qcab kmyl avzg seho");

        return props;
    }
}

