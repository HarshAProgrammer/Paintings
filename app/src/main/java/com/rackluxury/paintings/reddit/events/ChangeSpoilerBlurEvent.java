package com.rackluxury.paintings.reddit.events;

public class ChangeSpoilerBlurEvent {
    public boolean needBlurSpoiler;

    public ChangeSpoilerBlurEvent(boolean needBlurSpoiler) {
        this.needBlurSpoiler = needBlurSpoiler;
    }
}
