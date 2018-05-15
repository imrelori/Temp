package lorant.imre.Service;

import lorant.imre.Model.DatabaseModel.DAO.PlayerDAO;
import lorant.imre.Model.DatabaseModel.Entity.Player;
import org.pmw.tinylog.Logger;

import java.util.List;

public class PlayerService {

    private static PlayerDAO playerDAO;

    /**
     * Public constructor of the Player with no parameters
     */
    public PlayerService() {
        playerDAO = new PlayerDAO();
    }

    /**
     * Persist an entity
     * Open and close the current session with transaction, what is declared in the {@link PlayerDAO}
     * @param entity the entity which should be persisted
     */
    public void persist(Player entity) {
        playerDAO.openCurrentSessionwithTransaction();
        playerDAO.persist(entity);
        playerDAO.closeCurrentSessionwithTransaction();
        Logger.info("Player persisted.");
    }

    /**
     * Update an entity
     * Open and close the current session with transaction, what is declared in the {@link PlayerDAO}
     * @param entity the entity which should be updated
     */
    public void update(Player entity) {
        playerDAO.openCurrentSessionwithTransaction();
        playerDAO.update(entity);
        playerDAO.closeCurrentSessionwithTransaction();
        Logger.info("Player updated.");
    }

    /**
     * Find an entity by id
     * Open and close the current session, what is declared in the {@link PlayerDAO}
     * @param id the id of the entity
     * @return a {@link Player} with the given id
     */
    public Player findById(Integer id) {
        playerDAO.openCurrentSession();
        Player player = playerDAO.findById(id);
        playerDAO.closeCurrentSession();
        Logger.info("Player with id " + id + " found.");
        return player;
    }

    /**
     * Delete an entity
     * Open and close the current session with transaction, what is declared in the {@link PlayerDAO}
     * @param id the id of the entity
     */
    public void delete(Integer id) {
        playerDAO.openCurrentSessionwithTransaction();
        Player player = playerDAO.findById(id);
        playerDAO.delete(player);
        playerDAO.closeCurrentSessionwithTransaction();
        Logger.info("Deleted Player with id" + id + ".");
    }

    /**
     * Used to find all entity
     * Open and close the current session, what is declared in the {@link PlayerDAO}
     * @return a {@code List} of {@link Player}
     */
    public List<Player> findAll() {
        playerDAO.openCurrentSession();
        List<Player> players = playerDAO.findAll();
        playerDAO.closeCurrentSession();
        Logger.info("All Player found.");
        return players;
    }

    /**
     * Delete all entity
     * Open and close the current session with transaction, what is declared in the {@link PlayerDAO}
     */
    public void deleteAll() {
        playerDAO.openCurrentSessionwithTransaction();
        playerDAO.deleteAll();
        playerDAO.closeCurrentSessionwithTransaction();
        Logger.info("All Player deleted.");
    }

    /**
     * Used to get a {@link PlayerDAO}
     * @return a {@link PlayerDAO}
     */
    public PlayerDAO playerDao() {
        return playerDAO;
    }
}
