package lv.accenture.bootcamp.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("email")
public class EmailService implements NotificationChannel {

	@Value("${notification.email.from}")
	private String from;

	@Override
	public void notifyUser(User user) {

		String info = user.getFullName() + " is notified by email on number " + user.getEmail()
				+ " email sent from an email " + from;

		System.out.println(info);
	}

}
