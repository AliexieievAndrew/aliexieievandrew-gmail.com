package warehouse_api.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import warehouse_api.model.enums.CustomerType;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @SequenceGenerator(allocationSize = 1, sequenceName = "customer_id_seq", name = "customerSeq")
    @GeneratedValue(generator = "customerSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_type")
    @Enumerated(value = EnumType.STRING)
    private CustomerType customerType;

    @Column(name = "customer_address")
    private String customerAddress;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<Details> customerDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Set<Details> getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(Set<Details> customerDetails) {
        this.customerDetails = customerDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerName, customer.customerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerName);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", customerType=" + customerType +
                ", customerAddress='" + customerAddress + '\'' +
                '}';
    }
}
