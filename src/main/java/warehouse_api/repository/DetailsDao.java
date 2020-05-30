package warehouse_api.repository;

import warehouse_api.model.entity.Details;
import warehouse_api.model.enums.DetailsType;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class DetailsDao extends BaseDao<Details> {

    @PersistenceUnit(unitName = "warehouse_1")
    private EntityManagerFactory entityManagerFactory;

    public DetailsDao(Class<Details> entityClass) {
        super(entityClass);
    }

    public DetailsDao() {
        super(Details.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public List<Details> detailsByType(DetailsType detailsType) {
        Query query = getEntityManager().createQuery("FROM Details WHERE detailsType = :detailsType");
        query.setParameter("detailsType", detailsType);

        return (List<Details>) query.getResultList();
    }
}
