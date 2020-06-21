package warehouse_api.service.security;


import warehouse_api.model.enums.UserRole;
import warehouse_api.service.exception.AuthenticationTokenRefreshmentException;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Priorities;
import javax.ws.rs.ext.Provider;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * JWT authentication filter
 */
@Provider
@Stateless
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationTokenService {

    /**
     * How long the token is valid for (in seconds).
     */
    private Long validFor = 36000L;

    /**
     * How many times the token can be refreshed.
     */
    private Integer refreshLimit = 1;

    @EJB
    private AuthenticationTokenIssuer tokenIssuer;

    @EJB
    private AuthenticationTokenParser tokenParser;

    /**
     * Issue a token for a user with the given roles.
     *
     * @param username
     * @param roles
     * @return
     */
    public String issueToken(String username, Set<UserRole> roles) {

        String id = generateTokenIdentifier();
        ZonedDateTime issuedDate = ZonedDateTime.now();
        ZonedDateTime expirationDate = calculateExpirationDate(issuedDate);

        AuthenticationTokenDetails authenticationTokenDetails = new AuthenticationTokenDetails.Builder()
                .withId(id)
                .withUsername(username)
                .withAuthorities(roles)
                .withIssuedDate(issuedDate)
                .withExpirationDate(expirationDate)
                .withRefreshCount(0)
                .withRefreshLimit(refreshLimit)
                .build();

        return tokenIssuer.issueToken(authenticationTokenDetails);
    }

    /**
     * Parse and validate the token.
     *
     * @param token
     * @return
     */
    public AuthenticationTokenDetails parseToken(String token) {
        return tokenParser.parseToken(token);
    }

    /**
     * Refresh a token.
     *
     * @param currentTokenDetails
     * @return
     */
    public String refreshToken(AuthenticationTokenDetails currentTokenDetails) {

        if (!currentTokenDetails.isEligibleForRefreshment()) {
            throw new AuthenticationTokenRefreshmentException("This token cannot be refreshed");
        }

        ZonedDateTime issuedDate = ZonedDateTime.now();
        ZonedDateTime expirationDate = calculateExpirationDate(issuedDate);

        AuthenticationTokenDetails newTokenDetails = new AuthenticationTokenDetails.Builder()
                .withId(currentTokenDetails.getId()) // Reuse the same id
                .withUsername(currentTokenDetails.getUsername())
                .withAuthorities(currentTokenDetails.getRoles())
                .withIssuedDate(issuedDate)
                .withExpirationDate(expirationDate)
                .withRefreshCount(currentTokenDetails.getRefreshCount() + 1)
                .withRefreshLimit(refreshLimit)
                .build();

        return tokenIssuer.issueToken(newTokenDetails);
    }

    /**
     * Calculate the expiration date for a token.
     *
     * @param issuedDate
     * @return
     */
    private ZonedDateTime calculateExpirationDate(ZonedDateTime issuedDate) {
        return issuedDate.plusSeconds(validFor);
    }

    /**
     * Generate a token identifier.
     *
     * @return
     */
    private String generateTokenIdentifier() {
        return UUID.randomUUID().toString();
    }

}
