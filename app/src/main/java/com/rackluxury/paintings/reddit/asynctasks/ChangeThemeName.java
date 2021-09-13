package com.rackluxury.paintings.reddit.asynctasks;

import java.util.concurrent.Executor;

import com.rackluxury.paintings.reddit.RedditDataRoomDatabase;

public class ChangeThemeName {
    public static void changeThemeName(Executor executor, RedditDataRoomDatabase redditDataRoomDatabase,
                                       String oldName, String newName) {
        executor.execute(() -> {
            redditDataRoomDatabase.customThemeDao().updateName(oldName, newName);
        });
    }
}
