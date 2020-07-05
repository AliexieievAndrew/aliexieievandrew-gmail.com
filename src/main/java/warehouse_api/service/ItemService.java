package warehouse_api.service;

import warehouse_api.model.dto.ItemCreateDto;
import warehouse_api.model.entity.*;
import warehouse_api.model.enums.DetailsType;
import warehouse_api.repository.ItemDao;
import warehouse_api.service.exception.BusinessException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Stateless
public class ItemService {

    private static final String TECHNICAL_CUSTOMER = "TECHNICAL";

    private static final String TECHNICAL_INIT_ITEM_INFO = "init item";

    @EJB
    private ItemDao itemDao;

    @EJB
    private CategoryService categoryService;

    @EJB
    private UserService userService;

    @EJB
    private CustomerService customerService;

    @EJB
    private DetailsService detailsService;

    public List<Item> all() {
        return itemDao.findAll();
    }

    public Item save(Item item) {
        return itemDao.persist(item);
    }

    public Item itemByName(String name) {
        return itemDao.itemByName(name);
    }

    public List<Item> itemsByNames(List<String> names) {
        return itemDao.itemsByNames(names);
    }

    public Item create(ItemCreateDto createDto, User user) throws BusinessException {
        Category category = categoryService.categoryByName(createDto.getCategoryName());

        if(category == null) {
            throw new BusinessException("Category: " + createDto.getCategoryName() + " is not exist");
        }

        Item itemByName = itemByName(createDto.getName());

        if (itemByName != null) {
            throw new BusinessException("Item: " + itemByName.getItemName() + " already exist");
        }

        Item item = new Item(createDto.getName(), category, new Date(), user);

        Item created = save(item);

        prepareBalanceDetail(created, user);

        return created;
    }

    private void prepareBalanceDetail(Item item, User user) {
        Customer customer = customerService.customerByName(TECHNICAL_CUSTOMER);

        Details details1 = new Details(
                DetailsType.TECHNICAL,
                new Date(),
                user,
                customer,
                item,
                0D,
                TECHNICAL_INIT_ITEM_INFO,
                UUID.randomUUID());

        detailsService.save(details1);
    }
}
