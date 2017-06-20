package com.example.streams.demoappa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;

@SpringBootApplication
@EnableBinding(DemoAppAApplication.DemoEventSource.class)
public class DemoAppAApplication {

    public interface DemoEventSource {
        String CHANNEL_NAME = "demo-events";

        @Output(CHANNEL_NAME)
        MessageChannel demoEventSource();
    }

    @Autowired
    @Qualifier(DemoEventSource.CHANNEL_NAME)
    private MessageChannel demoEventSource;

    private int count;

    @Scheduled(fixedDelay = 1000)
    void sendPeriodically() {
        DemoEvent event = new DemoEvent("value " + (count++));
        System.out.printf("%s: sending event %s\n", Instant.now(), event);
        demoEventSource.send(MessageBuilder.withPayload(event).build());
    }

    public static void main(String[] args) {
		SpringApplication.run(DemoAppAApplication.class, args);
	}

}
