package warehouse_api.controller;

import warehouse_api.model.dto.ResponeDto;

import javax.ws.rs.core.Response;

public class BaseController {

    public Response sendSucces(Object info) {
        ResponeDto responeDto = new ResponeDto(info);

        return Response.ok().entity(responeDto).build();
    }

    public Response sendCreated(Object info) {
        ResponeDto responeDto = new ResponeDto(info);

        return Response.status(Response.Status.CREATED).entity(responeDto).build();
    }

    public Response sendError(Object info) {
        ResponeDto responeDto = new ResponeDto(1, info);

        return Response.status(Response.Status.BAD_REQUEST).entity(responeDto).build();
    }
}