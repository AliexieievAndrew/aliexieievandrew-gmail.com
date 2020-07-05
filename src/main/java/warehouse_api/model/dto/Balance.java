package warehouse_api.model.dto;

import warehouse_api.model.entity.Item;

public class Balance {

    private Item item;

    private Double quantity;

    public Balance() {
    }

    public Balance(Item item, Double quantity) {
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
