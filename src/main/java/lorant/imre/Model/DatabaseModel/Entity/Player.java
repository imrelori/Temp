package lorant.imre.Model.DatabaseModel.Entity;

import javax.persistence.*;

@Entity
@Table(name = "PLAYERS")
public class Player
{
    /**
     * Id value of a Player
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    /**
     * Name value of a Player
     */
    @Column(name = "NAME", length = 200)
    private String name;

    /**
     * Score value of a Player
     */
    @Column(name = "SCORE")
    private int score;

    /**
     * public constructor of the Player with no parameters
     */
    public Player()
    {
        this.id = 0;
        this.name = "";
        this.score = 0;
    }

    /**
     * Constructor with parameters
     * @param id        the id of the player
     * @param name      the name of the player
     * @param score     the score of the player
     *
     */
    public Player(Integer id, String name, int score)
    {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    /**
     * Constructor with parameters
     * @param name      the name of the player
     * @param score     the score of the player
     *
     */
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Used to get the name of the player
     *
     * @return The name of the player
     */
    public String getName()
    {
        return name;
    }

    /**
     * Used to get the score of the player
     *
     * @return The score of the player
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Used to get the id of the player
     *
     * @return The id of the player
     */
    public int getId() {
        return id;
    }

    /**
     * Used to set the name of the player.
     *
     * @param name the {@code string} to be set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Used to set the score value of the player.
     *
     * @param score the {@code int} to be set
     */
    public void setScore(int score)
    {
        this.score=score;
    }

    /**
     * Used to set the id of the player.
     *
     * @param id the {@code Integer} to be set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /***
     * Change the value of the score
     *
     * @param number the value of score incrementation
     */
    public int updateScoreBy(int number){
        this.score = score + number < 0 ? 0 : score + number;
        return score;
    }

    /**
     * Used to get the player id, name and score
     *
     * @return The player id, name and score as a string
     */
    public String toString()
    {
        return "Player: " + this.id + ", " + this.name + ", " + this.score;
    }

}