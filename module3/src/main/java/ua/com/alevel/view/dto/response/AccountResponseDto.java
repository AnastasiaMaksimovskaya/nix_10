package ua.com.alevel.view.dto.response;

import ua.com.alevel.entity.Account;

public class AccountResponseDto extends ResponseDto {

    private Integer balance;
    private String name;
    private Long Id;
    private String userEmail;

    public AccountResponseDto() {
    }

    public AccountResponseDto(Account account) {
        setId(account.getId());
        setCreated(account.getCreated());
        this.name = account.getName();
        this.userEmail = account.getUser().getEmail();
        this.balance = account.getBalance();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return Id;
    }

    @Override
    public void setId(Long id) {
        Id = id;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

}
