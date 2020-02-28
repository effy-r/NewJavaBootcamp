package lv.accenture.bootcamp.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {
	
	@Autowired
	@Qualifier("sms")
	//Qualifier er en identifikator som forteller Spring at dette her er component du trenger
	private NotificationChannel smsService;
	
	@Autowired
	@Qualifier("email")
	private NotificationChannel emailService;
	
	public void spamPerson(User user) {
		
		smsService.notifyUser(user);
		emailService.notifyUser(user);
		
		
		
	}

}
