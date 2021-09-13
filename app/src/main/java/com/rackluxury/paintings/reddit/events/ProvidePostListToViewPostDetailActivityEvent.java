package com.rackluxury.paintings.reddit.events;

import java.util.ArrayList;

import com.rackluxury.paintings.reddit.post.Post;

public class ProvidePostListToViewPostDetailActivityEvent {
    public long postFragmentId;
    public ArrayList<Post> posts;

    public ProvidePostListToViewPostDetailActivityEvent(long postFragmentId, ArrayList<Post> posts) {
        this.postFragmentId = postFragmentId;
        this.posts = posts;
    }
}
