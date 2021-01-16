package ru.netology.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {
    public NotFoundException(long id) {
        super("Post with id=" + id + " not found");
    }
}
