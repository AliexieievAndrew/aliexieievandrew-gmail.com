package warehouse_api.controller;

import warehouse_api.model.dto.CategoryCreateDto;
import warehouse_api.model.dto.ItemCreateDto;
import warehouse_api.model.entity.Item;
import warehouse_api.service.ItemService;
import warehouse_api.service.exception.BusinessException;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("item")
public class ItemController extends BaseController {

    @EJB
    private ItemService itemService;

    @GET
    @Path("items")
    public Response getAll() {
        return sendSuccess(itemService.all());
    }

    @POST
    @Path("create")
    @RolesAllowed({"ADMIN"})
    public Response create(ItemCreateDto createDto, @Context SecurityContext securityContext) throws BusinessException {
        Item item = itemService.create(createDto, securityContext.getUserPrincipal().getName());

        return sendCreated(item.getItemName() + " is created");
    }
}
