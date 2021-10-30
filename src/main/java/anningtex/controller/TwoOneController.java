package anningtex.controller;

import anningtex.entity.OrderTwoEntity;
import anningtex.entity.OrderTexInfoEntity;
import anningtex.manger.api.BaseResponse;
import anningtex.manger.api.Constants;
import anningtex.manger.api.JsonData;
import anningtex.manger.api.UrlManger;
import anningtex.manger.http.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @Author Song
 * @Desc:desc:使用ListView
 * @Date：2021-10-30
 */
public class TwoOneController implements Initializable {
    @FXML
    private Button btnQuery;

    @FXML
    private ChoiceBox<OrderTexInfoEntity> cbList;

    @FXML
    private DatePicker endData;

    @FXML
    private DatePicker startData;

    @FXML
    private TextField tfOrderNo;

    @FXML
    private ListView<OrderTwoEntity> lvOrderList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HttpUtils.doGet(Constants.BASE_URL + UrlManger.TEX_INFO_LIST, param -> {
            BaseResponse<List<OrderTexInfoEntity>> response = new Gson().fromJson(param, new TypeToken<BaseResponse<List<OrderTexInfoEntity>>>() {
            }.getType());
            System.out.println(param);
            if (response.getCode() == 1) {
                List<OrderTexInfoEntity> data = response.getData();
                cbList.getItems().addAll(data);
            } else {
                new Alert(Alert.AlertType.ERROR, "加载错误").showAndWait();
            }
        });
        endData.getEditor().setText("2021-10-1");
    }

    @FXML
    void queryData(ActionEvent event) {
        OrderTexInfoEntity selectedItem = cbList.getSelectionModel().getSelectedItem();
        String texID = "";
        if (selectedItem != null) {
            texID = selectedItem.getTexID();
        }
        String date1 = startData.getEditor().getText();
        String date2 = endData.getEditor().getText();
        String orderno = tfOrderNo.getText();
        lvOrderList.getItems().clear();
        BaseResponse<List<OrderTwoEntity>> response = new Gson().fromJson(JsonData.jsonOrderList, new TypeToken<BaseResponse<List<OrderTwoEntity>>>() {
        }.getType());
        if (response.getCode() == 1) {
            List<OrderTwoEntity> data = response.getData();
            lvOrderList.getItems().addAll(data);
            lvOrderList.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 2) {
                    Platform.runLater(() -> {
                        int selectedIndex = lvOrderList.getSelectionModel().getSelectedIndex();
                        String orderNo = lvOrderList.getItems().get(selectedIndex).getOrderNo();
                        System.out.println("orderNo: " + orderNo);
                        try {
                            //1.关闭旧窗口 打开新窗口类
//                        Parent root = FXMLLoader.load(getClass().getResource(Constants.TWO_MENU_VIEW_PATH));
//                        ((Stage) (lvOrderList.getScene().getWindow())).setScene(new Scene(root));

                            //2.关闭旧窗口 打开新窗口类
//                        Stage primaryStage = (Stage) lvOrderList.getScene().getWindow();
//                        primaryStage.close();
//                        TwoMenuMain twoMenuMain = new TwoMenuMain();
//                        twoMenuMain.start(new Stage());

                            //3.打开旧窗口 同时打开新窗口类
                            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.TWO_MENU_VIEW_PATH));
                            Stage stage = new Stage();
                            stage.setScene(new Scene(loader.load()));
                            stage.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
            });
        } else {
            new Alert(Alert.AlertType.ERROR, response.getMsg()).showAndWait();
        }
    }
}