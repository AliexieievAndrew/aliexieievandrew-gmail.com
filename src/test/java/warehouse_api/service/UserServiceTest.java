package warehouse_api.service;

import org.junit.Assert;
import org.junit.Test;
import warehouse_api.config.BaseTestConf;
import warehouse_api.model.dto.UserCreateDto;
import warehouse_api.model.entity.User;
import warehouse_api.model.enums.UserRole;
import warehouse_api.repository.UserDao;

import java.util.Date;
import java.util.List;

public class UserServiceTest extends BaseTestConf {

    @Test
    public void testContext() throws Exception{
        UserService userService = (UserService) ctx.lookup("java:global/classes/UserService");
        UserDao userDao = (UserDao) ctx.lookup("java:global/classes/UserDao");

        Assert.assertNotNull(userService);
        Assert.assertNotNull(userDao);
    }

    @Test
    public void testAll() throws Exception {
        UserService userService = (UserService) ctx.lookup("java:global/classes/UserService");

        List<User> all = userService.all();

        Assert.assertNotNull(all);
        Assert.assertTrue(all.size() > 0);
    }

    @Test
    public void testSave() throws Exception {
        String userName = "Test username";

        UserService userService = (UserService) ctx.lookup("java:global/classes/UserService");

        User user = new User();
        user.setUsername(userName);
        user.setCreateDate(new Date());
        user.setUserPassword("test password");
        user.setUserRole(UserRole.MANAGER);

        userService.save(user);

        User userDb = userService.userByName(userName);

        Assert.assertNotNull(userDb);
        Assert.assertTrue(userName.equals(userDb.getUsername()));
    }

    @Test
    public void testCreateUser() throws Exception {
        UserService userService = (UserService) ctx.lookup("java:global/classes/UserService");

        UserCreateDto dto = new UserCreateDto("TestName", "TestPassword", UserRole.MANAGER);
        User user = userService.createUser(dto);

        Assert.assertNotNull(user);
    }
}
