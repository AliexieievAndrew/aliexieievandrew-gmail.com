package warehouse_api.controller.mapper;

import warehouse_api.service.exception.UserNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserNotFoundMapper implements ExceptionMapper<UserNotFoundException> {

    public Response toResponse(UserNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
    }
}
