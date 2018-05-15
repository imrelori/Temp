package lorant.imre;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lorant.imre.Controller.MenuController;

public class Main extends Application {

    /**
     * Launch a standalone application.
     * @param primaryStage The Stage will show up the application
     * @throws Exception if exception occurred
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("MethJump");

        Parent root = FXMLLoader.load(getClass().getResource("/FXML/menu.fxml"));

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        MenuController.setPrimaryStage(primaryStage);
        primaryStage.show();
    }

    /**
     * Main method for the MethJump application.
     * @param args are not used
     */
    public static void main(String[] args) {
        launch(args);
    }
}
