package lorant.imre.Service;

import lorant.imre.Model.DatabaseModel.Entity.Player;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

public class HibernateCRUDTest {

    private PlayerService playerService;
    private Player player;

    @Test
    public void testGetAll() {
        playerService = new PlayerService();
        List<Player> players = playerService.findAll();
        assertEquals(0, players.size());
    }

    @Test
    public void testPersist() {
        player = new Player("Skyler", 10);
        playerService = new PlayerService();
        playerService.persist(player);

        List<Player> players = playerService.findAll();

        assertNotNull(players);
        assertEquals(1, players.size());
        assertEquals("Skyler", players.get(0).getName());
        assertEquals(10, players.get(0).getScore());

        playerService.delete(players.get(0).getId());
    }

    @Test
    public void testFindById() {
        player = new Player("Hank", 11);
        playerService = new PlayerService();
        playerService.persist(player);

        List<Player> players = playerService.findAll();
        Player returnedPlayer = playerService.findById(players.get(0).getId());

        assertNotNull(returnedPlayer);
        assertEquals("Hank", returnedPlayer.getName());
        assertEquals(11, returnedPlayer.getScore());
    }

    @Test
    public void testUpdate() {
        player = new Player();
        playerService = new PlayerService();

        playerService.persist(player);

        List<Player> players1 = playerService.findAll();

        playerService.update(new Player(players1.get(0).getId(),"Walter Jr.", 123));

        List<Player> players2 = playerService.findAll();

        assertEquals(players1.get(0).getId(), players2.get(0).getId());
        assertNotSame(players1.get(0).getName(),players2.get(0).getName());
        assertEquals("Walter Jr.", players2.get(0).getName());
        assertEquals(123, players2.get(0).getScore());

        playerService.delete(players1.get(0).getId());
    }

    @Test
    public void testDelete() {
        player = new Player();
        playerService = new PlayerService();

        playerService.persist(player);
        List<Player> players = playerService.findAll();
        assertEquals(1, players.size());

        playerService.delete(players.get(0).getId());
        players = playerService.findAll();
        assertEquals(0, players.size());
    }


    @Test
    public void testDeleteAll(){
        player = new Player();
        playerService = new PlayerService();
        playerService.deleteAll();

        List<Player> players = playerService.findAll();
        assertEquals(0, players.size());
    }

}