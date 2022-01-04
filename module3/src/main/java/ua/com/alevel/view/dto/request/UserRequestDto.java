package ua.com.alevel.view.dto.request;

import java.util.Set;

public class UserRequestDto extends RequestDto {

    private String name;

    private String email;

    private Set<Long> accId;

    public Set<Long> getAccId() {
        return accId;
    }

    public void setAccId(Set<Long> accId) {
        this.accId = accId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
