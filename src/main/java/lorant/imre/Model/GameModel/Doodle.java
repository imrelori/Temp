package lorant.imre.Model.GameModel;

import lorant.imre.View.Render;

public class Doodle extends Character
{
    private Render renderer = new Render();

    /**
     * Constructor with parameters
     * @param id    the id of the doodle
     * @param x     the x coordinate of doodle
     * @param y     the y coordinate of doodle
     * @param w     the width of the doodle
     * @param h     the height of the doodle
     */
    public Doodle(int id, int x, int y, int w, int h)
    {
        super(id,x,y,w,h);
    }

    /**
     * Provide jumping movement for the doodle
     * @param doodleY       the y coordinate of the doodle
     * @param differenceY   the difference value for changing the y coordinate
     * @return doodleY what is the y coordinate of the doodle
     */
    public float jumping(float doodleY, float differenceY){
        differenceY += 0.2;
        doodleY += differenceY;
        if (doodleY > (renderer.CANVAS_HEIGHT - renderer.DOODLE_HEIGHT))
            differenceY = -10;
        return doodleY;
    }

    /**
     * Provide jumping movement for the doodle
     * @param doodleY       the y coordinate of the doodle
     * @param differenceY   the difference value for changing the y coordinate
     * @return differenceY the difference value for changing the y coordinate
     */
    public float jumpingDifference(float doodleY, float differenceY){
        differenceY += 0.2;
        //doodleY += differenceY;
        if (doodleY > (renderer.CANVAS_HEIGHT - renderer.DOODLE_HEIGHT))
            differenceY = -10;
        return differenceY;
    }

    /**
     * Used to get the doodle x and y coordinates
     *
     * @return The doodle x and y coordinates as a string
     */
    public String toString()
    {
        return "Doodle x: " + x + ", y: "+ y;
    }

}