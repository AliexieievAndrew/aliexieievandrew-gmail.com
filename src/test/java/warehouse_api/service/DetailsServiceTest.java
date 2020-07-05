package warehouse_api.service;

import org.junit.Assert;
import org.junit.Test;
import warehouse_api.config.BaseTestConf;
import warehouse_api.model.StubGenerator;
import warehouse_api.model.dto.BalanceDto;
import warehouse_api.model.dto.DetailsCreateDto;
import warehouse_api.model.entity.Customer;
import warehouse_api.model.entity.Details;
import warehouse_api.model.entity.Item;
import warehouse_api.model.entity.User;
import warehouse_api.model.enums.DetailsType;
import warehouse_api.repository.DetailsDao;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class DetailsServiceTest extends BaseTestConf {

    @Test
    public void testContext() throws Exception{
        DetailsService detailsService = (DetailsService) ctx.lookup("java:global/classes/DetailsService");
        DetailsDao detailsDao = (DetailsDao) ctx.lookup("java:global/classes/DetailsDao");

        Assert.assertNotNull(detailsService);
        Assert.assertNotNull(detailsDao);
    }

    @Test
    public void testAll() throws Exception {
        DetailsService detailsService = (DetailsService) ctx.lookup("java:global/classes/DetailsService");

        List<Details> all = detailsService.all();

        Assert.assertNotNull(all);
        Assert.assertTrue(all.size() > 0);
    }

    @Test
    public void testSave() throws Exception {
        DetailsService detailsService = (DetailsService) ctx.lookup("java:global/classes/DetailsService");
        ItemService itemService = (ItemService) ctx.lookup("java:global/classes/ItemService");
        CustomerService customerService = (CustomerService) ctx.lookup("java:global/classes/CustomerService");
        UserService userService = (UserService) ctx.lookup("java:global/classes/UserService");

        Item item = itemService.all().get(0);
        Customer customer = customerService.all().get(0);
        User user = userService.all().get(0);

        Details details = new Details();
        details.setOrderId(UUID.randomUUID());
        details.setItem(item);
        details.setCustomer(customer);
        details.setUser(user);
        details.setQuantity(555D);
        details.setCreateDate(new Date());
        details.setDetailsType(DetailsType.INCOME);

        detailsService.save(details);
        List<Details> detailsList = detailsService.detailsByType(DetailsType.INCOME);

        Assert.assertNotNull(detailsList);
        Assert.assertTrue(detailsList.size() > 1);
    }

    @Test
    public void testCreate() throws Exception {
        DetailsService detailsService = (DetailsService) ctx.lookup("java:global/classes/DetailsService");
        UserService userService = (UserService) ctx.lookup("java:global/classes/UserService");

        User user = userService.all().get(0);

        DetailsCreateDto detailsCreateDto = StubGenerator.getDetailsCreateDto();

        List<Details> detailsCreated = detailsService.create(detailsCreateDto, user);

        Assert.assertNotNull(detailsCreated);
        Assert.assertNotNull(detailsCreated.get(0).getId());
    }

    @Test
    public void testGetBalance() throws Exception {
        DetailsService detailsService = (DetailsService) ctx.lookup("java:global/classes/DetailsService");
        ItemService itemService = (ItemService) ctx.lookup("java:global/classes/ItemService");
        List<Item> all = itemService.all();


        List<BalanceDto> balance = detailsService.getBalance(all);

        Assert.assertNotNull(balance);
        Assert.assertTrue(balance.size() > 0);
    }
}
