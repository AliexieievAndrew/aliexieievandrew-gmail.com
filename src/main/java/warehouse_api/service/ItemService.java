package warehouse_api.service;

import warehouse_api.model.dto.ItemCreateDto;
import warehouse_api.model.entity.Category;
import warehouse_api.model.entity.Item;
import warehouse_api.model.entity.User;
import warehouse_api.repository.ItemDao;
import warehouse_api.service.exception.BusinessException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;

@Stateless
public class ItemService {

    @EJB
    private ItemDao itemDao;

    @EJB
    private CategoryService categoryService;

    @EJB
    private UserService userService;

    public List<Item> all() {
        return itemDao.findAll();
    }

    public Item save(Item item) {
        return itemDao.persist(item);
    }

    public Item itemByName(String name) {
        return itemDao.userByName(name);
    }

    public Item create(ItemCreateDto createDto, String username) throws BusinessException {
        Category category = categoryService.categoryByName(createDto.getCategoryName());

        if(category == null) {
            throw new BusinessException("Category: " + createDto.getCategoryName() + " is not exist");
        }

        Item itemByName = itemByName(createDto.getName());

        if (itemByName != null) {
            throw new BusinessException("Item: " + itemByName.getItemName() + " already exist");
        }

        User user = userService.userByName(username);

        Item item = new Item(createDto.getName(), category, new Date(), user);

        return save(item);
    }
}
