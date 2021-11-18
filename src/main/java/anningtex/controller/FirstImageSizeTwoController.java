package anningtex.controller;

import anningtex.manger.http.RxBus;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author Song
 * @Desc:图片的鼠标滚动缩放 第二种 (建议使用这种)
 * @Date：2021-10-29
 */
public class FirstImageSizeTwoController implements Initializable {
    @FXML
    private ImageView imageView;

    private double tOffX = 0, tOffY = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RxBus.getInstance().toFlowable(String.class).subscribe(strResult -> {
            System.out.println("strResult: " + strResult);
            imageView.setImage(new Image(strResult));

            final double w = imageView.getImage().getWidth();
            final double h = imageView.getImage().getHeight();
            final double max = Math.max(w, h);
            final int width = (int) (509 * w / max);
            imageView.addEventFilter(ScrollEvent.SCROLL, event -> {
                double rate;
                if (event.getDeltaY() > 0) {
                    rate = 0.05;
                } else {
                    rate = -0.05;
                }
                double newWidth = imageView.getFitWidth() + w * rate;
                double newHeight = imageView.getFitHeight() + h * rate;
                if (newWidth <= width || newWidth > 509 * width) {
                    return;
                }
                imageView.setFitWidth(newWidth);
                imageView.setFitHeight(newHeight);
            });

            imageView.setOnMousePressed(event -> {
                tOffX = event.getSceneX() - imageView.getX();
                tOffY = event.getSceneY() - imageView.getY();
            });
            imageView.setOnMouseDragged(event -> {
                imageView.xProperty().bind(new SimpleDoubleProperty(event.getSceneX() - tOffX));
                imageView.yProperty().bind(new SimpleDoubleProperty(event.getSceneY() - tOffY));
            });
        });
    }
}