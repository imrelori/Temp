package lorant.imre.Model.GameModel;

import lorant.imre.Model.DatabaseModel.Entity.Player;
import lorant.imre.View.Render;

import java.util.ArrayList;

public class Platform extends Character {

    private Render renderer;

    /**
     * Used to get a random platform
     * @return a Platform
     */
    private Platform randomPlatform() {
        renderer = new Render();

        int platformY = (int) (Math.random() * (renderer.CANVAS_HEIGHT - renderer.PLATFORM_HEIGHT));
        int platformX = (int) (Math.random() * (renderer.CANVAS_WIDTH - renderer.PLATFORM_WIDTH));

        return new Platform(1, platformX, platformY, renderer.PLATFORM_WIDTH, renderer.PLATFORM_HEIGHT);
    }

    /**
     * Used to get the first ten platform
     * @param Platforms {@code ArrayList} of {@link Character}
     */
    public void makeTheFirstTen(ArrayList<Character> Platforms){
        Platform plat;
        int n = 0;
        while (n < 10){
            plat = randomPlatform();
            if(!platformCollision(plat, Platforms)) {
                Platforms.add(plat);
                n++;
            }
        }
    }

    /**
     * Constructor with parameters
     * @param id    the id of the platform
     * @param x     the x coordinate of the platform
     * @param y     the y coordinate of the platform
     * @param w     the width if the platform
     * @param h     the height of the platform
     */
    public Platform(int id, int x, int y, int w, int h) {
        super(id,x,y,w,h);
    }

    /**
     * Public constructor of the Platform with no parameters
     */
    public Platform() {
    }

    /**
     * Used to get the platform x and y coordinates
     * @return The platform x and y coordinates as a string
     */
    public String toString()
    {
        return x + " " + y;
    }

    /**
     * Used to decide the platforms are collide or not
     * @param newPlatform   a new platform for comparison
     * @param Platforms     {@code ArrayList} of {@link Character}
     * @return a {@code boolean} to help decide, that the platforms are collide or not
     */
    private boolean platformCollision(Platform newPlatform, ArrayList<Character> Platforms) {
        boolean same = false;
        for (Character savedPlatform : Platforms) {
            if ((newPlatform.getX() + renderer.PLATFORM_WIDTH >= savedPlatform.getX()) &&
                    (newPlatform.getX() <= savedPlatform.getX() + renderer.PLATFORM_WIDTH) &&
                    (newPlatform.getY() + renderer.PLATFORM_HEIGHT >= savedPlatform.getY()) &&
                    (newPlatform.getY() <= savedPlatform.getY() + renderer.PLATFORM_HEIGHT)){
                same = true;
                break;
            }
        }
        return same;
    }

    /**
     * Used to update the platforms coordinates, when they are over the boundary
     * @param doodleY       the y coordinate of the platform
     * @param player        the player who currently play the game
     * @param Platforms     {@code ArrayList} of {@link Character}
     * @param differenceY   the difference value for changing the y coordinate
     * @return a {@code float} which is actually the y coordinate of the platform
     */
    public float updatePlatforms(float doodleY, Player player, ArrayList<Character> Platforms, float differenceY) {
        if (doodleY < renderer.BOUNDARY)
            for (int i = 0; i < 10; i++) {
                doodleY = renderer.BOUNDARY;
                if (i > 0)
                player.updateScoreBy(1);
                Platforms.get(i).setY((int) (Platforms.get(i).getY() - differenceY));
                if (Platforms.get(i).getY() > renderer.CANVAS_HEIGHT) {
                    Platforms.get(i).setY(0);
                    Platforms.get(i).setX((int) (Math.random() * (renderer.CANVAS_WIDTH - renderer.PLATFORM_WIDTH)));
                }
                //if (platformCollision((Platform) Platforms.get(i), Platforms))
                    //updatePlatforms(doodleY, player1, Platforms, differenceY);
            }
        return doodleY;
    }

    /**
     * Used to decide the doodle hit any of the platforms or not
     * @param doodleX       the x coordinate of the platform
     * @param doodleY       the y coordinate of the platform
     * @param differenceY   the difference value for changing the y coordinate
     * @param Platforms     {@code ArrayList} of {@link Character}
     * @param renderer      a {@link Render} object which is provides information about the platform and the doodle
     * @return differenceY, the difference value for changing the y coordinate
     */
    public float platformHitting(int doodleX, float doodleY, float differenceY, ArrayList<Character> Platforms, Render renderer) {
        for (int i = 0; i < 10; i++) {
            if ((doodleX - renderer.DOODLE_WIDTH / 2 + renderer.DOODLE_WIDTH >= Platforms.get(i).getX()) &&
                    (doodleX <= Platforms.get(i).getX() + renderer.PLATFORM_WIDTH) &&
                    (doodleY + renderer.DOODLE_HEIGHT >= Platforms.get(i).getY()) &&
                    (doodleY + renderer.DOODLE_HEIGHT <= Platforms.get(i).getY() + renderer.PLATFORM_HEIGHT) &&
                    (differenceY > 0))
                differenceY = -10;
        }
        return differenceY;
    }

}