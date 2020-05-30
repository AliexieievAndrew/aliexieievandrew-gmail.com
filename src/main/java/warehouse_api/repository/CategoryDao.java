package warehouse_api.repository;

import warehouse_api.model.entity.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Transactional (rollbackOn = Exception.class)
@Stateless
public class CategoryDao extends BaseDao<Category>{

    @PersistenceUnit(unitName = "warehouse_1")
    private EntityManagerFactory entityManagerFactory;

    public CategoryDao(Class<Category> entityClass) {
        super(entityClass);
    }

    public CategoryDao() {
        super(Category.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public Category categoryByName(String name) {
        Query query = getEntityManager().createQuery("FROM Category WHERE categoryName = :name");
        query.setParameter("name", name);

        return (Category) query.getSingleResult();
    }
}
