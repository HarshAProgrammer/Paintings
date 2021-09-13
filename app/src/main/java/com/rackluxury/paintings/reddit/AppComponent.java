package com.rackluxury.paintings.reddit;

import javax.inject.Singleton;

import dagger.Component;
import com.rackluxury.paintings.reddit.activities.RedditAccountPostsActivity;
import com.rackluxury.paintings.reddit.activities.AccountSavedThingActivity;
import com.rackluxury.paintings.reddit.activities.AnonymousSubscriptionsActivity;
import com.rackluxury.paintings.reddit.activities.RedditCommentActivity;
import com.rackluxury.paintings.reddit.activities.CreateMultiRedditActivity;
import com.rackluxury.paintings.reddit.activities.CustomThemeListingActivity;
import com.rackluxury.paintings.reddit.activities.RedditCustomThemePreviewActivity;
import com.rackluxury.paintings.reddit.activities.CustomizePostFilterActivity;
import com.rackluxury.paintings.reddit.activities.RedditCustomizeThemeActivity;
import com.rackluxury.paintings.reddit.activities.RedditEditCommentActivity;
import com.rackluxury.paintings.reddit.activities.RedditEditMultiRedditActivity;
import com.rackluxury.paintings.reddit.activities.RedditEditPostActivity;
import com.rackluxury.paintings.reddit.activities.FetchRandomSubredditOrPostActivity;
import com.rackluxury.paintings.reddit.activities.RedditFilteredPostsActivity;
import com.rackluxury.paintings.reddit.activities.FullMarkdownActivity;
import com.rackluxury.paintings.reddit.activities.GiveAwardActivity;
import com.rackluxury.paintings.reddit.activities.RedditInboxActivity;
import com.rackluxury.paintings.reddit.activities.RedditLinkResolverActivity;
import com.rackluxury.paintings.reddit.activities.RedditLockScreenActivity;
import com.rackluxury.paintings.reddit.activities.RedditLoginActivity;
import com.rackluxury.paintings.reddit.activities.RedditMainActivity;
import com.rackluxury.paintings.reddit.activities.MultiredditSelectionActivity;
import com.rackluxury.paintings.reddit.activities.RedditPostFilterPreferenceActivity;
import com.rackluxury.paintings.reddit.activities.PostFilterUsageListingActivity;
import com.rackluxury.paintings.reddit.activities.RedditPostGalleryActivity;
import com.rackluxury.paintings.reddit.activities.RedditPostImageActivity;
import com.rackluxury.paintings.reddit.activities.RedditPostLinkActivity;
import com.rackluxury.paintings.reddit.activities.RedditPostTextActivity;
import com.rackluxury.paintings.reddit.activities.RedditPostVideoActivity;
import com.rackluxury.paintings.reddit.activities.RedditRPANActivity;
import com.rackluxury.paintings.reddit.activities.RedditRulesActivity;
import com.rackluxury.paintings.reddit.activities.RedditSearchActivity;
import com.rackluxury.paintings.reddit.activities.RedditSearchUsersResultActivity;
import com.rackluxury.paintings.reddit.activities.RedditSettingsActivity;
import com.rackluxury.paintings.reddit.activities.RedditSubredditMultiselectionActivity;
import com.rackluxury.paintings.reddit.activities.RedditTrendingActivity;
import com.rackluxury.paintings.reddit.activities.RedditViewPostDetailActivity;
import com.rackluxury.paintings.reddit.activities.RedditViewPrivateMessagesActivity;
import com.rackluxury.paintings.reddit.activities.RedditViewRedditGalleryActivity;
import com.rackluxury.paintings.reddit.activities.RedditViewVideoActivity;
import com.rackluxury.paintings.reddit.activities.ReportActivity;
import com.rackluxury.paintings.reddit.activities.SearchResultActivity;
import com.rackluxury.paintings.reddit.activities.RedditSearchSubredditsResultActivity;
import com.rackluxury.paintings.reddit.activities.SelectUserFlairActivity;
import com.rackluxury.paintings.reddit.activities.SelectedSubredditsAndUsersActivity;
import com.rackluxury.paintings.reddit.activities.SendPrivateMessageActivity;
import com.rackluxury.paintings.reddit.activities.RedditSubmitCrosspostActivity;
import com.rackluxury.paintings.reddit.activities.RedditSubredditSelectionActivity;
import com.rackluxury.paintings.reddit.activities.RedditSubscribedThingListingActivity;
import com.rackluxury.paintings.reddit.activities.SuicidePreventionActivity;
import com.rackluxury.paintings.reddit.activities.ViewImageOrGifActivity;
import com.rackluxury.paintings.reddit.activities.RedditViewImgurMediaActivity;
import com.rackluxury.paintings.reddit.activities.RedditViewMultiRedditDetailActivity;
import com.rackluxury.paintings.reddit.activities.RedditViewSubredditDetailActivity;
import com.rackluxury.paintings.reddit.activities.RedditViewUserDetailActivity;
import com.rackluxury.paintings.reddit.activities.RedditWebViewActivity;
import com.rackluxury.paintings.reddit.bottomsheetfragments.FlairBottomSheetFragment;
import com.rackluxury.paintings.reddit.fragments.CommentsListingFragment;
import com.rackluxury.paintings.reddit.fragments.FollowedUsersListingFragment;
import com.rackluxury.paintings.reddit.fragments.InboxFragment;
import com.rackluxury.paintings.reddit.fragments.MultiRedditListingFragment;
import com.rackluxury.paintings.reddit.fragments.PostFragment;
import com.rackluxury.paintings.reddit.fragments.SidebarFragment;
import com.rackluxury.paintings.reddit.fragments.SubredditListingFragment;
import com.rackluxury.paintings.reddit.fragments.SubscribedSubredditsListingFragment;
import com.rackluxury.paintings.reddit.fragments.UserListingFragment;
import com.rackluxury.paintings.reddit.fragments.ViewImgurImageFragment;
import com.rackluxury.paintings.reddit.fragments.ViewImgurVideoFragment;
import com.rackluxury.paintings.reddit.fragments.ViewPostDetailFragment;
import com.rackluxury.paintings.reddit.fragments.ViewRPANBroadcastFragment;
import com.rackluxury.paintings.reddit.fragments.ViewRedditGalleryImageOrGifFragment;
import com.rackluxury.paintings.reddit.fragments.ViewRedditGalleryVideoFragment;
import com.rackluxury.paintings.reddit.services.RedditDownloadMediaService;
import com.rackluxury.paintings.reddit.services.RedditDownloadRedditVideoService;
import com.rackluxury.paintings.reddit.services.MaterialYouService;
import com.rackluxury.paintings.reddit.services.SubmitPostService;
import com.rackluxury.paintings.reddit.settings.AdvancedPreferenceFragment;
import com.rackluxury.paintings.reddit.settings.CrashReportsFragment;
import com.rackluxury.paintings.reddit.settings.CustomizeBottomAppBarFragment;
import com.rackluxury.paintings.reddit.settings.CustomizeMainPageTabsFragment;
import com.rackluxury.paintings.reddit.settings.DownloadLocationPreferenceFragment;
import com.rackluxury.paintings.reddit.settings.GesturesAndButtonsPreferenceFragment;
import com.rackluxury.paintings.reddit.settings.MainPreferenceFragment;
import com.rackluxury.paintings.reddit.settings.MiscellaneousPreferenceFragment;
import com.rackluxury.paintings.reddit.settings.NotificationPreferenceFragment;
import com.rackluxury.paintings.reddit.settings.NsfwAndSpoilerFragment;
import com.rackluxury.paintings.reddit.settings.PostHistoryFragment;
import com.rackluxury.paintings.reddit.settings.SecurityPreferenceFragment;
import com.rackluxury.paintings.reddit.settings.ThemePreferenceFragment;
import com.rackluxury.paintings.reddit.settings.TranslationFragment;
import com.rackluxury.paintings.reddit.settings.VideoPreferenceFragment;

