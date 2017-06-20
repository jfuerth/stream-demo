package com.example.streams.demoappa;

public class DemoEvent {

    private String property;

    public DemoEvent() {

    }

    public DemoEvent(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "DemoEvent{" +
                "property='" + property + '\'' +
                '}';
    }
}
