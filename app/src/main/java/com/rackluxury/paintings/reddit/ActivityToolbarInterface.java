package com.rackluxury.paintings.reddit;

public interface ActivityToolbarInterface {
    void onLongPress();
    default void displaySortType() {}
}
