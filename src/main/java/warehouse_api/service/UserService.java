package warehouse_api.service;

import warehouse_api.model.entity.User;
import warehouse_api.repository.UserDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserService {

    @EJB
    private UserDao userDao;

    public List<User> all() {
        return userDao.findAll();
    }

    public User save(User user) {
        return userDao.persist(user);
    }

    public User userByName(String name) {
        return userDao.userByName(name);
    }
}
