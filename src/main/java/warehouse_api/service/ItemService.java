package warehouse_api.service;

import warehouse_api.model.entity.Item;
import warehouse_api.repository.ItemDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ItemService {

    @EJB
    private ItemDao itemDao;

    public List<Item> all() {
        return itemDao.findAll();
    }

    public Item save(Item item) {
        return itemDao.persist(item);
    }

    public Item itemByName(String name) {
        return itemDao.userByName(name);
    }
}
