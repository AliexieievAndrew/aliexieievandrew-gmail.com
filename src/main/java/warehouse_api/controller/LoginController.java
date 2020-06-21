package warehouse_api.controller;

import warehouse_api.model.entity.User;
import warehouse_api.model.enums.UserRole;
import warehouse_api.service.security.AuthenticationToken;
import warehouse_api.service.security.AuthenticationTokenService;
import warehouse_api.service.security.UserValidationService;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;

@Path("auth")
public class LoginController extends BaseController {

    @EJB
    private UserValidationService userValidationService;

    @EJB
    private AuthenticationTokenService authenticationTokenService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("login")
    @PermitAll
    public Response login(@QueryParam("username") String username, @QueryParam("password") String password) throws Exception {
        User user = userValidationService.authenticate(username, password);

        String token = authenticationTokenService.issueToken(user.getUsername(), new HashSet<UserRole>() {{
            add(user.getUserRole());
        }});

        return sendSuccess(new AuthenticationToken(token));
    }
}
