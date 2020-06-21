package warehouse_api.service;

import org.junit.Assert;
import org.junit.Test;
import warehouse_api.config.BaseTest;
import warehouse_api.model.dto.ItemCreateDto;
import warehouse_api.model.entity.Category;
import warehouse_api.model.entity.Item;
import warehouse_api.model.entity.User;
import warehouse_api.repository.ItemDao;

import java.util.Date;
import java.util.List;

public class ItemServiceTest extends BaseTest {

    @Test
    public void testContext() throws Exception{
        ItemService itemService = (ItemService) ctx.lookup("java:global/classes/ItemService");
        ItemDao userDao = (ItemDao) ctx.lookup("java:global/classes/ItemDao");

        Assert.assertNotNull(itemService);
        Assert.assertNotNull(userDao);
    }

    @Test
    public void testAll() throws Exception {
        ItemService itemService = (ItemService) ctx.lookup("java:global/classes/ItemService");

        List<Item> all = itemService.all();

        Assert.assertNotNull(all);
        Assert.assertTrue(all.size() > 0);
    }

    @Test
    public void testSave() throws Exception {
        String itemName = "Test item";

        ItemService itemService = (ItemService) ctx.lookup("java:global/classes/ItemService");
        CategoryService categoryService = (CategoryService) ctx.lookup("java:global/classes/CategoryService");
        UserService userService = (UserService) ctx.lookup("java:global/classes/UserService");

        User user = userService.all().get(0);
        Category category = categoryService.all().get(0);

        Item item = new Item();
        item.setItemName(itemName);
        item.setUser(user);
        item.setCategory(category);
        item.setCreateDate(new Date());

        itemService.save(item);

        Item itemDb = itemService.itemByName(itemName);

        Assert.assertNotNull(itemDb);
        Assert.assertTrue(itemName.equals(itemDb.getItemName()));
    }

    @Test
    public void testCrate() throws Exception {
        String userName = "test name";
        String categoryName = "test name";
        String itemName = "new item name";

        ItemService itemService = (ItemService) ctx.lookup("java:global/classes/ItemService");

        ItemCreateDto dto = new ItemCreateDto(itemName, categoryName);

        itemService.create(dto, userName);

        Item item = itemService.itemByName(itemName);

        Assert.assertNotNull(item);
        Assert.assertEquals(userName, item.getUser().getUsername());
        Assert.assertEquals(categoryName, item.getCategory().getCategoryName());
    }
}
