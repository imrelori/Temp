package lorant.imre.Controller;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import lorant.imre.Model.DatabaseModel.Entity.Player;
import lorant.imre.Model.GameModel.Character;
import lorant.imre.Model.GameModel.Doodle;
import lorant.imre.Model.GameModel.Platform;
import lorant.imre.Service.PlayerService;
import lorant.imre.View.Menu;
import lorant.imre.View.Render;
import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameController implements Serializable {

    public static Stage primaryStage;
    private static Group root;
    private AnimationTimer mainAnimationLoop;
    private Render renderer;
    private Player player;
    private Doodle doodle = new Doodle(1, 100, 100, 100, 70);
    private TextField playerName;

    private int doodleX = doodle.getX();
    private float doodleY = doodle.getY();
    private float differenceY = 0;
    private boolean paused = false;

    private Platform platform;
    private Menu.GameMenu gameMenu;

    private ArrayList<String> input;
    public ArrayList<Character> Platforms;

    private PlayerService playerService;

    /**
     * This class controlling the game
     */
    public GameController(TextField playerName) {

        primaryStage = new Stage();
        primaryStage.setTitle("MethJump");

        root = new Group();
        root.prefHeight( 600);
        root.prefWidth(600);

        gameMenu = new Menu.GameMenu();
        gameMenu.setVisible(false);

        Canvas canvas = new Canvas(600, 600);
        root.getChildren().addAll(canvas, gameMenu);

        renderer = new Render(root, canvas);
        renderer.scoreText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        renderer.gameOverText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 36));
        renderer.gameOverText.setX(170);
        renderer.gameOverText.setY(150);

        Scene scene = new Scene(root);

        input = new ArrayList<>();
        Platforms = new ArrayList<>();
        platform = new Platform();

        platform.makeTheFirstTen(Platforms);

        player = new Player();
        runKeyListeners();
        this.playerName = playerName;

        playerService = new PlayerService();

        animationLoop();
        Logger.info("The Game Is On.");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This method provides getting back to the main menu
     */
    public void goToMenu() {

        primaryStage.close();

        AnchorPane root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/menu.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            Logger.error("Unable to load the fxml file.");
        }
        Stage pstage = new Stage();
        Scene scene = new Scene(Objects.requireNonNull(root), 600, 600);
        pstage.setScene(scene);
        MenuController.setPrimaryStage(pstage);
        pstage.show();
    }

    /**
     * Trigger actions when a button pressed or released
     * Save and remove the input key codes
     * Handle the game pausing when pressed P
     */
    private void runKeyListeners() {

        root.getScene().setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if (!input.contains(code))
                        input.add(code);
                    if (input.contains("P")) {
                        paused = !paused;
                        renderer.gameOverText.setText(String.valueOf("PAUSED"));
                        renderer.gameOverText.setVisible(paused);
                    }
                });

        root.getScene().setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove(code);
                });

        primaryStage.setOnCloseRequest(
                e -> {
                    mainAnimationLoop.stop();
                    goToMenu();
                });
        Logger.info("Storing the input.");
    }

    /**
     * Here are the {@link Render} class used mainly
     * Here are handled all the methods which provide the characters movements,
     * when end the game and what should the program do when the game is over
     */
    private void animationLoop() {

        mainAnimationLoop = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {

                if (!paused) {
                    doodleY = doodle.jumping(doodleY, differenceY);
                    differenceY = doodle.jumpingDifference(doodleY, differenceY);
                    doodleY = platform.updatePlatforms(doodleY, player, Platforms, differenceY);
                    differenceY = platform.platformHitting(doodleX, doodleY, differenceY, Platforms, renderer);

                    renderer.scoreText.setText(String.valueOf("\nScore:\n" + player.getScore() / 10));

                    if (input.contains("LEFT"))
                        doodleX -= 6;
                    if (input.contains("RIGHT"))
                        doodleX += 6;
                    if (doodleX > 600)
                        doodleX = 0;
                    else if (doodleX < -60)
                        doodleX = 600;
                }

                if (doodleY + renderer.DOODLE_HEIGHT >= renderer.CANVAS_HEIGHT) {

                    stop();
                    Logger.info("The Game Is Over.");
                    player.setScore(player.getScore() / 10);
                    player.setName(String.valueOf(playerName.getText()));
                    renderer.gameOverText.setText(String.valueOf("GAME OVER"));
                    renderer.gameOverText.setVisible(true);

                    if (!gameMenu.isVisible()) {
                        FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
                        ft.setFromValue(0);
                        ft.setToValue(1);

                        gameMenu.setVisible(true);
                        ft.play();
                    } else {
                        FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
                        ft.setFromValue(1);
                        ft.setToValue(0);
                        ft.setOnFinished(evt -> gameMenu.setVisible(false));
                        ft.play();
                    }

                    playerService.persist(player);

                    List<Player> players = playerService.findAll();

                    if (players.size() > 10) {
                        playerService.delete(players.get(0).getId());
                    }
                }
                renderer.rendering(doodleX, doodleY, Platforms);
            }
        };
        mainAnimationLoop.start();
    }
}