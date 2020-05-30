package warehouse_api.service;

import warehouse_api.model.entity.Details;
import warehouse_api.model.enums.DetailsType;
import warehouse_api.repository.DetailsDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class DetailsService {

    @EJB
    private DetailsDao detailsDao;

    public List<Details> all() {
        return detailsDao.findAll();
    }

    public Details save(Details details) {
        return detailsDao.persist(details);
    }

    public List<Details> detailsByType(DetailsType detailsType) {
        return detailsDao.detailsByType(detailsType);
    }
}
