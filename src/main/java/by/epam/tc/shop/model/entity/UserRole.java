package by.epam.tc.shop.model.entity;

public enum UserRole {
    ADMIN{
        {
            this.roleId = 0;
            this.roleName = "admin";
        }
    },
    USER{
        {
            this.roleId = 1;
            this.roleName = "user";
        }
    },
    GUEST{
        {
            this.roleId = 2;
            this.roleName = "guest";
        }
    };

    int roleId;
    String roleName;

    UserRole(){}

    public int getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }
}
