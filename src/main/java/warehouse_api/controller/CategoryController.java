package warehouse_api.controller;

import warehouse_api.model.entity.Category;
import warehouse_api.service.CategoryService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("category")
public class CategoryController extends BaseController {

    @EJB
    private CategoryService categoryService;

    @GET
    @Path("categories")
    public Response getAll() {
        return sendSucces(categoryService.all());
    }

    @GET // post
    @Path("new")
    public Response save() {
        Category category = new Category();
        category.setCategoryName("new test category");
        return sendSucces(categoryService.save(category));
    }
}