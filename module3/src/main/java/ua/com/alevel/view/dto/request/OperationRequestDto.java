package ua.com.alevel.view.dto.request;

import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.Category;
import ua.com.alevel.exception.InvalidInputException;

public class OperationRequestDto extends RequestDto {

    private String categoryName;
    private Double sum;
    private Account account;

    public Double getSum() {
        try {
            return sum;
        } catch (NumberFormatException e) {
            throw new InvalidInputException("сумма должна быть числом");
        }
    }

    public void setSum(Double sum) {
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
