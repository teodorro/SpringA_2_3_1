package ru.netology.exception;

public class NotFoundException extends Exception {
    public NotFoundException(long id) {
        super("Post with id=" + id + " not found");
    }
}
