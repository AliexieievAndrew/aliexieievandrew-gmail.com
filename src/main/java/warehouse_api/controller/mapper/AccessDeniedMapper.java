package warehouse_api.controller.mapper;

import warehouse_api.service.exception.AccessDeniedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AccessDeniedMapper implements ExceptionMapper<AccessDeniedException> {
    @Override
    public Response toResponse(AccessDeniedException e) {
        return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
    }
}
