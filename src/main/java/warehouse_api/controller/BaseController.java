package warehouse_api.controller;

import warehouse_api.model.dto.ResponseDto;

import javax.ws.rs.core.Response;

public class BaseController {

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
}