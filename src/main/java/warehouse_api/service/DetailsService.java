package warehouse_api.service;

import javassist.NotFoundException;
import warehouse_api.model.dto.DetailsCreateDto;
import warehouse_api.model.dto.DetailsDto;
import warehouse_api.model.entity.Customer;
import warehouse_api.model.entity.Details;
import warehouse_api.model.entity.Item;
import warehouse_api.model.entity.User;
import warehouse_api.model.enums.DetailsType;
import warehouse_api.repository.DetailsDao;
import warehouse_api.service.exception.BusinessException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.*;
import java.util.stream.Collectors;

@Stateless
public class DetailsService {

    @EJB
    private DetailsDao detailsDao;

    @EJB
    private UserService userService;

    @EJB
    private CustomerService customerService;

    @EJB
    private ItemService itemService;

    public List<Details> all() {
        return detailsDao.findAll();
    }

    public Details save(Details details) {
        return detailsDao.persist(details);
    }

    public List<Details> save(List<Details> detailsList) {
        return detailsDao.persist(detailsList);
    }

    public List<Details> detailsByType(DetailsType detailsType) {
        return detailsDao.detailsByType(detailsType);
    }

    public List<Details> create(DetailsCreateDto createDto, User user) throws BusinessException, NotFoundException {
        Customer customer = customerService.customerByName(createDto.getCustomerName());

        if(customer == null) {
            throw new BusinessException("Customer: " + createDto.getCustomerName() + " is not exist");
        }

        List<Item> items = fetchItems(createDto.getDetails());

        UUID orderId = UUID.randomUUID();
        Date createDate = new Date();

        List<Details> detailsList = new ArrayList<>();

        for (DetailsDto dto : createDto.getDetails()) {
            Optional<Item> optionalItem = items.stream().filter(item -> item.getItemName().equals(dto.getItemName())).findFirst();

            DetailsType detailsType = createDto.getDetailsType();

            if(optionalItem.isPresent()) {
                Item item = optionalItem.get();

                Details details = new Details(
                        detailsType,
                        createDate,
                        user,
                        customer,
                        item,
                        getCurrentQuantity(detailsType, dto.getQuantity()),
                        dto.getAdditionalInfo(),
                        orderId);

                detailsList.add(details);
            } else {
                throw new NotFoundException("Item: " + dto.getItemName() + " is not exist");
            }
        }

        return this.save(detailsList);
    }

    private void checkBalance(Item item) {

    }

    private Double getCurrentQuantity(DetailsType detailsType, Double quantity) throws BusinessException {
        switch (detailsType) {
            case OUTCOME:
                return quantity * (-1);
            case INCOME:
                return quantity;
            default:
                throw new BusinessException("invalid details type: " + detailsType);
        }
    }

    private List<Item> fetchItems(List<DetailsDto> details) {
        List<String> itemsNames = details.stream()
                .map(DetailsDto::getItemName)
                .collect(Collectors.toList());

       return itemService.itemsByNames(itemsNames);
    }
}
