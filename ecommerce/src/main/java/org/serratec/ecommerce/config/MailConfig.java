package org.serratec.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void enviarEmail(String para, String assunto, String texto) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("");
		simpleMailMessage.setTo(para);
		simpleMailMessage.setSubject(assunto);
		simpleMailMessage.setText("Dados do pedido: " + texto + "\n\n Serratec Residência de Software - 2021");
		javaMailSender.send(simpleMailMessage);
	}
}
