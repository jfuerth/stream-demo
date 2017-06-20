package com.example.streams.demoappb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageChannel;

import java.time.Instant;

@SpringBootApplication
@EnableBinding(DemoAppBApplication.DemoEventSink.class)
public class DemoAppBApplication {

	public interface DemoEventSink {
		String CHANNEL_NAME = "demo-events";

		@Input(CHANNEL_NAME)
		MessageChannel demoEventSink();
	}

	@StreamListener(DemoEventSink.CHANNEL_NAME)
	public void onDemoEvent(DemoEvent event) {
		System.out.printf("%s: got event %s\n", Instant.now(), event);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoAppBApplication.class, args);
	}
}