@Singleton
@Component(modules = com.rackluxury.paintings.reddit.AppModule.class)
public interface AppComponent {
    void inject(RedditMainActivity redditMainActivity);

    void inject(RedditLoginActivity redditLoginActivity);

    void inject(PostFragment postFragment);

    void inject(SubredditListingFragment subredditListingFragment);

    void inject(UserListingFragment userListingFragment);

    void inject(RedditViewPostDetailActivity redditViewPostDetailActivity);

    void inject(RedditViewSubredditDetailActivity redditViewSubredditDetailActivity);

    void inject(RedditViewUserDetailActivity redditViewUserDetailActivity);

    void inject(RedditCommentActivity redditCommentActivity);

    void inject(RedditSubscribedThingListingActivity redditSubscribedThingListingActivity);

    void inject(RedditPostTextActivity redditPostTextActivity);

    void inject(SubscribedSubredditsListingFragment subscribedSubredditsListingFragment);

    void inject(RedditPostLinkActivity redditPostLinkActivity);

    void inject(RedditPostImageActivity redditPostImageActivity);

    void inject(RedditPostVideoActivity redditPostVideoActivity);

    void inject(FlairBottomSheetFragment flairBottomSheetFragment);

    void inject(RedditRulesActivity redditRulesActivity);

    void inject(CommentsListingFragment commentsListingFragment);

    void inject(SubmitPostService submitPostService);

    void inject(RedditFilteredPostsActivity redditFilteredPostsActivity);

    void inject(SearchResultActivity searchResultActivity);

    void inject(RedditSearchSubredditsResultActivity redditSearchSubredditsResultActivity);

    void inject(FollowedUsersListingFragment followedUsersListingFragment);

    void inject(RedditSubredditSelectionActivity redditSubredditSelectionActivity);

    void inject(RedditEditPostActivity redditEditPostActivity);

    void inject(RedditEditCommentActivity redditEditCommentActivity);

    void inject(RedditAccountPostsActivity redditAccountPostsActivity);

