package vn.edu.nlu.fit.web.chat.service;

import vn.edu.nlu.fit.web.chat.payload.LoginResponse;

public interface AuthenticationService {


    /**
     * Logs a user into the chat system.
     *
     * @param email the email of the user
     * @param password the user's password
     */
    LoginResponse login(String email, String password);

    /**
     * Logs the currently authenticated user out of the chat system.
     */
    void logout();

    /**
     * Checks if a user is currently authenticated.
     *
     * @return true if a user is logged in, false otherwise
     */
    boolean isLoggedIn();

    /**
     * Retrieves the username of the currently authenticated user.
     *
     * @return the username if logged in, null otherwise
     */
    String getCurrentUsername();

    void verifyNewUser(String token);
}

