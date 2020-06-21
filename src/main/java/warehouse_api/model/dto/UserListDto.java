package warehouse_api.model.dto;

import warehouse_api.model.entity.User;
import warehouse_api.model.enums.UserRole;

import java.util.Date;

public class UserListDto {
    private String username;

    private UserRole userRole;

    private Date created;

    public UserListDto() {
    }

    public UserListDto(User user) {
        this.username = user.getUsername();
        this.userRole = user.getUserRole();
        this.created =user.getCreateDate();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
