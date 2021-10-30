package anningtex.view;

import anningtex.manger.api.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @Author Song
 * @Desc:
 * @Date：2021-10-30
 */
public class TwoMenuMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(Constants.TWO_MENU_VIEW_PATH));
        primaryStage.setTitle("Two Menu");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
