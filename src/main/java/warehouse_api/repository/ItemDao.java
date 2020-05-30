package warehouse_api.repository;

import warehouse_api.model.entity.Item;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

@Stateless
public class ItemDao extends BaseDao<Item>{

    @PersistenceUnit(unitName = "warehouse_1")
    private EntityManagerFactory entityManagerFactory;

    public ItemDao(Class<Item> entityClass) {
        super(entityClass);
    }

    public ItemDao() {
        super(Item.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public Item userByName(String name) {
        Query query = getEntityManager().createQuery("FROM Item WHERE itemName = :name");
        query.setParameter("name", name);

        return (Item) query.getSingleResult();
    }
}