    void inject(com.rackluxury.paintings.reddit.PullNotificationWorker pullNotificationWorker);

    void inject(RedditInboxActivity redditInboxActivity);

    void inject(NotificationPreferenceFragment notificationPreferenceFragment);

    void inject(RedditLinkResolverActivity redditLinkResolverActivity);

    void inject(RedditSearchActivity redditSearchActivity);

    void inject(RedditSettingsActivity redditSettingsActivity);

    void inject(MainPreferenceFragment mainPreferenceFragment);

    void inject(AccountSavedThingActivity accountSavedThingActivity);

    void inject(ViewImageOrGifActivity viewGIFActivity);

    void inject(RedditViewMultiRedditDetailActivity redditViewMultiRedditDetailActivity);

    void inject(RedditViewVideoActivity redditViewVideoActivity);

    void inject(GesturesAndButtonsPreferenceFragment gesturesAndButtonsPreferenceFragment);

    void inject(CreateMultiRedditActivity createMultiRedditActivity);

    void inject(RedditSubredditMultiselectionActivity redditSubredditMultiselectionActivity);

    void inject(ThemePreferenceFragment themePreferenceFragment);

    void inject(RedditCustomizeThemeActivity redditCustomizeThemeActivity);

    void inject(CustomThemeListingActivity customThemeListingActivity);

    void inject(SidebarFragment sidebarFragment);

    void inject(AdvancedPreferenceFragment advancedPreferenceFragment);

    void inject(RedditCustomThemePreviewActivity redditCustomThemePreviewActivity);

    void inject(RedditEditMultiRedditActivity redditEditMultiRedditActivity);

    void inject(SelectedSubredditsAndUsersActivity selectedSubredditsAndUsersActivity);

    void inject(ReportActivity reportActivity);

    void inject(RedditViewImgurMediaActivity redditViewImgurMediaActivity);

    void inject(ViewImgurVideoFragment viewImgurVideoFragment);

    void inject(RedditDownloadRedditVideoService redditDownloadRedditVideoService);

    void inject(MultiRedditListingFragment multiRedditListingFragment);

    void inject(InboxFragment inboxFragment);

    void inject(RedditViewPrivateMessagesActivity redditViewPrivateMessagesActivity);

    void inject(SendPrivateMessageActivity sendPrivateMessageActivity);

    void inject(VideoPreferenceFragment videoPreferenceFragment);

    void inject(RedditViewRedditGalleryActivity redditViewRedditGalleryActivity);

    void inject(ViewRedditGalleryVideoFragment viewRedditGalleryVideoFragment);

    void inject(CustomizeMainPageTabsFragment customizeMainPageTabsFragment);

    void inject(RedditDownloadMediaService redditDownloadMediaService);

    void inject(DownloadLocationPreferenceFragment downloadLocationPreferenceFragment);

    void inject(RedditSubmitCrosspostActivity redditSubmitCrosspostActivity);

    void inject(FullMarkdownActivity fullMarkdownActivity);

    void inject(SelectUserFlairActivity selectUserFlairActivity);

    void inject(SecurityPreferenceFragment securityPreferenceFragment);

    void inject(NsfwAndSpoilerFragment nsfwAndSpoilerFragment);

    void inject(CustomizeBottomAppBarFragment customizeBottomAppBarFragment);

    void inject(GiveAwardActivity giveAwardActivity);

    void inject(TranslationFragment translationFragment);

    void inject(FetchRandomSubredditOrPostActivity fetchRandomSubredditOrPostActivity);

    void inject(MiscellaneousPreferenceFragment miscellaneousPreferenceFragment);

    void inject(CustomizePostFilterActivity customizePostFilterActivity);

    void inject(PostHistoryFragment postHistoryFragment);

    void inject(RedditPostFilterPreferenceActivity redditPostFilterPreferenceActivity);

    void inject(PostFilterUsageListingActivity postFilterUsageListingActivity);

    void inject(RedditSearchUsersResultActivity redditSearchUsersResultActivity);

    void inject(MultiredditSelectionActivity multiredditSelectionActivity);

    void inject(ViewImgurImageFragment viewImgurImageFragment);

    void inject(ViewRedditGalleryImageOrGifFragment viewRedditGalleryImageOrGifFragment);

    void inject(ViewPostDetailFragment viewPostDetailFragment);

    void inject(SuicidePreventionActivity suicidePreventionActivity);

    void inject(RedditWebViewActivity redditWebViewActivity);

    void inject(CrashReportsFragment crashReportsFragment);

    void inject(AnonymousSubscriptionsActivity anonymousSubscriptionsActivity);

    void inject(RedditLockScreenActivity redditLockScreenActivity);

    void inject(MaterialYouService materialYouService);

    void inject(RedditRPANActivity redditRpanActivity);

    void inject(ViewRPANBroadcastFragment viewRPANBroadcastFragment);

    void inject(RedditPostGalleryActivity redditPostGalleryActivity);

    void inject(RedditTrendingActivity redditTrendingActivity);
}
