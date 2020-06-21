package warehouse_api.model.dto;

public class CategoryCreateDto {

    private String categoryName;

    public CategoryCreateDto() {
    }

    public CategoryCreateDto(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
