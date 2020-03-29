package test;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManagerFactory;
import org.hibernate.query.NativeQuery;
import warehouse_api.model.entity.Category;
import warehouse_api.repository.CategoryDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
public class ServiceTest {

    @PersistenceUnit(unitName = "warehouse_1")
    private EntityManagerFactory entityManagerFactory;

    @EJB
    private CategoryDao categoryDao;

    public List<Category> test3() {
        return categoryDao.findAll();
    }

    public void test() {


        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // method1
        Session session = ((Session) entityManager.getDelegate()).getSession();

        // method2
        Session unwrap = entityManager.unwrap(Session.class);

        Criteria criteria = session.createCriteria(Category.class);
        List<Category> list = criteria.list();

        System.out.println("HELLO JPA");
        System.out.println("Category_name = " + list.get(0).getCategoryName());

    }

    public void saveUser(Category user){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
        entityManager.persist(user);
//        entityManager.getTransaction().commit();

    }

    private Session getSession() {
        return ((HibernateEntityManagerFactory) entityManagerFactory).getSessionFactory().openSession();
    }
}
