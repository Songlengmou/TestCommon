package anningtex.controller;

import anningtex.entity.OrderPicEntity;
import anningtex.manger.api.BaseResponse;
import anningtex.manger.api.Constants;
import anningtex.manger.api.JsonData;
import anningtex.manger.http.RxBus;
import anningtex.view.FirstImageSizeTwoMain;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * @Author Song
 * @Date：2021-10-30
 */
public class FirstTwoController implements Initializable {
    private static final Logger logger = Logger.getLogger(FirstTwoController.class.getName());

    @FXML
    TableView<OrderPicEntity.ImageListBean> tableView;
    @FXML
    TableColumn<OrderPicEntity.ImageListBean, String> seq;
    @FXML
    TableColumn<OrderPicEntity.ImageListBean, String> tcFlowerNo;
    @FXML
    TableColumn<OrderPicEntity.ImageListBean, String> tcFlowerUrlMini;
    @FXML
    TableColumn<OrderPicEntity.ImageListBean, String> tcFlowerFinishedUrl;
    @FXML
    TableColumn<OrderPicEntity.ImageListBean, String> oper;
    @FXML
    TableColumn<OrderPicEntity.ImageListBean, String> photo;
    @FXML
    ImageView ggy;
    @FXML
    Button btnDelete;
    @FXML
    Button btnPhoto;

    private Map<String, String> userData;
    private static int numSeq = 1;//seq

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(this::getUserData);
        seq.setCellValueFactory(new PropertyValueFactory<>("seq"));
        seq.setCellFactory(setCommonData());
        tcFlowerNo.setCellValueFactory(new PropertyValueFactory<>("flower_no"));
        tcFlowerNo.setCellFactory(setCommonData());
        tcFlowerUrlMini.setCellValueFactory(new PropertyValueFactory<>("flower_url_mini"));
        tcFlowerUrlMini.setCellFactory(v -> new TableCell<OrderPicEntity.ImageListBean, String>() {
            {
                setAlignment(Pos.CENTER);
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    ImageView imageView = new ImageView();
                    imageView.setFitHeight(100);
                    imageView.setFitWidth(100);
                    setGraphic(imageView);
                    new Thread(() -> {
                        Image image = new Image(item);
                        imageView.setImage(image);
                    }).start();
                    setOnMouseClicked(v -> {
                        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
                        String flowerNo = tableView.getItems().get(selectedIndex).getFlower_no();
                        String finishedUrl = tableView.getItems().get(selectedIndex).getFlower_url_medium();
                        launchImageSizeWindow(flowerNo, finishedUrl);
                    });
                } else {
                    setGraphic(null);
                }
            }
        });
        tcFlowerFinishedUrl.setCellFactory(v -> new TableCell<OrderPicEntity.ImageListBean, String>() {
            {
                setAlignment(Pos.CENTER);
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null || !"".equals(item)) {
                    ImageView imageView = new ImageView();
                    imageView.setFitHeight(100);
                    imageView.setFitWidth(100);
                    setGraphic(imageView);
                    new Thread(() -> {
                        assert item != null;
                        if (!"".equals(item)) {
                            imageView.setImage(new Image(item));
                        }
                    }).start();
                    setOnMouseClicked(v -> {
                        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
                        String flowerNo = tableView.getItems().get(selectedIndex).getFlower_no();
                        String finishedUrl = tableView.getItems().get(selectedIndex).getFlower_finished_url();
                        launchImageSizeWindow(flowerNo, finishedUrl);
                    });
                } else {
                    setGraphic(null);
                }
            }
        });
        tcFlowerFinishedUrl.setCellValueFactory(new PropertyValueFactory<>("flower_finished_url"));
        oper.setCellValueFactory(new PropertyValueFactory<>("flower_no"));
        oper.setCellFactory(v -> new TableCell<OrderPicEntity.ImageListBean, String>() {
            {
                setAlignment(Pos.CENTER);
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setTextAlignment(TextAlignment.CENTER);
                    setText("删除");
                    setTextFill(Color.RED);
                    setOnMouseClicked(v -> {
                        System.out.println("删除监听");
                    });
                } else {
                    setText(null);
                }
            }
        });
        photo.setCellValueFactory(new PropertyValueFactory<>("flower_no"));
        photo.setCellFactory(v -> new TableCell<OrderPicEntity.ImageListBean, String>() {
            {
                setAlignment(Pos.CENTER);
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setTextAlignment(TextAlignment.CENTER);
                    setText("图片缩放选择第二种");
                    setTextFill(Color.PURPLE);
                    setOnMouseClicked(v -> {
                        Platform.runLater(() -> {
                            try {
                                Stage stage = new Stage();
                                FirstImageSizeTwoMain imageSizeMain = new FirstImageSizeTwoMain();
                                stage.setTitle("第二种");
                                imageSizeMain.start(stage);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    });
                } else {
                    setText(null);
                }
            }
        });
        btnPhoto.setTextFill(Color.PURPLE);
        btnDelete.setTextFill(Color.RED);
    }

    private Callback<TableColumn<OrderPicEntity.ImageListBean, String>, TableCell<OrderPicEntity.ImageListBean, String>> setCommonData() {
        return v -> new TableCell<OrderPicEntity.ImageListBean, String>() {
            {
                setAlignment(Pos.CENTER);
            }

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
    }

    /**
     * 获取花布图片详情
     */
    private void getUserData() {
        userData = (Map<String, String>) tableView.getScene().getWindow().getUserData();
//        HttpUtils.doPost(Constants.BASE_URL + UrlManger.ORDER_PIC, "olid=" + userData.get("olid"), paramResponse -> {
        BaseResponse<OrderPicEntity> baseResponse = new Gson().fromJson(JsonData.jsonCPList, new TypeToken<BaseResponse<OrderPicEntity>>() {
        }.getType());
        List<OrderPicEntity.ImageListBean> imageList = baseResponse.getData().getImage_list();
        numSeq = 1;
        imageList.forEach(c -> c.setSeq((numSeq++) + ""));
        tableView.getItems().addAll(imageList);
        //ggy
        if (baseResponse.getData().getGgy() != null && !"".equals(baseResponse.getData().getGgy())) {
            ggy.setImage(new Image(baseResponse.getData().getGgy()));
        } else {
            File file = new File(Constants.ICON_NO_DATA);
            ggy.setImage(new Image(file.toURI().toString()));
        }
        ggy.setOnMouseClicked(v -> {
            launchImageSizeWindow(userData.get("orderNo"), baseResponse.getData().getGgy());
        });
//    });
    }

    /**
     * 点击图片跳转到缩放窗口界面
     */
    private void launchImageSizeWindow(String titleName, String picUrl) {
        try {
            Stage stage = new Stage();
            //第一种
//            Parent root = FXMLLoader.load(getClass().getResource(Constants.IMAGE_SIZE_VIEW_PATH));
            //第二种
            Parent root = FXMLLoader.load(getClass().getResource(Constants.IMAGE_SIZE_VIEW_PATH_TWO));
            Scene scene = new Scene(root);
            stage.setTitle(titleName);
            RxBus.getInstance().send(picUrl);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void photoGGYUp(ActionEvent actionEvent) {
        logger.info("其他");
    }

    @FXML
    public void photoGGYDel(ActionEvent actionEvent) {
        logger.info("删除GGY");
    }

    @FXML
    public void btnFreshList(ActionEvent actionEvent) {
        tableView.getItems().clear();
        FirstTwoController.this.getUserData();
    }

    void shutdown() {
        tableView.getScene().getWindow().hide();
    }
}