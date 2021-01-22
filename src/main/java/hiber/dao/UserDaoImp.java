package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<Car> getUsersByCar(Car car) {

      String hql = "FROM Car car LEFT OUTER JOIN FETCH car.user WHERE car.model =:m AND car.series =:s";

      Query query=sessionFactory.getCurrentSession().createQuery(hql);

      query.setParameter("m", car.getModel());
      query.setParameter("s", car.getSeries());

      return query.getResultList();
   }

}
