package DBservice.dao;

import DBservice.dataSets.OrderDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
/**
 * Created by Игорь on 03.11.2016.
 */
public class OrderDao {
    private Session session;

    public OrderDao(Session session) {
        this.session = session;
    }

    public long insertOrder(String vk, String data, String signature, String payBy) throws HibernateException {
        return (Long) session.save(new OrderDataSet(vk, data, signature, payBy));
    }

    public OrderDataSet get(long id) throws HibernateException {
        return (OrderDataSet) session.get(OrderDataSet.class, id);
    }

    public OrderDataSet getByData(String signature) throws HibernateException {
        Criteria criteria = session.createCriteria(OrderDataSet.class);
        return ((OrderDataSet) criteria.add(Restrictions.eq("signature", signature)).uniqueResult());
    }

    public long getRowCount() throws HibernateException {
        return ( (Long) session.createQuery("select count(*) from OrderDataSet").iterate().next() ).intValue();
    }
}
