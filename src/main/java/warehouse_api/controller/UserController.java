package warehouse_api.controller;

import warehouse_api.model.dto.UserCreateDto;
import warehouse_api.model.entity.User;
import warehouse_api.service.UserService;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("user")
public class UserController extends BaseController {

    @EJB
    private UserService userService;

    @POST
    @Path("create")
    public Response save(UserCreateDto dto) {

        User user = userService.createUser(dto);

        return sendCreated(user.getUsername() + " is created");
    }
}
