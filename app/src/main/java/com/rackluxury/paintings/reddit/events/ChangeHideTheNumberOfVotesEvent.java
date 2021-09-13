package com.rackluxury.paintings.reddit.events;

public class ChangeHideTheNumberOfVotesEvent {
    public boolean hideTheNumberOfVotes;

    public ChangeHideTheNumberOfVotesEvent(boolean hideTheNumberOfVotes) {
        this.hideTheNumberOfVotes = hideTheNumberOfVotes;
    }
}
