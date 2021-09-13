package com.rackluxury.paintings.reddit.fragments;

public class ChangePullToRefreshEvent {
    public boolean pullToRefresh;

    public ChangePullToRefreshEvent(boolean pullToRefresh) {
        this.pullToRefresh = pullToRefresh;
    }
}
