package warehouse_api.model.dto;

import warehouse_api.model.enums.UserRole;

public class UserCreateDto {

    private String name;

    private String password;

    private UserRole role;

    public UserCreateDto() {
    }

    public UserCreateDto(String name, String password, UserRole role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
