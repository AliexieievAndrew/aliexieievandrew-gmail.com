package warehouse_api.service;

import org.junit.Assert;
import org.junit.Test;
import warehouse_api.config.BaseTest;
import warehouse_api.model.entity.Customer;
import warehouse_api.model.enums.CustomerType;
import warehouse_api.repository.CustomerDao;

import java.util.List;

public class CustomerServiceTest extends BaseTest {

    @Test
    public void testContext() throws Exception{
        CustomerService customerService = (CustomerService) ctx.lookup("java:global/classes/CustomerService");
        CustomerDao customerDao = (CustomerDao) ctx.lookup("java:global/classes/CustomerDao");

        Assert.assertNotNull(customerService);
        Assert.assertNotNull(customerDao);
    }

    @Test
    public void testAll() throws Exception {
        CustomerService customerService = (CustomerService) ctx.lookup("java:global/classes/CustomerService");

        List<Customer> all = customerService.all();

        Assert.assertNotNull(all);
        Assert.assertTrue(all.size() > 0);
    }

    @Test
    public void testSave() throws Exception {
        String customerName = "Test username";

        CustomerService customerService = (CustomerService) ctx.lookup("java:global/classes/CustomerService");

        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setCustomerType(CustomerType.PARTNER);
        customer.setCustomerAddress("test password");

        customerService.save(customer);

        Customer customerDb = customerService.userByName(customerName);

        Assert.assertNotNull(customerDb);
        Assert.assertTrue(customerName.equals(customerDb.getCustomerName()));
    }
}
