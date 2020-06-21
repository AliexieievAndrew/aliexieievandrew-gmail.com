package warehouse_api.service.security;

import io.jsonwebtoken.*;
import warehouse_api.model.enums.UserRole;
import warehouse_api.service.exception.InvalidAuthenticationTokenException;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Component which provides operations for parsing JWT tokens.
 */
@Singleton
public class AuthenticationTokenParser {

    @EJB
    private AuthenticationTokenSettings settings;

    /**
     * Parse a JWT token.
     *
     * @param token
     * @return
     */
    public AuthenticationTokenDetails parseToken(String token) {

        try {

            Claims claims = Jwts.parser()
                    .setSigningKey(settings.getSecret())
                    .requireAudience(settings.getAudience())
                    .setAllowedClockSkewSeconds(settings.getClockSkew())
                    .parseClaimsJws(token)
                    .getBody();

            return new AuthenticationTokenDetails.Builder()
                    .withId(extractTokenIdFromClaims(claims))
                    .withUsername(extractUsernameFromClaims(claims))
                    .withAuthorities(extractAuthoritiesFromClaims(claims))
                    .withIssuedDate(extractIssuedDateFromClaims(claims))
                    .withExpirationDate(extractExpirationDateFromClaims(claims))
                    .withRefreshCount(extractRefreshCountFromClaims(claims))
                    .withRefreshLimit(extractRefreshLimitFromClaims(claims))
                    .build();

        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException e) {
            throw new InvalidAuthenticationTokenException("Invalid token");
        } catch (ExpiredJwtException e) {
            throw new InvalidAuthenticationTokenException("Expired token");
        } catch (InvalidClaimException e) {
            throw new InvalidAuthenticationTokenException("Invalid value for claim \"" + e.getClaimName() + "\"");
        } catch (Exception e) {
            throw new InvalidAuthenticationTokenException("Invalid token");
        }
    }

    /**
     * Extract the token identifier from the token claims.
     *
     * @param claims
     * @return Identifier of the JWT token
     */
    private String extractTokenIdFromClaims(Claims claims) {
        return (String) claims.get(Claims.ID);
    }

    /**
     * Extract the username from the token claims.
     *
     * @param claims
     * @return Username from the JWT token
     */
    private String extractUsernameFromClaims(Claims claims) {
        return claims.getSubject();
    }

    /**
     * Extract the user authorities from the token claims.
     *
     * @param claims
     * @return User authorities from the JWT token
     */
    private Set<UserRole> extractAuthoritiesFromClaims(Claims claims) {
        List<String> rolesAsString = (List<String>) claims.getOrDefault(settings.getAuthoritiesClaimName(), new ArrayList<>());
        return rolesAsString.stream().map(UserRole::valueOf).collect(Collectors.toSet());
    }

    /**
     * Extract the issued date from the token claims.
     *
     * @param claims
     * @return Issued date of the JWT token
     */
    private ZonedDateTime extractIssuedDateFromClaims(Claims claims) {
        return ZonedDateTime.ofInstant(claims.getIssuedAt().toInstant(), ZoneId.systemDefault());
    }

    /**
     * Extract the expiration date from the token claims.
     *
     * @param claims
     * @return Expiration date of the JWT token
     */
    private ZonedDateTime extractExpirationDateFromClaims(Claims claims) {
        return ZonedDateTime.ofInstant(claims.getExpiration().toInstant(), ZoneId.systemDefault());
    }

    /**
     * Extract the refresh count from the token claims.
     *
     * @param claims
     * @return Refresh count from the JWT token
     */
    private int extractRefreshCountFromClaims(Claims claims) {
        return (int) claims.get(settings.getRefreshCountClaimName());
    }

    /**
     * Extract the refresh limit from the token claims.
     *
     * @param claims
     * @return Refresh limit from the JWT token
     */
    private int extractRefreshLimitFromClaims(Claims claims) {
        return (int) claims.get(settings.getRefreshLimitClaimName());
    }
}
