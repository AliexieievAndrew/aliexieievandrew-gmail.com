package warehouse_api.repository;

import warehouse_api.model.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Transactional(rollbackOn = Exception.class)
@Stateless
public class UserDao extends BaseDao<User>{

    @PersistenceUnit(unitName = "warehouse_1")
    private EntityManagerFactory entityManagerFactory;

    public UserDao(Class<User> entityClass) {
        super(entityClass);
    }

    public UserDao() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public User userByName(String name) {
        Query query = getEntityManager().createQuery("FROM User WHERE username = :name");
        query.setParameter("name", name);

        return (User) query.getResultList().stream().findFirst().orElse(null);
    }
}
