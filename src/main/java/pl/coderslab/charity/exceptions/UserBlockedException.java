package pl.coderslab.charity.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UserBlockedException extends AuthenticationException {

    public UserBlockedException() {
        super("User has been blocked!");
    }
}