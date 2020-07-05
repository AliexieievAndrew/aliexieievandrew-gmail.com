package warehouse_api.model.dto;

public class DetailsDto {

    private String itemName;

    private Double quantity;

    private String additionalInfo;

    public DetailsDto() {
    }

    public DetailsDto(String itemName, Double quantity, String additionalInfo) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.additionalInfo = additionalInfo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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
}
