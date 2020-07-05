package warehouse_api.model.dto;

import warehouse_api.model.entity.Item;

public class BalanceDto {

    private Item item;

    private Double quantity;

    public BalanceDto() {
    }

    public BalanceDto(Item item, Double quantity) {
        this.item = item;
        this.quantity = quantity;
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
}
