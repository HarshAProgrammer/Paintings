package com.rackluxury.paintings.reddit.events;

public class NeedForPostListFromPostFragmentEvent {
    public long postFragmentTimeId;

    public NeedForPostListFromPostFragmentEvent(long postFragmentId) {
        this.postFragmentTimeId = postFragmentId;
    }
}
