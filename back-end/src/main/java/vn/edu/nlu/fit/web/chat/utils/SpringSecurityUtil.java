package vn.edu.nlu.fit.web.chat.utils;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * A utility class providing helper methods for interacting with Spring Security.
 */
public class SpringSecurityUtil {

    /**
     * Retrieves the currently authenticated username from the Spring Security context.
     *
     * @return the username if authenticated, null otherwise
     */
    public static String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            }
        }

        return null;
    }
}
