package lorant.imre.Model.DatabaseModel.DAO;

import java.io.Serializable;
import java.util.List;

public interface PlayerDAOInterface<T, Id extends Serializable> {

    /**
     * Persist the entity
     * @param entity which should be persisted
     */
    void persist(T entity);

    /**
     * Update the entity
     * @param entity the entity which should be updated
     */
    void update(T entity);

    /**
     * Find a {@code T} entity with the given id
     * @param id id of the entity
     * @return {@code T} entity
     */
    T findById(Id id);

    /**
     * Delete the entity
     * @param entity the entity which should be deleted
     */
    void delete(T entity);

    /**
     * Find all entity and return in a list
     * @return {@code List} of {@code T}
     */
    List<T> findAll();

    /**
     * Delete all of the entities
     */
    void deleteAll();

}