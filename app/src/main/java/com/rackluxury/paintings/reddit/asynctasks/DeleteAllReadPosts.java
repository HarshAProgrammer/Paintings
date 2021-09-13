package com.rackluxury.paintings.reddit.asynctasks;

import android.os.Handler;

import java.util.concurrent.Executor;

import com.rackluxury.paintings.reddit.RedditDataRoomDatabase;

public class DeleteAllReadPosts {

    public static void deleteAllReadPosts(Executor executor, Handler handler,
                                          RedditDataRoomDatabase redditDataRoomDatabase,
                                          DeleteAllReadPostsAsyncTaskListener deleteAllReadPostsAsyncTaskListener) {
        executor.execute(() -> {
            redditDataRoomDatabase.readPostDao().deleteAllReadPosts();
            handler.post(deleteAllReadPostsAsyncTaskListener::success);
        });
    }

    public interface DeleteAllReadPostsAsyncTaskListener {
        void success();
    }
}
