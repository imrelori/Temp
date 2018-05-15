package lorant.imre.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.pmw.tinylog.Logger;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class control the menu.
 */
public class MenuController implements Initializable {

    private static Stage primaryStage;

    @FXML
    private Button quitButton;

    @FXML
    private TextField playerNameTF;

    /**
     * Initialize this class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Logger.info("MenuController opened.");
    }

    /**
     * Used to get the player name.
     * @return the player name
     */
    private String getPlayerNameTF() {
        return playerNameTF.getText();
    }

    /**
     * Trigger the game-ender button.
     */
    @FXML
    public void quitGame() {
        quitButton.setOnMouseClicked(event -> System.exit(0));
        Logger.info("Quit from the game.");
    }

    public static void setPrimaryStage(Stage primaryStage) {
        MenuController.primaryStage = primaryStage;
    }

    /**
     * Trigger the game-starter button.
     */
    @FXML
    public void startGame() {
        primaryStage.close();
        if (playerNameTF.getText() == null || playerNameTF.getText().trim().isEmpty()) {
            String playerName = getPlayerNameTF();
        }
        new GameController(playerNameTF);
        Logger.info("MenuController closed, GameController started");
    }

}
