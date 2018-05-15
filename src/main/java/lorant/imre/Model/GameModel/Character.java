package lorant.imre.Model.GameModel;

public abstract class Character
{
    protected int y;
    protected int x, id;
    protected int width, height;

    /**
     * public constructor of the Character with no parameters
     */
    public Character()
    {
        x = 0;
        y = 0;
        width = 0;
        height = 0;
        id=0;
    }

    /**
     * Constructor with parameters
     * @param id    the id of the character
     * @param x     the x coordinate of the character
     * @param y     the y coordinate of the character
     * @param w     the width if the character
     * @param h     the height of the character
     */
    public Character(int id, int x, int y, int w, int h)
    {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    /**
     * Used to get the id of the character
     *
     * @return The id of the character
     */
    public int getId()
    {
        return id;
    }

    /**
     * Used to set the id of the character
     *
     * @param i the id of the player
     */
    public void setId(int i)
    {
        id = i;
    }

    /**
     * Used to get the x coordinate of the character
     *
     * @return The x coordinate of the character
     */
    public int getX()
    {
        return x;
    }

    /**
     * Used to get the y coordinate of the character
     *
     * @return The y coordinate of the character
     */
    public int getY()
{
    return y;
}

    /**
     * Used to set the x coordinate of the character
     *
     * @param x The x coordinate of the character
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * Used to set the y coordinate of the character
     *
     * @param y The y coordinate of the character
     */
    public void setY(int y)
    {
        this.y = y;
    }
}