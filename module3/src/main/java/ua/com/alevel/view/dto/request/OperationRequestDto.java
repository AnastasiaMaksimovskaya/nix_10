package ua.com.alevel.view.dto.request;

import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.Category;

public class OperationRequestDto extends RequestDto{

    private String categoryName;
    private int sum;
    private Account account;

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "OperationRequestDto{" +
                "categoryName='" + categoryName + '\'' +
                ", sum=" + sum +
                ", account=" + account +
                '}';
    }
}
