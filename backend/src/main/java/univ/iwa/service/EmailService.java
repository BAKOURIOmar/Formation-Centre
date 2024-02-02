package univ.iwa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("omarbekouri5@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);
    }
    
    public void sendEvaluationEmail(String to, String subject, String formLink) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("najatelmrabet2001@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText("Cher participant,\n\nNous vous remercions d'avoir participé à notre formation. Pour nous aider à améliorer nos services, veuillez remplir notre formulaire d'évaluation en ligne : " + formLink + "\n\nCordialement,\nL'équipe de formation");

        emailSender.send(message);
    }
}
