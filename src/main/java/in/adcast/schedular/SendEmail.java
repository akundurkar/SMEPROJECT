package in.adcast.schedular;

import java.util.Calendar;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import in.adcast.services.NotificationService;
import in.adcast.services.impl.MailServiceImpl;
import in.adcast.services.impl.NotificationServiceImpl;




@Service
@EnableScheduling
public class SendEmail {
	
@Autowired
	NotificationService notificationService;
	
	
	
	/*
	 * * "0 0 * * * *" = the top of every hour of every day.
	 * 10 * * * * *" = every ten seconds.
	 * "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
	 * "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30 and 10 o'clock every day.
	 * "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
	 * "0 0 0 25 12 ?" = every Christmas Day at midnight
	 * 
	 */
	
	@Scheduled(cron="0 59 17 * * *")
	//@Scheduled(cron="*/10 * * * * *")
	
	public void sendImpressionCountEmail() { 
		notificationService.sendTestMail();
		System.out.println("Sending email at "+ Calendar.getInstance().getTime()); 
	}

	
	@Scheduled(cron="0 34 15 * * *")
	//@Scheduled(cron="*/10 * * * * *")
	public void offerEmail() { 
		notificationService.offerEmail();
		System.out.println("Sending offerEmail at "+ Calendar.getInstance().getTime()); 
	}
	
	@Scheduled(cron="0 34 15 * * *")
	public void birthdayOfferEmail() { 
		notificationService.birthdayOfferEmail();
		System.out.println("Sending offerEmail at "+ Calendar.getInstance().getTime()); 
	}
	
	public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        		SendEmail.class);
        String sendEmail = "org.springframework.context.annotation.internalScheduledAnnotationProcessor";
        System.out.println("Contains " + sendEmail + ": "
                + context.containsBean(sendEmail));
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            context.close();
        }
    }
}
