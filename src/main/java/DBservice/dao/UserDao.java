package DBservice.dao;

import DBservice.dataSets.UserDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
/**
 * Created by Игорь on 02.11.2016.
 */
public class UserDao {
    private Session session;

    public UserDao(Session session) {
        this.session = session;
    }

    public long insertUser(String name) throws HibernateException {
        return (Long) session.save(new UserDataSet(name));
    }

    public long getId(String vk) throws HibernateException {
        Criteria criteria = session.createCriteria(UserDataSet.class);
        return ((UserDataSet) criteria.add(Restrictions.eq("vk", vk)).uniqueResult()).getId();
    }
}
