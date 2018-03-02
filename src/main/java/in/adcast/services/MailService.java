package in.adcast.services;

import in.adcast.common.utils.Mail;

public interface MailService {
    public void sendEmail(Mail mail,String templateName);
}
