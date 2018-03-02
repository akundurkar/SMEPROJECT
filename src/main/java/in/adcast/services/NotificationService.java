package in.adcast.services;

import java.util.Map;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public interface NotificationService {
	
	
	public void offerEmail();

	public void sendTestMail();
	
	@Async
	public void sendMail(String templateFile,String mailTo,Map<String, Object> freeMarkerModel,String subject);

	@Async
	public void birthdayOfferEmail();

}
