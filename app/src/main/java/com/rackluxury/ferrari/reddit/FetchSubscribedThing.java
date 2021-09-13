package com.rackluxury.ferrari.reddit;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import com.rackluxury.ferrari.reddit.apis.RedditAPI;
import com.rackluxury.ferrari.reddit.subreddit.SubredditData;
import com.rackluxury.ferrari.reddit.subscribedsubreddit.SubscribedSubredditData;
import com.rackluxury.ferrari.reddit.subscribeduser.SubscribedUserData;
import com.rackluxury.ferrari.reddit.utils.APIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FetchSubscribedThing {
    public static void fetchSubscribedThing(final Retrofit retrofit, String accessToken, String accountName,
                                            final String lastItem, final ArrayList<SubscribedSubredditData> subscribedSubredditData,
                                            final ArrayList<SubscribedUserData> subscribedUserData,
                                            final ArrayList<SubredditData> subredditData,
                                            final FetchSubscribedThingListener fetchSubscribedThingListener) {
        RedditAPI api = retrofit.create(RedditAPI.class);

        Call<String> subredditDataCall = api.getSubscribedThing(lastItem, APIUtils.getOAuthHeader(accessToken));
        subredditDataCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()) {
                    com.rackluxury.ferrari.reddit.ParseSubscribedThing.parseSubscribedSubreddits(response.body(), accountName,
                            subscribedSubredditData, subscribedUserData, subredditData,
                            new com.rackluxury.ferrari.reddit.ParseSubscribedThing.ParseSubscribedSubredditsListener() {

                                @Override
                                public void onParseSubscribedSubredditsSuccess(ArrayList<SubscribedSubredditData> subscribedSubredditData,
                                                                               ArrayList<SubscribedUserData> subscribedUserData,
                                                                               ArrayList<SubredditData> subredditData,
                                                                               String lastItem) {
                                    if (lastItem.equals("null")) {
                                        fetchSubscribedThingListener.onFetchSubscribedThingSuccess(
                                                subscribedSubredditData, subscribedUserData, subredditData);
                                    } else {
                                        fetchSubscribedThing(retrofit, accessToken, accountName, lastItem,
                                                subscribedSubredditData, subscribedUserData, subredditData,
                                                fetchSubscribedThingListener);
                                    }
                                }

                                @Override
                                public void onParseSubscribedSubredditsFail() {
                                    fetchSubscribedThingListener.onFetchSubscribedThingFail();
                                }
                            });
                } else {
                    fetchSubscribedThingListener.onFetchSubscribedThingFail();
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                fetchSubscribedThingListener.onFetchSubscribedThingFail();
            }
        });
    }

    public interface FetchSubscribedThingListener {
        void onFetchSubscribedThingSuccess(ArrayList<SubscribedSubredditData> subscribedSubredditData,
                                           ArrayList<SubscribedUserData> subscribedUserData,
                                           ArrayList<SubredditData> subredditData);

        void onFetchSubscribedThingFail();
    }
}
