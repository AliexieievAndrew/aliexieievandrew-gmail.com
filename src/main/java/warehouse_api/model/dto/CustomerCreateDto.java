package warehouse_api.model.dto;

import warehouse_api.model.enums.CustomerType;

import javax.validation.constraints.NotNull;

public class CustomerCreateDto {

    @NotNull
    private String customerName;

    @NotNull
    private CustomerType customerType;

    @NotNull
    private String customerAddress;

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
}
