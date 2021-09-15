package com.rackluxury.rolex.activities;

public class ImageItem {
    private String mImageUrl;
    private String mCreator;
    private int mLikes;

    public ImageItem(String imageUrl, String creator, int likes) {
        mImageUrl = imageUrl;
        mCreator = creator;
        mLikes = likes;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getCreator() {
        return mCreator;
    }

    public int getLikeCount() {
        return mLikes;
    }
}