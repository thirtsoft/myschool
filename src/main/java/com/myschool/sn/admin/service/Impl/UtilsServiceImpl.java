package com.myschool.sn.admin.service.Impl;

import com.myschool.sn.admin.entity.Utilisateur;
import com.myschool.sn.admin.exception.UtilsException;
import com.myschool.sn.admin.service.UtilsServiceCustom;
import com.myschool.sn.utils.ConstantDeployment;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class UtilsServiceImpl implements UtilsServiceCustom {
    private final JavaMailSender sender;

    String first = ConstantDeployment.host_front + "/auth/login/first-time/";

    private final Configuration freemarkerConfig;

    public UtilsServiceImpl(JavaMailSender sender, Configuration freemarkerConfig) {
        this.sender = sender;
        this.freemarkerConfig = freemarkerConfig;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @Async
    public void sendMailCreateUser(Utilisateur utilisateur) throws Exception  {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        Map<String, Object> model = new HashMap();
        model.put("url", first + utilisateur.getActivation());
    //    model.put("nomUser", utilisateur.getUtilisateurDetails().getNom());
    //    model.put("prenomUser", utilisateur.getUtilisateurDetails().getPrenom());
        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/");
        Template t = freemarkerConfig.getTemplate("emailFormatage/creation-user.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
        helper.setFrom(new InternetAddress("thirdiallo@gmail.com", "wokite"));
    //    helper.setTo(utilisateur.getEmail());
        helper.setText(text, true); // set to html
        helper.setSubject("Invitation sur l'application MY-SCHOOL");
        try {
            sender.send(message);
        } catch (Exception e) {
			throw new UtilsException("Erreur à l'envoi du mail");
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @Async
    public void sendMailForgotPass(Utilisateur utilisateur, String motDePasse) throws Exception {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        Map<String, Object> model = new HashMap();
        model.put("url", ConstantDeployment.host_front);
        /*
        model.put("nomUser", utilisateur.getUtilisateurDetails().getNom());
        model.put("prenomUser", utilisateur.getUtilisateurDetails().getPrenom());
        model.put("email", utilisateur.getEmail());
        */
        model.put("motdepasse", motDePasse);
        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/");
        Template t = freemarkerConfig.getTemplate("emailFormatage/motDePassOublie.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
        helper.setFrom(new InternetAddress("thirdiallo@gmail.com", "wokite"));
   //     helper.setTo(utilisateur.getEmail());
        helper.setText(text, true); // set to html
        helper.setSubject("Réinitialisation de votre mot de passe");
        try {
            sender.send(message);
        } catch (Exception e) {
            throw new Exception("Erreur à l'envoi du mail");
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @Async
    public void sendMailUpdatePass(Utilisateur user) throws Exception {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        Map<String, Object> model = new HashMap();
        model.put("url", ConstantDeployment.host_front);
        /*
        model.put("nomUser", user.getUtilisateurDetails().getNom());
        model.put("prenomUser", user.getUtilisateurDetails().getPrenom());
        */
        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/");
        Template t = freemarkerConfig.getTemplate("emailFormatage/mdp_modifier.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
        helper.setFrom(new InternetAddress("thirdiallo@gmail.com", "wokite"));
    //    helper.setTo(user.getEmail());
        helper.setText(text, true); // set to html
        helper.setSubject("Changement de votre mot de passe");
        try {
            sender.send(message);
        } catch (Exception e) {
            throw new Exception("Erreur à l'envoi du mail");
        }

    }

}
