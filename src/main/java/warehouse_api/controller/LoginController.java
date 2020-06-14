package warehouse_api.controller;

import warehouse_api.model.entity.User;
import warehouse_api.service.UserService;
import warehouse_api.service.exception.UserNotFoundException;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("auth")
public class LoginController extends BaseController {

    @EJB
    private UserService userService;

    @POST
    @Path("login")
    public Response login(@QueryParam("username") String username, @QueryParam("password") String password) throws UserNotFoundException {
        User user = userService.userByName(username);

        if(user == null) throw new UserNotFoundException(String.format("User %s not found", username));

        return sendSuccess("");
    }
}
