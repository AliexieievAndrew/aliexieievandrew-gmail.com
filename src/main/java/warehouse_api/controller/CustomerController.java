package warehouse_api.controller;

import warehouse_api.model.dto.CustomerCreateDto;
import warehouse_api.model.entity.Customer;
import warehouse_api.service.CustomerService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("customer")
public class CustomerController extends BaseController {

    @EJB
    private CustomerService customerService;

    @POST
    @Path("create")
    @RolesAllowed({"ADMIN", "MANAGER"})
    public Response create(@Valid CustomerCreateDto dto) {
        Customer save = customerService.createCustomer(dto);

        return sendCreated(save.getCustomerName() + " is created");
    }
}
