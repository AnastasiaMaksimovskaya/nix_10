package ua.com.alevel.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "accounts")
public class Account extends BaseEntity{

    @AttributeOverride(name = "id", column = @Column(name = "account_id"))

    private Long balance;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "account",cascade = {
            CascadeType.REMOVE
    })
    private Set<Operation> operations;

    public Account() {
        super();
        this.operations = new HashSet<>();
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Operation> getOperations() {
        return operations;
    }

    public void setOperations(Set<Operation> operations) {
        this.operations = operations;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", name='" + name + '\'' +
                '}';
    }
}
