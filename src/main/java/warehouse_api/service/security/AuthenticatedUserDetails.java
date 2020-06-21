package warehouse_api.service.security;

import warehouse_api.model.enums.UserRole;

import java.security.Principal;
import java.util.Collections;
import java.util.Set;

public class AuthenticatedUserDetails implements Principal {
    private final String username;
    private final Set<UserRole> roles;

    public AuthenticatedUserDetails(String username, Set<UserRole> roles) {
        this.username = username;
        this.roles = Collections.unmodifiableSet(roles);
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    @Override
    public String getName() {
        return username;
    }
}
