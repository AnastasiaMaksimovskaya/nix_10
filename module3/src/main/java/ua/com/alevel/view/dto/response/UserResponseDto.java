package ua.com.alevel.view.dto.response;

import ua.com.alevel.entity.User;

public class UserResponseDto extends ResponseDto {

    private Integer accountCount;

    private String email;

    public UserResponseDto(User user) {
        setEmail(user.getEmail());
        setId(user.getId());
        setName(user.getName());
        this.accountCount = user.getAccounts().size();
    }

    public UserResponseDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAccountCount() {
        return accountCount;
    }

    public void setAccountCount(Integer accountCount) {
        this.accountCount = accountCount;
    }

    @Override
    public String toString() {
        return "UserResponseDto{" +
                "accountCount=" + accountCount +
                ", email='" + email + '\'' +
                '}';
    }
}
