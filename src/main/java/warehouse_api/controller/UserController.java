package warehouse_api.controller;

import warehouse_api.model.dto.UserCreateDto;
import warehouse_api.model.entity.User;
import warehouse_api.service.UserService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

@Path("user")
public class UserController extends BaseController {

    @EJB
    private UserService userService;

    @POST
    @Path("create")
    @RolesAllowed({"ADMIN"})
    public Response create(UserCreateDto dto) {

        User user = userService.createUser(dto);

        return sendCreated(user.getUsername() + " is created");
    }

    @GET
    @RolesAllowed({"ADMIN"})
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response userList(@Context SecurityContext securityContext) {
        Principal principal = securityContext.getUserPrincipal();
        String username = principal.getName();
        System.out.println("username: "  + username);

        return sendSuccess(userService.getAllUsers());
    }
}
