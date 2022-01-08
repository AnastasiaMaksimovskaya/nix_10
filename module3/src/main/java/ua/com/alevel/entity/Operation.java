package ua.com.alevel.entity;

import javax.persistence.*;

@Entity
@Table(name = "operations")
public class Operation extends BaseEntity{

    @AttributeOverride(name = "id", column = @Column(name = "operation_id"))

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private Long sum;

    public Operation() {
        super();
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "account=" + account +
                ", category=" + category +
                ", sum=" + sum +
                '}';
    }
}
