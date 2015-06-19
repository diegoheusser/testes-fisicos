//Email.java
package util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * @author 5102011212
 */
public class Email {

    private String name;
    private String email;
    private String message;
    private String subject;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Email(String name, String email, String subject, String message) {
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    public void sendEmail() throws EmailException {
        SimpleEmail e = new SimpleEmail();
        //Utilize o hostname do seu provedor de email
        e.setHostName("smtp.gmail.com");
        //Quando a porta utilizada não é a padrão (gmail = 465)
        e.setSmtpPort(465);
        //Adicione os destinatários
        e.addTo(this.name, this.email);
        //Configure o seu email do qual enviará
        e.setFrom("safisbudesc@gmail.com", "Sistema de Avalição Física para Badminton");
        //Adicione um assunto
        e.setSubject(this.subject);
        //Adicione a mensagem do email
        e.setMsg(this.message);
        //Para autenticar no servidor é necessário chamar os dois métodos abaixo
        e.setSSLOnConnect(true);
        e.setAuthentication("safisbudesc@gmail.com", "sistemadeavaliacaofisicaparabadminton");
        e.send();
    }
}
