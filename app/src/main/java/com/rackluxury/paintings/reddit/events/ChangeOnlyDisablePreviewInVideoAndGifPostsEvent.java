package com.rackluxury.paintings.reddit.events;

public class ChangeOnlyDisablePreviewInVideoAndGifPostsEvent {
    public boolean onlyDisablePreviewInVideoAndGifPosts;

    public ChangeOnlyDisablePreviewInVideoAndGifPostsEvent(boolean onlyDisablePreviewInVideoAndGifPosts) {
        this.onlyDisablePreviewInVideoAndGifPosts = onlyDisablePreviewInVideoAndGifPosts;
    }
}
