package com.rackluxury.paintings.reddit.events;

import com.rackluxury.paintings.reddit.post.Post;

public class PostUpdateEventToPostDetailFragment {
    public final Post post;

    public PostUpdateEventToPostDetailFragment(Post post) {
        this.post = post;
    }
}
