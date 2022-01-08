package ua.com.alevel.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @AttributeOverride(name = "id", column = @Column(name = "category_id"))

    public enum Name {
        refill("-"),
        transfer_to_card("+"),
        transfer_from_the_card("-"),
        buying_food("-"),
        salary("+"),
        social_charges("+");

        private String isIncome;

        Name(String isIncome) {
            this.isIncome = isIncome;
        }

        public String getIsIncome() {
            return isIncome;
        }
    }

    @Enumerated(EnumType.STRING)
    private Name name;

    @Column(name = "is_income")
    private Boolean isIncome;

    @OneToMany(mappedBy = "category")
    private Set<Operation> operations;

    public Category() {
        super();
        this.operations = new HashSet<>();
    }

    public Set<Operation> getOperations() {
        return operations;
    }

    public void setOperations(Set<Operation> operations) {
        this.operations = operations;
    }

    public Boolean getIncome() {
        return isIncome;
    }

    public void setIncome(Boolean income) {
        isIncome = income;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name=" + name +
                ", isIncome=" + isIncome +
                '}';
    }
}
