package lorant.imre.View;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import lorant.imre.Model.GameModel.Character;

import java.util.ArrayList;
import java.util.Objects;

public class Render {

    private Group root;
    private GraphicsContext gc;
    public Text scoreText = new Text();
    public Text gameOverText = new Text();

    private Image background = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("images/DoodleBackground.png")).toExternalForm());
    private Image platform = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("images/platform.png")).toExternalForm());
    private Image doodler = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("images/Doodler.png")).toExternalForm());

    public int DOODLE_WIDTH = 70;
    public int DOODLE_HEIGHT = 70;
    public int PLATFORM_WIDTH = 70;
    public int PLATFORM_HEIGHT = 17;
    public int CANVAS_WIDTH = 600;
    public int CANVAS_HEIGHT = 600;
    public int BOUNDARY = 200;

    public Render(Group root, Canvas canvas) {

        this.root = root;
        this.root.getChildren().addAll(scoreText, gameOverText);
        this.gc = canvas.getGraphicsContext2D();
    }

    public Render() {
    }

    public void rendering(int doodleX, float doodleY, ArrayList<Character> Platforms){
        clear();
        background();
        platforms(Platforms);
        doodle(doodleX, doodleY);
    }

    private void clear(){
        gc.clearRect(0, 0, 600, 600);
    }

    private void background(){
        gc.drawImage(background, 0, 0, 600, 600);
    }

    private void platforms(ArrayList<Character> Platforms){
        for (int i = 0; i < 10; i++)
            gc.drawImage(platform, Platforms.get(i).getX(), Platforms.get(i).getY(), 70, 17);
    }

    private void doodle(int doodleX, float doodleY){
        gc.drawImage(doodler, doodleX, doodleY, 70, 70);
    }
}
