package br.com.lucca16bit.forms.service;

import br.com.lucca16bit.forms.dto.forms.CreateFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${contact.email}")
    private String contactEmail;

    public String sendEmail(CreateFormDTO data){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(contactEmail);
            mailMessage.setSubject(data.name() + " quer entrar em contato com você!");

            String body = String.format(
                """
                Olá Lucas!
            
                Você recebeu um novo contato através do seu portfólio.
            
                Detalhes do contato:
                Nome: %s
                Email: %s
            
                Mensagem:
                %s
                
                Por favor, responda diretamente para o email do contato se desejar continuar a conversa.

                Atenciosamente,
                Lucas - Equipe Back-End
                """, data.name(), data.email(), data.description());

            mailMessage.setText(body);
            mailMessage.setReplyTo(data.email());

            javaMailSender.send(mailMessage);

            return "Email enviado com sucesso!";
        } catch (Exception e) {
            return "Erro ao enviar email: " + e.getMessage();
        }
    }

}
