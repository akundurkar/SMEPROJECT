package in.adcast.services.impl;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;

import in.adcast.common.utils.Mail;
import in.adcast.services.MailService;

@Service("mailService")
public class MailServiceImpl implements MailService {
 
    @Autowired
    private JavaMailSender mailSender = new JavaMailSenderImpl();
 
    @Autowired
    private Configuration fmConfiguration;
 
    public void sendEmail(Mail mail,String templateName) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
 
        try {
 
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
 
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(mail.getMailFrom());
            mimeMessageHelper.setTo(mail.getMailTo());
            mail.setMailContent(geContentFromTemplate(mail.getModel(),templateName));
            mimeMessageHelper.setText(mail.getMailContent(), true);
 
            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
 
    public String geContentFromTemplate(Map < String, Object > model,String templateName) {
        StringBuffer content = new StringBuffer();
 
        try {
        	
            content.append(FreeMarkerTemplateUtils
                .processTemplateIntoString(fmConfiguration.getTemplate(templateName), model));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
 

}
