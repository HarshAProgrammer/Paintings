package com.rackluxury.paintings.reddit.events;

import com.rackluxury.paintings.reddit.Flair;

public class FlairSelectedEvent {
    public long viewPostDetailFragmentId;
    public Flair flair;

    public FlairSelectedEvent(long viewPostDetailFragmentId, Flair flair) {
        this.viewPostDetailFragmentId = viewPostDetailFragmentId;
        this.flair = flair;
    }
}
