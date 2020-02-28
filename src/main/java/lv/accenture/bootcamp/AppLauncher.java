package lv.accenture.bootcamp;

import lv.accenture.bootcamp.io.conveyor.Pipeline;
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

    public static void main(String[] args) {
        SpringApplication.run(AppLauncher.class);
    }
    
    User user = new User("Jhon Doe", "47268598", "john.doe@gmail.com");
    
    //notificationService.spamPerson(user);

    @Override
    public void run(String... args) throws Exception {
        greetingService.greet("world");
        goodbyeService.sayGoodbye("world");
        pipeline.performConversions();
        
        User user = new User("Jhon Doe", "47268598", "john.doe@gmail.com");
        
        
        notificationService.spamPerson(user);
    
    
//        User user = new User("Jhon Doe", "47268598", "john.doe@gmail.com");
//        
//        notificationService.spamPerson(user);
       
        
       
        
    }
}
