package com.example.streams.demoappa;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageChannel;

import java.time.Instant;

@EnableBinding(LocalDemoEventSink.DemoEventSink.class)
public class LocalDemoEventSink {

    public interface DemoEventSink {
        String CHANNEL_NAME = "demo-events";

        @Input(CHANNEL_NAME)
        MessageChannel demoEventSink();
    }

    @StreamListener(DemoEventSink.CHANNEL_NAME)
    public void onDemoEvent(DemoEvent event) {
        System.out.printf("%s: got event %s\n", Instant.now(), event);
    }

}
