package warehouse_api.repository;

import warehouse_api.model.entity.Item;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.util.List;

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

    public Item itemByName(String name) {
        Query query = getEntityManager().createQuery("FROM Item WHERE itemName = :name");
        query.setParameter("name", name);

        return (Item) query.getResultList().stream().findFirst().orElse(null);
    }

    public List<Item> itemsByNames(List<String> names) {
        Query query = getEntityManager().createQuery("FROM Item WHERE itemName IN :names");
        query.setParameter("names", names);

        return (List<Item>) query.getResultList();
    }
}
