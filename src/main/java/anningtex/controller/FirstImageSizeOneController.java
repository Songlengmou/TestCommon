package anningtex.controller;

import anningtex.manger.http.RxBus;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author Song
 * @Desc:图片的鼠标滚动缩放
 * @Date：2021-10-29
 */
public class FirstImageSizeOneController implements Initializable {
    @FXML
    private ImageView imageView;

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
                Point2D eventPoint = new Point2D(event.getSceneX(), event.getSceneY());
                Point2D imagePoint = imageView.localToScene(new Point2D(imageView.getX(), imageView.getY()));
                Rectangle2D imageRect = new Rectangle2D(imagePoint.getX(), imagePoint.getY(), imageView.getFitWidth(), imageView.getFitHeight());
                Point2D ratePoint;
                Point2D eventPointDistance;
                if (newWidth > 980 / 4 * width && imageRect.contains(eventPoint)) {
                    ratePoint = eventPoint.subtract(imagePoint);
                    ratePoint = new Point2D(ratePoint.getX() / imageView.getFitWidth(), ratePoint.getY() / imageView.getFitHeight());
                    eventPointDistance = imageView.sceneToLocal(eventPoint);
                } else {
                    ratePoint = new Point2D(0.5, 0.5);
                    eventPointDistance = new Point2D(509 / 2, 390 / 2);
                }
                imageView.setX(eventPointDistance.getX() - newWidth * ratePoint.getX());
                imageView.setY(eventPointDistance.getY() - newHeight * ratePoint.getY());
                imageView.setFitWidth(newWidth);
                imageView.setFitHeight(newHeight);
            });
        });
    }
}