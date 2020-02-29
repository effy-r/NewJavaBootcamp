package lv.accenture.bootcamp;

import lv.accenture.bootcamp.io.conveyor.DateAdjuster;
import lv.accenture.bootcamp.io.conveyor.Pipeline;
import lv.accenture.bootcamp.network.DateInsert;
import lv.accenture.bootcamp.network.SunAPIService;
import lv.accenture.bootcamp.spring.GoodbyeService;
import lv.accenture.bootcamp.spring.GreetingService;
import lv.accenture.bootcamp.spring.NotificationService;
import lv.accenture.bootcamp.spring.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppLauncher implements CommandLineRunner {

	@Autowired
	private GreetingService greetingService;

	@Autowired
	private GoodbyeService goodbyeService;

	@Autowired
	private Pipeline pipeline;

	@Autowired
	private NotificationService notificationService;

	// 29.02.2020
	@Autowired
	private SunAPIService sunAPIService;
	
	@Autowired
	private DateAdjuster dateAdjuster;
	
	@Autowired
	private DateInsert dateInsert;

	public static void main(String[] args) {
		SpringApplication.run(AppLauncher.class);
	}

	User user = new User("Jhon Doe", "47268598", "john.doe@gmail.com");

	// notificationService.spamPerson(user);

	@Override
	public void run(String... args) throws Exception {
		//greetingService.greet("world");
		//goodbyeService.sayGoodbye("world");
		//pipeline.performConversions();

		//User user = new User("Jhon Doe", "47268598", "john.doe@gmail.com");

		//notificationService.spamPerson(user);
//29.02.2020
		sunAPIService.getSunrise("22.02.2020");
		
		dateAdjuster.adjustLectureTime();
		//sunAPIService.getSunrise(dateAdjuster.adjustLectureTime());
		
		
		dateInsert.DateInsert(sunAPIService.getSunrise(dateAdjuster.adjustLectureTime()));
	

	}
}
