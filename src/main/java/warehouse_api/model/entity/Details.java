package warehouse_api.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import warehouse_api.model.enums.DetailsType;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "details")
public class Details {

    @Id
    @SequenceGenerator(allocationSize = 1, sequenceName = "details_id_seq", name = "detailsSeq")
    @GeneratedValue(generator = "detailsSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "details_type")
    @Enumerated(value = EnumType.STRING)
    private DetailsType detailsType;

    @Column(name = "create_date")
    private Date createDate;


    @Column(name = "user_id", insertable=false, updatable=false)
    @JsonIgnore
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private User user;

    @Column(name = "customer_id", insertable=false, updatable=false)
    @JsonIgnore
    private Long customerId;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Customer customer;

    @Column(name = "item_id", insertable=false, updatable=false)
    @JsonIgnore
    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Item item;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "additional_info")
    private String additionalInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DetailsType getDetailsType() {
        return detailsType;
    }

    public void setDetailsType(DetailsType detailsType) {
        this.detailsType = detailsType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Details details = (Details) o;
        return Objects.equals(id, details.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Details{" +
                "id=" + id +
                ", detailsType=" + detailsType +
                ", createDate=" + createDate +
                ", user=" + user +
                ", customer=" + customer +
                ", item=" + item +
                ", quantity=" + quantity +
                ", additionalInfo='" + additionalInfo + '\'' +
                '}';
    }
}
