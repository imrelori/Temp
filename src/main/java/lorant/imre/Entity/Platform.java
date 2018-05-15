package lorant.imre.Entity;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Platform extends Character
{
    private int stepy;
    private int id,hv,vv,vcount;
    private int rightUpVertex;

    public ArrayList<Character> Platforms;
    Platform plat1 = new Platform();

    private Image platform = new Image("file:C:\\Users\\Lóránt\\Downloads\\platform.png", 70, 20, true, false);
    private Image background = new Image("file:C:\\Users\\Lóránt\\Downloads\\DoodleBackground.png", 600, 600, false, false);

    private final int PLATFORM_WIDTH = (int) platform.getRequestedWidth();
    private final int PLATFORM_HEIGHT = (int) platform.getRequestedHeight();
    private final int CANVAS_WIDTH = (int) background.getRequestedWidth();
    private final int CANVAS_HEIGHT = (int) background.getRequestedHeight();

    private Platform randomPlatform() {
        int platformY = (int) (Math.random() * (CANVAS_HEIGHT - PLATFORM_HEIGHT));
        int platformX = (int) (Math.random() * (CANVAS_WIDTH - PLATFORM_WIDTH - 10));

        Platform plat1 = new Platform(1, platformX, platformY, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        // plat1.setY(platformY);
        //plat1.setX(platformX);
        return plat1;
    }

    public void makeTheFirstTen(){
        for (int i = 0; i < 10; i++) {
        plat1 = randomPlatform();
        /*if (platformCollision(plat1))
            randomPlatform();
        else*/
            Platforms.add(plat1);
        }
    }

    public Platform(int id, int x, int y, int w, int h)
    {
        super(id,x,y,w,h);
        this.Platforms = new ArrayList<>();

        vcount=0;
    }

    public Platform() {
        this.Platforms = new ArrayList<>();
    }

    public void changeStepY(int y)
    {
        stepy += y;
    }

    public int show()
    {
        return id;
    }

    public String toString()
    {
        return x + " " + y;
    }

    public boolean inScene()
    {
        boolean scene = true;
        if(getX() > 600)
            scene = false;
        return scene;
    }



}