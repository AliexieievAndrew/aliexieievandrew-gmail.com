package warehouse_api.controller.filter;

import warehouse_api.model.entity.User;
import warehouse_api.model.enums.UserRole;
import warehouse_api.service.UserService;
import warehouse_api.service.security.AuthenticatedUserDetails;
import warehouse_api.service.security.AuthenticationTokenDetails;
import warehouse_api.service.security.AuthenticationTokenService;
import warehouse_api.service.security.TokenBasedSecurityContext;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.ws.rs.Priorities;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.util.HashSet;

//@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @EJB
    private UserService userService;

    @EJB
    private AuthenticationTokenService authenticationTokenService;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String authenticationToken = authorizationHeader.substring(7);
            validateToken(authenticationToken, requestContext);
            return;
        }
    }

    private void validateToken(String authenticationToken, ContainerRequestContext requestContext) {
        AuthenticationTokenDetails authenticationTokenDetails = authenticationTokenService.parseToken(authenticationToken);
        User user = userService.userByName(authenticationTokenDetails.getUsername());

        AuthenticatedUserDetails authenticatedUserDetails = new AuthenticatedUserDetails(user.getUsername(), new HashSet<UserRole>() {{
            add(user.getUserRole());
        }});

        boolean isSecure = requestContext.getSecurityContext().isSecure();

        SecurityContext securityContext = new TokenBasedSecurityContext(authenticatedUserDetails, authenticationTokenDetails, isSecure);
        requestContext.setSecurityContext(securityContext);
    }
}
