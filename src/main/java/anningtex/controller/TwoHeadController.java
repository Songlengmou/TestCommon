package anningtex.controller;

import anningtex.manger.api.Constants;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author Song
 * @Date：2021-10-30
 */
public class TwoHeadController implements Initializable {
    @FXML
    private ImageView dynamic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dynamic.setImage(new Image(new File(Constants.ICON_NO_DATA).toURI().toString()));
    }
}