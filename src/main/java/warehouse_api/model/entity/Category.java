package warehouse_api.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @SequenceGenerator(allocationSize = 1, sequenceName = "category_id_seq", name = "categorySeq")
    @GeneratedValue(generator = "categorySeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column (name = "category_name")
    private String categoryName;

    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Item> itemCreator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Item> getItemCreator() {
        return itemCreator;
    }

    public void setItemCreator(Set<Item> itemCreator) {
        this.itemCreator = itemCreator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(categoryName, category.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryName);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
