package anningtex.view;

import anningtex.manger.api.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @Author Song
 * @Desc:登录
 * @Date：2021-10-30
 */
public class LoginMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(Constants.LOGIN_VIEW_PATH));
        primaryStage.setTitle("TestCommon");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}