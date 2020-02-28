package lv.accenture.bootcamp.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("sms")
public class SmsNotification implements NotificationChannel {

	@Value("${notification.sms.operator}")
	private String operator;

	@Override
	public void notifyUser(User user) {

		String info = user.getFullName() + " is notified by sms on number " + user.getNumber() + " by operator "
				+ operator;

		System.out.println(info);
	}
}
