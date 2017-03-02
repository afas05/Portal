package DBservice.dao;

import DBservice.dataSets.AdminDataSet;
import DBservice.dataSets.UserDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Created by ihor on 01.03.17.
 */
public class AdminDao {
    private Session session;

    public AdminDao(Session session) {this.session = session;}

    public AdminDataSet get(String login) throws HibernateException {
        return (AdminDataSet) session.get(AdminDataSet.class, login);
    }

    public long insertSid(String login, String pass, String sid) throws HibernateException {
        return (Long) session.save(new AdminDataSet(login, pass, sid));
    }
}
