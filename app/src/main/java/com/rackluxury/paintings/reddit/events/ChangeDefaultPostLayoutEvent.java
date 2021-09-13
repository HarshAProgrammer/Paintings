package com.rackluxury.paintings.reddit.events;

public class ChangeDefaultPostLayoutEvent {
    public int defaultPostLayout;

    public ChangeDefaultPostLayoutEvent(int defaultPostLayout) {
        this.defaultPostLayout = defaultPostLayout;
    }
}
