package lorant.imre.Entity;

import javax.persistence.*;

@Entity
@Table(name = "PLAYERS")
public class Player
{
    private Integer id;

    //@Column(name = "NAME")
    private String name;

    //@Column(name = "SCORE")
    private Integer score;

    public Player()
    {
        id = 0;
        name="";
        score=0;
    }

    public Player(Integer i, String n, int s)
    {
        id = i;
        name = n;
        score = s;
    }

    public String getName()
    {
        return name;
    }

    public int getScore()
    {
        return score;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setName(String n)
    {
        this.name = n;
    }

    public void setScore(int s)
    {
        this.score=s;
    }

    public void updateScoreBy(int number){ this.score = score + number < 0 ? 0 : score + number; }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return this.name + ", " + this.score;
    }

}