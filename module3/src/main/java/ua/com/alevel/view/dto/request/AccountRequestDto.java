package ua.com.alevel.view.dto.request;

public class AccountRequestDto extends RequestDto {

    private String name;
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AccountRequestDto{" +
                "name='" + name + '\'' +
                ", userId=" + userId +
                '}';
    }
}
