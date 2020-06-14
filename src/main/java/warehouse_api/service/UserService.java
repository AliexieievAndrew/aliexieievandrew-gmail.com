package warehouse_api.service;

import org.mindrot.jbcrypt.BCrypt;
import warehouse_api.model.dto.UserCreateDto;
import warehouse_api.model.entity.User;
import warehouse_api.repository.UserDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;
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

    public User createUser(UserCreateDto createDto) {
        User user = new User();
        user.setUsername(createDto.getName());
        user.setUserPassword(BCrypt.hashpw(createDto.getPassword(), BCrypt.gensalt()));
        user.setUserRole(createDto.getRole());
        user.setCreateDate(new Date());

        return save(user);
    }
}
