package com.rackluxury.paintings.reddit.events;

public class SwitchAccountEvent {
    public String excludeActivityClassName;

    public SwitchAccountEvent() {
    }

    public SwitchAccountEvent(String excludeActivityClassName) {
        this.excludeActivityClassName = excludeActivityClassName;
    }
}
