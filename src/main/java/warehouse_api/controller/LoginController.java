package warehouse_api.controller;

import org.mindrot.jbcrypt.BCrypt;
import warehouse_api.model.entity.User;
import warehouse_api.service.UserService;
import warehouse_api.service.exception.BadPasswordException;
import warehouse_api.service.exception.UserNotFoundException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("auth")
public class LoginController extends BaseController {

    @EJB
    private UserService userService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("login")
    public Response login(@QueryParam("username") String username, @QueryParam("password") String password) throws Exception {
        authenticate(username, password);

        String token = issueToken(username);

        return sendSuccess("");
    }

    private void authenticate(String username, String password) throws Exception {
        User user = userService.userByName(username);

        if(user == null) {
            throw new UserNotFoundException(String.format("User %s not found", username));
        }


        if (!BCrypt.checkpw(password, user.getUserPassword())) {
            throw new BadPasswordException("Password is wrong");
        }
    }

    private String issueToken(String username) {
        return "";
    }
}
