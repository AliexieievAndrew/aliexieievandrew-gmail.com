package warehouse_api.service.security;

import org.mindrot.jbcrypt.BCrypt;
import warehouse_api.model.entity.User;
import warehouse_api.service.UserService;
import warehouse_api.service.exception.BadPasswordException;
import warehouse_api.service.exception.UserNotFoundException;

import javax.ejb.EJB;
import javax.ejb.Singleton;

/**
 * Component for validating user credentials.
 */
@Singleton
public class UserValidationService {

    @EJB
    private UserService userService;

    public User authenticate(String username, String password) throws Exception {
        User user = userService.userByName(username);

        if(user == null) {
            throw new UserNotFoundException(String.format("User %s not found", username));
        }


        if (!BCrypt.checkpw(password, user.getUserPassword())) {
            throw new BadPasswordException("Password is wrong");
        }

        return user;
    }
}
