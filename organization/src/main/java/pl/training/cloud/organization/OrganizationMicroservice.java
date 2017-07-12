package pl.training.cloud.organization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import pl.training.cloud.organization.stream.Message;

@EnableBinding(Sink.class)
@SpringBootApplication
public class OrganizationMicroservice {

    public static void main(String[] args) {
        SpringApplication.run(OrganizationMicroservice.class);
    }

    @StreamListener(Sink.INPUT)
    public void logMessage(Message message) {
        System.out.println("#### New message: " + message);
    }

}
