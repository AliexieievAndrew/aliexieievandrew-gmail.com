package warehouse_api.model.dto;

public class ItemCreateDto {

    private String name;

    private String categoryName;

    public ItemCreateDto() {
    }

    public ItemCreateDto(String name, String categoryName) {
        this.name = name;
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
