package warehouse_api.model.dto;

public class ItemBalanceResponseDto {
    private String itemName;

    private Double quantity;

    public ItemBalanceResponseDto() {
    }

    public ItemBalanceResponseDto(Balance balance) {
        this.itemName = balance.getItem().getItemName();
        this.quantity = balance.getQuantity();
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
}
