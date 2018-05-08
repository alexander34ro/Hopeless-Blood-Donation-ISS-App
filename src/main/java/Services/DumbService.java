package Services;

import Repositories.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class DumbService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public <T> T save(final T o) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.getTransaction();
            transaction.begin();

            T e = (T) session.save(o);

            transaction.commit();
            return e;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public void delete(final Object o) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.getTransaction();
            transaction.begin();

            session.delete(o);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public <T> T get(final Class<T> type, final short id) {
        return (T) sessionFactory.openSession().get(type, id);
    }

    public <T> T merge(final T o) {
        return (T) sessionFactory.openSession().merge(o);
    }

    public <T> void saveOrUpdate(final T o) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.getTransaction();
            transaction.begin();

            session.saveOrUpdate(o);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public <T> List<T> getAll(final Class<T> type) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            final Criteria crit = session.createCriteria(type);
            return crit.list();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
}