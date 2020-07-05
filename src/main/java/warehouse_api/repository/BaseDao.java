package warehouse_api.repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

public abstract class BaseDao<T> {

    private Class<T> entityClass;

    public BaseDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public T find(Long id) {
        return getEntityManager().find(entityClass, id);
    }

    public T persist(T entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    @Transactional(rollbackOn = Exception.class)
    public List<T> persist(List<T> entities) {
        entities.forEach(e -> getEntityManager().persist(e));

        return entities;
    }

    public T edit (T entity) {
        getEntityManager().merge(entity);
        return entity;
    }

    public T remove (T entity) {
        getEntityManager().remove(entity);
        return entity;
    }

    public List<T> findAll() {
        Query q = getEntityManager().createQuery("SELECT e FROM " + entityClass.getName() + " e");
        List<T> list = (List<T>) q.getResultList();
        return list;
    }
}
