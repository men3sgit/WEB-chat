package vn.edu.nlu.fit.web.chat.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * Enum representing roles in the system.
 */
public enum Role {

    USER("user", "Regular User"),

    ADMIN("admin", "Administrator"),

    MODERATOR("moderator", "Moderator");

    private final String roleName;
    /**
     * -- GETTER --
     *  Get the display name of the role.
     *
     * @return The display name of the role.
     */
    @Getter
    private final String displayName;

    /**
     * Constructor for Role enum.
     *
     * @param roleName     The name of the role used in JSON serialization.
     * @param displayName  The display name of the role.
     */
    Role(String roleName, String displayName) {
        this.roleName = roleName;
        this.displayName = displayName;
    }

    /**
     * Get the role name.
     *
     * @return The role name.
     */
    @JsonValue
    public String getRoleName() {
        return roleName;
    }

    /**
     * Deserialize a Role from its string representation.
     *
     * @param roleName The string representation of the role.
     * @return The corresponding Role object.
     * @throws IllegalArgumentException if the given roleName is not a valid role name.
     */
    @JsonCreator
    public static Role fromString(String roleName) {
        for (Role role : Role.values()) {
            if (role.getRoleName().equals(roleName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown role name: " + roleName);
    }
}
