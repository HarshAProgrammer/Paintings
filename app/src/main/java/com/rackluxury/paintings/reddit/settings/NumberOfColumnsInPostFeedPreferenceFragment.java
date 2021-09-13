package com.rackluxury.paintings.reddit.settings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.rackluxury.paintings.R;

public class NumberOfColumnsInPostFeedPreferenceFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.number_of_columns_in_post_feed_preferences, rootKey);
    }
}
