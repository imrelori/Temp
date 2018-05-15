package lorant.imre.Entity;

public class Doodle extends Character
{
    public float doodleY = getY();
    public int doodleX = getX();
    public float differenceY;
    private final int CANVAS_HEIGHT = 600;
    private final int DOODLE_HEIGHT = 90;
    //Tuple coordinates = new Tuple(doodleY, differenceY);

    public Doodle(int id, int x, int y, int w, int h)
    {
        super(id,x,y,w,h);
        differenceY = 0;
    }

    public float jumping(){
        differenceY += 0.2;
        doodleY += differenceY;
        if (doodleY > (CANVAS_HEIGHT - DOODLE_HEIGHT))
            differenceY = -10;
        return doodleY;
    }

    public boolean checkHitPlatform(Object obj)
    {
        Platform other = (Platform) obj;

        if(getX()+getWidth() >= other.getX()&&
                getX()<= other.getX()+other.getWidth() &&
                getY()+getHeight() >= other.getY() &&
                getY()+getHeight() <= other.getY()+other.getHeight())
            return true;
        return false;
    }

    public String toString()
    {
        return "Doodle x: "+x+"|y: "+y;
    }

}