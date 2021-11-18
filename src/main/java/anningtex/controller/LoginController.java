package anningtex.controller;

import anningtex.manger.api.BaseResponse;
import anningtex.manger.api.Constants;
import anningtex.manger.http.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @Author Song
 * @Desc:登录
 * @Date：2021-10-30
 */
public class LoginController /*implements Initializable*/ {
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
//                rememberUsers(strUserName, strPassword);
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

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        Properties prop = new Properties();
//        try {
//            if (new File("user.properties").exists()) {
//                InputStream in = new BufferedInputStream(new FileInputStream("user.properties"));
//                prop.load(in);
//                userName.setText(prop.getProperty("userName"));
//                password.setText(prop.getProperty("passWord"));
//                in.close();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public void rememberUsers(String username, String password) {
//        Properties prop = new Properties();
//        try {
//            System.out.println("记住密码");
//            //这里true表示追加,false会将原文件清空后,重新添加.
//            FileOutputStream oFile = new FileOutputStream("user.properties", false);
//            prop.setProperty("userName", username);
//            prop.setProperty("passWord", password);
//            prop.store(oFile, null);
//            oFile.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}