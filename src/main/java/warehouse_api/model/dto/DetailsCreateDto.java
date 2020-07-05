package warehouse_api.model.dto;

import warehouse_api.model.enums.DetailsType;

import java.util.List;

public class DetailsCreateDto {

    private List<DetailsDto> details;

    private DetailsType detailsType;

    private String customerName;

    public DetailsCreateDto() {
    }

    public DetailsCreateDto(List<DetailsDto> details, DetailsType detailsType, String customerName) {
        this.details = details;
        this.detailsType = detailsType;
        this.customerName = customerName;
    }

    public List<DetailsDto> getDetails() {
        return details;
    }

    public void setDetails(List<DetailsDto> details) {
        this.details = details;
    }

    public DetailsType getDetailsType() {
        return detailsType;
    }

    public void setDetailsType(DetailsType detailsType) {
        this.detailsType = detailsType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
