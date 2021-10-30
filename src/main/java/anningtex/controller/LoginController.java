package anningtex.controller;

import anningtex.manger.api.BaseResponse;
import anningtex.manger.api.Constants;
import anningtex.manger.http.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @Author Song
 * @Desc:登录
 * @Date：2021-10-30
 */
public class LoginController {
    @FXML
    TextField userName;
    @FXML
    PasswordField password;

    @FXML
    void login() {
        String strUserName = userName.getText();
        String strPassword = password.getText();
        HttpUtils.login(strUserName, strPassword, paramResponse -> {
            System.out.println("response: " + paramResponse);
            BaseResponse<Object> response = new Gson().fromJson(paramResponse, new TypeToken<BaseResponse<Object>>() {
            }.getType());
            if (response.getCode() == 1) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource(Constants.MAIN_VIEW_PATH));
                    ((Stage) (userName.getScene().getWindow())).setScene(new Scene(root));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, response.getMsg()).showAndWait();
            }
        });
    }
}