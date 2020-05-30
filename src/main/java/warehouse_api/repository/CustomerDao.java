package warehouse_api.repository;

import warehouse_api.model.entity.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Transactional(rollbackOn = Exception.class)
@Stateless
public class CustomerDao extends BaseDao<Customer> {

    public CustomerDao(Class<Customer> entityClass) {
        super(entityClass);
    }

    public CustomerDao() {
        super(Customer.class);
    }

    @PersistenceUnit(unitName = "warehouse_1")
    private EntityManagerFactory entityManagerFactory;

    @Override
    protected EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public Customer customerByName(String name) {
        Query query = getEntityManager().createQuery("FROM Customer WHERE customerName = :name");
        query.setParameter("name", name);

        return (Customer) query.getSingleResult();
    }
}
