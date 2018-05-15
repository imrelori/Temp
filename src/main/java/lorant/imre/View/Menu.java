package lorant.imre.View;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lorant.imre.Controller.GameController;
import lorant.imre.Controller.MenuController;

import java.io.IOException;
import java.util.Objects;

public class Menu {

    public Menu() {
    }

    public static class GameMenu extends Parent {

        public GameMenu() {
            VBox menu0 = new VBox(10);

            menu0.setTranslateX(100);
            menu0.setTranslateY(200);

            Menu.MenuButton btnResume = new Menu.MenuButton("Go to main menu");
            btnResume.setOnMouseClicked(event -> {
                GameController.primaryStage.close();

                AnchorPane root = null;
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/menu.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage pstage = new Stage();
                pstage.setScene(new Scene(root));
                MenuController.setPrimaryStage(pstage);
                pstage.show();
            });

            Menu.MenuButton btnExit = new Menu.MenuButton("EXIT");
            btnExit.setOnMouseClicked(event -> {
                System.exit(0);
            });

            menu0.getChildren().addAll(btnResume, btnExit);

            Rectangle bg = new Rectangle(800, 600);
            bg.setFill(Color.GREY);
            bg.setOpacity(0.4);

            getChildren().addAll(bg, menu0);
        }
    }

    public static class MenuButton extends StackPane {
        private Text text;

        public MenuButton(String name) {
            text = new Text(name);
            text.setFont(text.getFont().font(20));
            text.setFill(Color.WHITE);

            Rectangle bg = new Rectangle(250, 30);
            bg.setOpacity(0.6);
            bg.setFill(Color.DODGERBLUE);
            bg.setEffect(new GaussianBlur(3.5));

            setAlignment(Pos.CENTER_LEFT);
            setRotate(-0.5);
            getChildren().addAll(bg, text);

            setOnMouseEntered(event -> {
                bg.setTranslateX(10);
                text.setTranslateX(10);
                bg.setFill(Color.WHITE);
                text.setFill(Color.DODGERBLUE);
            });

            setOnMouseExited(event -> {
                bg.setTranslateX(0);
                text.setTranslateX(0);
                bg.setFill(Color.DODGERBLUE);
                text.setFill(Color.WHITE);
            });

            DropShadow drop = new DropShadow(50, Color.WHITE);
            drop.setInput(new Glow());

            setOnMousePressed(event -> setEffect(drop));
            setOnMouseReleased(event -> setEffect(null));
        }
    }

}

