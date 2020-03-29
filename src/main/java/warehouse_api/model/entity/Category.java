package warehouse_api.model.entity;

import javax.persistence.*;

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
}
