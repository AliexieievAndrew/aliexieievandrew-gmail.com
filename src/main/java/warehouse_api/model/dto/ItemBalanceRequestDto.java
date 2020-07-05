package warehouse_api.model.dto;

import javax.validation.constraints.Size;
import java.util.List;

public class ItemBalanceRequestDto {

    @Size(min = 1, message = "Items cannot be empty")
    private List<String> itemNameList;

    public List<String> getItemNameList() {
        return itemNameList;
    }

    public void setItemNameList(List<String> itemNameList) {
        this.itemNameList = itemNameList;
    }
}
