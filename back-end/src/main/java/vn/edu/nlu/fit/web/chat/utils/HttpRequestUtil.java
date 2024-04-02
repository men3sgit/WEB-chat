package vn.edu.nlu.fit.web.chat.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.function.ServerRequest;

import java.util.Optional;

/**
 * Utility class for retrieving request-related information in a Spring Boot application.
 *
 * @author Menes
 * @since 1.0.0
 */
public class HttpRequestUtil {

    /**
     * Private constructor to prevent instantiation.
     */
    private HttpRequestUtil() {
        // Intentionally empty
    }

    /**
     * Retrieves the request URL from the current request context, handling both Servlet and
     * reactive environments.
     *
     * @return Optional containing the request URL, or empty if unavailable.
     */
    public static Optional<String> getHttpRequestURL() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .flatMap(HttpRequestUtil::extractURL);
    }

    /**
     * Extracts the request URL from the given RequestAttributes, handling different request types.
     *
     * @param attributes the RequestAttributes to extract the URL from
     * @return Optional containing the request URL, or empty if not retrievable
     */
    private static Optional<String> extractURL(RequestAttributes attributes) {
        if (attributes instanceof ServletRequestAttributes) {
            // Handle Servlet-based requests
            return Optional.of(((ServletRequestAttributes) attributes).getRequest().getRequestURL().toString());
        } else if (attributes instanceof ServerRequest) {
            // Handle reactive requests
            return Optional.of(((ServerRequest) attributes).uri().toString());
        } else {
            // Unsupported request type
            return Optional.empty();
        }
    }
}
