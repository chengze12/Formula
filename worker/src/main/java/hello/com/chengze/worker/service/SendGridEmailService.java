package hello.com.chengze.worker.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SendGridEmailService {
    private SendGrid sendGrid;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public SendGridEmailService(@Autowired SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    public void sendEmail(String emailJson) throws IOException {

        logger.debug(emailJson);
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> emailDetails = mapper.readValue(emailJson, new TypeReference<Map<String,Object>>(){});

        Mail mail = new Mail();
        String subject = emailDetails.get("subject").toString();

//        if (subject.equals("Willing to purchase car")) {
//            mail.setTemplateId(friendInvitationTemplateId);
//        } else if (subject.equals("Event Invitation")) {
//            mail.setTemplateId(eventInvitationTemplateId);
//        } else if (subject.equals("Registration Confirmation")) {
//            mail.setTemplateId(registrationTemplateId);
//        } else {
//            logger.debug("Unsupported Email Request Type!");
//            return;
//        }

        mail.setFrom(new Email(emailDetails.get("from").toString()));

        Map<String, String> requestInfo = (Map<String, String>) emailDetails.get("request");
        List<String> toEmails = (List<String>) emailDetails.get("to_emails");
        List<String> toUsernames = (List<String>) emailDetails.get("to_usernames");

        Personalization personalization = new Personalization();

        for (String to : toEmails) {
            personalization.addTo(new Email(to));
        }

        personalization.setSubject(subject);
        personalization.addDynamicTemplateData("username", toUsernames.get(0));
        personalization.addDynamicTemplateData("request", requestInfo);

        mail.addPersonalization(personalization);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
        } catch (IOException ex) {
            throw ex;
        }
    }
}
