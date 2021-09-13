package com.rackluxury.ferrari.reddit.multireddit;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.rackluxury.ferrari.reddit.RedditDataRoomDatabase;

public class MultiRedditRepository {
    private final LiveData<List<MultiReddit>> mAllMultiReddits;
    private final LiveData<List<MultiReddit>> mAllFavoriteMultiReddits;

    MultiRedditRepository(RedditDataRoomDatabase redditDataRoomDatabase, String accountName) {
        MultiRedditDao multiRedditDao = redditDataRoomDatabase.multiRedditDao();
        mAllMultiReddits = multiRedditDao.getAllMultiReddits(accountName);
        mAllFavoriteMultiReddits = multiRedditDao.getAllFavoriteMultiReddits(accountName);
    }

    LiveData<List<MultiReddit>> getAllMultiReddits() {
        return mAllMultiReddits;
    }

    LiveData<List<MultiReddit>> getAllFavoriteMultiReddits() {
        return mAllFavoriteMultiReddits;
    }
}
