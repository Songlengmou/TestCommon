package anningtex.controller;

import anningtex.entity.OrderAttrsEntity;
import anningtex.entity.OrderEntity;
import anningtex.entity.OrderTexInfoEntity;
import anningtex.entity.StatBatchEntity;
import anningtex.manger.api.BaseResponse;
import anningtex.manger.api.Constants;
import anningtex.manger.api.JsonData;
import anningtex.manger.api.UrlManger;
import anningtex.manger.http.HttpUtils;
import anningtex.manger.http.RxBus;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.*;

/**
 * @Author Song
 * desc:使用TableView
 * @Date：2021-10-30
 */
public class FirstOneController implements Initializable {
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
    private TableView<OrderEntity> tableView;

    @FXML
    TableColumn<OrderEntity, Integer> tcSeq;
    @FXML
    TableColumn<OrderEntity, String> tcOrderNo;
    @FXML
    TableColumn<OrderEntity, String> tcOrderHead;
    @FXML
    TableColumn<OrderEntity, String> tcOrderDate;
    @FXML
    TableColumn<OrderEntity, String> tcQuantityInfo;
    @FXML
    TableColumn<OrderEntity, OrderAttrsEntity> tcAttrs;
    @FXML
    TableColumn<OrderEntity, String> tcCompanyName;
    @FXML
    TableColumn<OrderEntity, String> tcNote;
    @FXML
    TableColumn<OrderEntity, StatBatchEntity> tcStatBatch;
    @FXML
    ProgressIndicator progress;

    private static int numSeq = 1;//seq

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        progress.setVisible(false);
        tcSeq.setCellValueFactory(new PropertyValueFactory<>("seq"));
        tcOrderNo.setCellValueFactory(new PropertyValueFactory<>("OrderNo"));
        tcOrderHead.setCellValueFactory(new PropertyValueFactory<>("OrderHead"));
        tcOrderDate.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
        tcQuantityInfo.setCellValueFactory(new PropertyValueFactory<>("QuantityInfo"));
        tcQuantityInfo.setCellFactory(p -> {
            TableCell<OrderEntity, String> tc = new TableCell<OrderEntity, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        setTextAlignment(TextAlignment.CENTER);
                        setText(item);
                    } else {
                        setText(null);
                    }
                }
            };
            tc.setAlignment(Pos.CENTER);
            return tc;
        });
        tcAttrs.setCellValueFactory(new PropertyValueFactory<>("AttrsInfo"));
        tcCompanyName.setCellValueFactory(new PropertyValueFactory<>("CompanyName"));
        tcNote.setCellValueFactory(new PropertyValueFactory<>("order_score"));
        tcStatBatch.setCellValueFactory(new PropertyValueFactory<>("statBatchEntity"));
        tcStatBatch.setCellFactory(p -> {
            TableCell<OrderEntity, StatBatchEntity> tc = new TableCell<OrderEntity, StatBatchEntity>() {
                @Override
                protected void updateItem(StatBatchEntity statBatchEntity, boolean empty) {
                    super.updateItem(statBatchEntity, empty);
                    if (statBatchEntity != null) {
                        setText("挂钩样: " + statBatchEntity.getOrder_finished_count() + "\n" +
                                "成品图: " + statBatchEntity.getOrder_item_finished_count() + "/" + statBatchEntity.getOrder_item_count());
                        if (0 == statBatchEntity.getOrder_finished_count() || 0 == statBatchEntity.getOrder_item_finished_count()) {
                            setTextFill(Color.RED);
                        } else {
                            setTextFill(Color.GREEN);
                        }
                    } else {
                        setText(null);
                    }
                }
            };
            tc.setAlignment(Pos.CENTER);
            return tc;
        });

        //获取布产单品名列表
        HttpUtils.doGet(Constants.BASE_URL + UrlManger.TEX_INFO_LIST, paramResponse -> {
            BaseResponse<List<OrderTexInfoEntity>> response = new Gson().fromJson(paramResponse, new TypeToken<BaseResponse<List<OrderTexInfoEntity>>>() {
            }.getType());
            System.out.println("response: " + paramResponse);
            if (response.getCode() == 1) {
                List<OrderTexInfoEntity> data = response.getData();
                cbList.getItems().addAll(data);
            } else {
                new Alert(Alert.AlertType.ERROR, "加载错误").showAndWait();
            }
        });
        //set Data
        endData.getEditor().setText(DateFormat.getDateInstance().format(new Date()));
        //监听到下一个窗口的关闭后进行再次刷新
        RxBus.getInstance().toFlowable(String.class).subscribe(strResult -> {
            if ("refresh".equals(strResult)) {
                orderListData();
            }
        });
    }

    @FXML
    void queryData(ActionEvent event) {
        orderListData();
    }

    @FXML
    public void btnFreshList(ActionEvent actionEvent) {
        orderListData();
    }

    /***
     * 获取布产单列表
     */
    private void orderListData() {
        OrderTexInfoEntity selectedItem = cbList.getSelectionModel().getSelectedItem();
        String texID = "";
        if (selectedItem != null) {
            texID = selectedItem.getTexID();
        }
        String date1 = startData.getEditor().getText();
        String date2 = endData.getEditor().getText();
        String orderNo = tfOrderNo.getText();
        String requestBody = "orderno=" + orderNo + "&texid=" + texID + "&date1=" + date1 + "&date2=" + date2;
        tableView.getItems().clear();
        //获取布产单列表
//        HttpUtils.doPost(Constants.BASE_URL + UrlManger.SEARCH_ORDER, requestBody, paramResponse -> {
        BaseResponse<List<OrderEntity>> response = new Gson().fromJson(JsonData.jsonOrderList, new TypeToken<BaseResponse<List<OrderEntity>>>() {
        }.getType());
        if (response.getCode() == 1) {
            List<OrderEntity> data = response.getData();
            JsonArray jsonArray = new JsonArray();
            for (OrderEntity datum : data) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("order_id", datum.getOLID());
                jsonArray.add(jsonObject);
            }
            System.out.println("jsonArray: " + jsonArray.toString());
            getFinishedImageStatBatch(jsonArray.toString(), data);
        } else {
            new Alert(Alert.AlertType.ERROR, response.getMsg()).showAndWait();
        }
