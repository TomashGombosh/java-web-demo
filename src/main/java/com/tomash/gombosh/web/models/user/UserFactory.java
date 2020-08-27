package com.tomash.gombosh.web.models.user;

import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

import com.tomash.gombosh.web.services.Factory;

import static com.tomash.gombosh.web.utils.ApplicationProperties.BASE_PASSWORD;
import static com.tomash.gombosh.web.utils.ApplicationProperties.BASE_USER;

/**
 * @author Tomash Gombosh
 */
@Data
@Setter
@Accessors(chain = true)
public class UserFactory implements Factory<User, UserFactory> {
    private String email = BASE_USER;
    private String password = BASE_PASSWORD;

    @Override
    public User create() {
        return new User(user -> {
            user.setEmail(email);
            user.setPassword(password);
        });
    }
}
