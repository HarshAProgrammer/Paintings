package com.rackluxury.paintings.reddit.events;

import com.rackluxury.paintings.reddit.post.Post;

public class SubmitTextOrLinkPostEvent {
    public boolean postSuccess;
    public Post post;
    public String errorMessage;

    public SubmitTextOrLinkPostEvent(boolean postSuccess, Post post, String errorMessage) {
        this.postSuccess = postSuccess;
        this.post = post;
        this.errorMessage = errorMessage;
    }
}
