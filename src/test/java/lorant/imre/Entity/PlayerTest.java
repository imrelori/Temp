package lorant.imre.Entity;

import lorant.imre.Model.DatabaseModel.Entity.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    private Player player;

    @Before
    public void setUp() {
        player = new Player();

        player.setId(1);
        player.setName("Heisenberg");
        player.setScore(666);
    }

    @Test
    public void testGetId() {
        assertEquals(1, player.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("Heisenberg", player.getName());
    }

    @Test
    public void testGetScore() {
        assertEquals(666, player.getScore());
    }

    @Test
    public void testToString() {
        assertEquals("Player: " + 1 + ", Heisenberg, " + 666, player.toString());
    }

    @Test
    public void testUpdateScoreBy(){
        Assert.assertEquals(700, player.updateScoreBy(34));
    }

}