//        });
        //监听事件
        tableView.setRowFactory(tv -> {
            TableRow<OrderEntity> row = new TableRow<>();
            row.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 2 && (!row.isEmpty())) {
                    OrderEntity rowData = row.getItem();
                    System.out.println(rowData.toString());
                    Platform.runLater(() -> {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.TWO_VIEW_PATH));
                            Parent root = loader.load();
                            Stage stage = new Stage();
                            Scene scene = new Scene(root);
                            Map<String, String> map = new HashMap<>();
                            map.put("olid", rowData.getOLID() + "");
                            map.put("orderNo", rowData.getOrderNo());
                            stage.setTitle(rowData.getOrderNo());
                            stage.setUserData(map);
                            stage.setScene(scene);
                            stage.show();
                            FirstTwoController controller = loader.getController();
                            stage.setOnHidden(e -> {
                                controller.shutdown();
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            });
            return row;
        });
    }

    private void getFinishedImageStatBatch(String orderIdList, List<OrderEntity> orderEntities) {
        String requestBody = "order_id_list=" + orderIdList;
//        HttpUtils.doPost(Constants.BASE_URL + UrlManger.GET_FINISHED_IMAGE_STAT_BATCH, requestBody, paramResponse -> {
        BaseResponse<List<StatBatchEntity>> response = new Gson().fromJson(JsonData.jsonGLOrderList, new TypeToken<BaseResponse<List<StatBatchEntity>>>() {
        }.getType());
        List<StatBatchEntity> data = response.getData();
        for (StatBatchEntity statBatchEntity : data) {
            for (OrderEntity entity : orderEntities) {
                if (statBatchEntity.getOrder_id() == entity.getOLID()) {
                    entity.setStatBatchEntity(statBatchEntity);
                }
            }
        }
        numSeq = 1;
        orderEntities.forEach(c -> c.setSeq(numSeq++));
        tableView.getItems().addAll(orderEntities);
//    });
    }
}