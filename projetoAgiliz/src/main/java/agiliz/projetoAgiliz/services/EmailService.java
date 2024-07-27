package agiliz.projetoAgiliz.services;

import freemarker.template.Configuration;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final Configuration fmConfig;

    @Value("${spring.mail.username}")
    private String remetente;

    public void enviarEmail(String destinatario, String titulo, Map<String,Object> props) {
        var mimeMessage = mailSender.createMimeMessage();


        try {
            var mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(remetente);
            mimeMessageHelper.setTo(destinatario);
            mimeMessageHelper.setSubject(titulo);

            mimeMessageHelper.setText(getConteudoTemplate(props), true);
            mailSender.send(mimeMessageHelper.getMimeMessage());
        }catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String getConteudoTemplate(Map<String,Object> modelo) {
        var conteudo = new StringBuilder();

        try {
            var templateFormatado = FreeMarkerTemplateUtils
                    .processTemplateIntoString(
                            fmConfig.getTemplate("email-recuperacao-senha.flth"), modelo
                    );

            conteudo.append(templateFormatado);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return conteudo.toString();
    }
}
