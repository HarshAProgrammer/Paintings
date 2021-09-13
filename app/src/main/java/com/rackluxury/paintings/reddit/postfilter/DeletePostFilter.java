package com.rackluxury.paintings.reddit.postfilter;

import java.util.concurrent.Executor;

import com.rackluxury.paintings.reddit.RedditDataRoomDatabase;

public class DeletePostFilter {
    public static void deletePostFilter(RedditDataRoomDatabase redditDataRoomDatabase, Executor executor, PostFilter postFilter) {
        executor.execute(() -> redditDataRoomDatabase.postFilterDao().deletePostFilter(postFilter));
    }
}
