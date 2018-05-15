package lorant.imre.Model.DatabaseModel.DAO;

import lorant.imre.Model.DatabaseModel.Entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.pmw.tinylog.Logger;

import java.util.List;

public class PlayerDAO implements PlayerDAOInterface<Player, Integer> {

    private Session currentSession;
    private Transaction currentTransaction;

    /**
     * Constructor of the {@code PlayerDAO}
     */
    public PlayerDAO() {
    }

    /**
     * Open the current session
     * @return currentSession
     */
    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    /**
     * Open the current session and begin a transaction
     * @return currentSession
     */
    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    /**
     * Close the current session
     */
    public void closeCurrentSession() {
        currentSession.close();
    }

    /**
     * Close the current session and commit the transaction
     */
    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    /**
     * Configure the annotated class {@link Player} with the hibernate.cfg.xml's help
     * @return SessionFactory
     */
    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure("/hibernate.cfg.xml");
        configuration.addAnnotatedClass(lorant.imre.Model.DatabaseModel.Entity.Player.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }

    /**
     * Used to get the Current session
     * @return Session
     */
    private Session getCurrentSession() {
        return currentSession;
    }

    @Override
    public void persist(Player entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(Player entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public Player findById(Integer id) {
        //return getCurrentSession().get(Player.class, id);
        try {
            return (Player) getCurrentSession().get(
                    "lorant.imre.Model.DatabaseModel.Entity.Player", id);
        } catch (RuntimeException re) {
            Logger.error("Find by id failed", re);
            throw re;
        }
    }

    @Override
    public void delete(Player entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Player> findAll() {
        return (List<Player>) getCurrentSession().createQuery("from Player").list();
    }

    @Override
    public void deleteAll() {
        List<Player> entityList = findAll();
        for (Player entity : entityList) {
            delete(entity);
        }
    }
}
