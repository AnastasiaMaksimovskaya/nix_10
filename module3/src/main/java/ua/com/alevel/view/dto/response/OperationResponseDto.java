package ua.com.alevel.view.dto.response;

import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.Operation;

public class OperationResponseDto extends ResponseDto {
    private String categoryName;
    private String isIncome;
    private int sum;
    private String accountName;

    public OperationResponseDto(Operation operation) {
        setId(operation.getId());
        setCreated(operation.getCreated());
        this.categoryName = operation.getCategory().getName().name();
        this.isIncome = operation.getCategory().getIncome() ? "+" : "-";
        this.sum = operation.getSum();
        System.out.println("OperationResponseDto.OperationResponseDto");
        System.out.println("operation = " + operation);
        this.accountName = operation.getAccount().getName();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getIsIncome() {
        return isIncome;
    }

    public void setIsIncome(String isIncome) {
        this.isIncome = isIncome;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
