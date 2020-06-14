package warehouse_api.controller.mapper;

import warehouse_api.service.exception.BadPasswordException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BadPasswordMapper implements ExceptionMapper<BadPasswordException> {
    @Override
    public Response toResponse(BadPasswordException e) {
        return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
    }
}
