package DBservice;

import DBservice.dao.OrderDao;
import DBservice.dao.UserDao;
import DBservice.dataSets.OrderDataSet;
import DBservice.dataSets.UserDataSet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
/**
 * Created by Игорь on 02.11.2016.
 */
public class DBservice {
    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "validate";

    private final SessionFactory sessionFactory;

    public DBservice() {
        Configuration configuration = getMysqlConf();
        sessionFactory = createSessionfactory(configuration);
    }

    private Configuration getMysqlConf() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.addAnnotatedClass(OrderDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/dbproject");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "gbrfxe228");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }

    private static SessionFactory createSessionfactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public long getUser(String vk) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            UserDao dao = new UserDao(session);
            long id = dao.getId(vk);
            session.close();
            return id;
        } catch (HibernateException e) {
            return 0;
        }
    }

    public long addUser(String vk) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UserDao dao = new UserDao(session);
            long id = dao.insertUser(vk);
            transaction.commit();
            session.close();
            return id;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public long addOrder(String vk, String data, String signature, String payBy) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            OrderDao dao = new OrderDao(session);
            long id = dao.insertOrder(vk, data, signature, payBy);
            transaction.commit();
            session.close();
            return id;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public OrderDataSet getOrderById(long id)  throws DBException {
        try {
            Session session = sessionFactory.openSession();
            OrderDao dao = new OrderDao(session);
            OrderDataSet orderDataSet = dao.get(id);
            session.close();
            return orderDataSet;
        } catch (HibernateException e) {
            throw  new DBException(e);
        }
    }

    public long getRowCount()  throws DBException {
        try {
            Session session = sessionFactory.openSession();
            OrderDao dao = new OrderDao(session);
            long rows = dao.getRowCount();
            session.close();
            return rows;
        } catch (HibernateException e) {
            throw  new DBException(e);
        }
    }

    public void closeFactory() {
        sessionFactory.close();
    }
}