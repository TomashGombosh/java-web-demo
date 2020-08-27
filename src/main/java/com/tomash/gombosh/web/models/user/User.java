package com.tomash.gombosh.web.models.user;

import java.util.function.Consumer;

import lombok.Data;

import static java.util.Objects.requireNonNull;

/**
 * @author Tomash Gombosh
 */
@Data
public class User {
    private String email;
    private String password;

    public User(final Consumer<User> builder) {
        requireNonNull(builder).accept(this);
    }
}
