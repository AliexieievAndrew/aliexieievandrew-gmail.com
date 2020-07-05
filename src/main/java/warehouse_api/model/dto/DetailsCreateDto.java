package warehouse_api.model.dto;

import warehouse_api.model.enums.DetailsType;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class DetailsCreateDto {

    @NotNull
    @Size(min=1, message = "details cannot be empty")
    @Valid
    private List<DetailsDto> details;

    @NotNull
    private DetailsType detailsType;

    @NotNull
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
