package warehouse_api.controller;

import warehouse_api.model.dto.CategoryCreateDto;
import warehouse_api.service.CategoryService;
import warehouse_api.service.exception.BusinessException;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("category")
public class CategoryController extends BaseController {

    @EJB
    private CategoryService categoryService;

    @GET
    @Path("categories")
    public Response getAll() {
        return sendSuccess(categoryService.all());
    }

    @POST
    @Path("create")
    @RolesAllowed({"ADMIN"})
    public Response create(CategoryCreateDto createDto) throws BusinessException {
        return sendSuccess(categoryService.create(createDto));
    }
}