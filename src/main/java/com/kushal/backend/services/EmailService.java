package com.kushal.backend.services;

import com.kushal.backend.constants.Constant;
import com.kushal.backend.entities.ContactForm;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration config;

    public boolean sendEmail(ContactForm form, Map<String, Object> model) {

        try {
            MimeMessage userMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper
                    (
                            userMessage,
                            MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                            StandardCharsets.UTF_8.name()
                    );
            Template template = config.getTemplate("mail-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            helper.setFrom(Constant.EMAIL_ADDRESS);
            helper.setTo(form.getEmail());
            helper.setSubject("Thank you for reaching out to me!");
            helper.setText(html, true);

            mailSender.send(userMessage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
