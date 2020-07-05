package warehouse_api.controller;

import javassist.NotFoundException;
import warehouse_api.model.dto.DetailsCreateDto;
import warehouse_api.model.entity.User;
import warehouse_api.service.DetailsService;
import warehouse_api.service.exception.BusinessException;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("details")
public class DetailsController extends BaseController {

    @EJB
    private DetailsService detailsService;

    @POST
    @Path("create")
    public Response createDetails(@Valid DetailsCreateDto createDto) throws NotFoundException, BusinessException {
        User activeUser = getActiveUser();

        detailsService.create(createDto, activeUser);

        return sendCreated("Details created");
    }
}
