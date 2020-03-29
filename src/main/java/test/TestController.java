package test;

import warehouse_api.model.entity.Category;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/test")
public class TestController {
    private static int a = 101;
    @EJB
    private ServiceTest serviceTest;

    @GET
    @Path("/ping")
    public Response ping() {
//        serviceTest.test();

        Category category = new Category();
        category.setCategoryName("JPA NAMAE" + a++);
//        serviceTest.saveUser(category);

        System.out.println("*******");
        List<Category> categories = serviceTest.test3();

        return Response.ok().entity(categories).build();
    }
}
