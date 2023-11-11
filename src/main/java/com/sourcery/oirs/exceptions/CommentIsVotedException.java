package com.sourcery.oirs.exceptions;

public class CommentIsVotedException extends RuntimeException {
    public CommentIsVotedException(String message) {
        super(message);
    }
}
