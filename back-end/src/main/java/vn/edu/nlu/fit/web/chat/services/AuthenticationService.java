package vn.edu.nlu.fit.web.chat.services;

import vn.edu.nlu.fit.web.chat.payload.AuthenticationResponse;

public interface AuthenticationService {


    /**
     * Logs a user into the chat system.
     *
     * @param username the username of the user
     * @param password the user's password
     */
    AuthenticationResponse login(String username, String password);

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
}

