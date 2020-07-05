package warehouse_api.controller;

import warehouse_api.model.dto.ResponseDto;
import warehouse_api.model.entity.User;
import warehouse_api.service.UserService;

import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

public class BaseController {

    @Context
    private SecurityContext securityContext;

    @EJB
    private UserService userService;

    public Response sendSuccess(Object info) {
        ResponseDto responseDto = new ResponseDto(info);

        return Response.ok().entity(responseDto).build();
    }

    public Response sendCreated(Object info) {
        ResponseDto responseDto = new ResponseDto(info);

        return Response.status(Response.Status.CREATED).entity(responseDto).build();
    }

    public Response sendError(Object info) {
        ResponseDto responseDto = new ResponseDto(1, info);

        return Response.status(Response.Status.BAD_REQUEST).entity(responseDto).build();
    }

    public User getActiveUser() {
        String name = securityContext.getUserPrincipal().getName();

        return userService.userByName(name);
    }
}