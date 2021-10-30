package anningtex.controller;

import anningtex.manger.api.Constants;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author Song
 * @Desc:第二个首页分类
 * @Date：2021-10-21
 */
public class TwoMenuController implements Initializable {
    private static final Logger logger = Logger.getLogger(TwoMenuController.class.getName());

    @FXML
    private AnchorPane mainPaneUnderScroll;

    @FXML
    private TreeView<String> tvMainLeft;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTreeView();
    }

    private void initTreeView() {
        TreeItem<String> root = new TreeItem<>(Constants.MAIN_TREE_HEADER);
        root.setExpanded(true);
        root.getChildren().addAll(
                new TreeItem<>(Constants.MAIN_TREE_HEADER_ITEM1),
                new TreeItem<>(Constants.MAIN_TREE_HEADER_ITEM2));
        tvMainLeft.setRoot(root);
    }

    @FXML
    public void mainTreeViewClick(MouseEvent mouseEvent) throws IOException {
        logger.log(Level.INFO, "点击TreeView");
        // 获取当前点击的Item
        TreeItem<String> selectedItem = tvMainLeft.getSelectionModel().getSelectedItem();
        logger.log(Level.INFO, selectedItem.getValue());
        String pagePath = "";
        switch (selectedItem.getValue()) {
            case Constants.MAIN_TREE_HEADER: //头部
                pagePath = Constants.HEAD_VIEW_PATH;
                break;
            case Constants.MAIN_TREE_HEADER_ITEM1: //qc
                pagePath = Constants.QC_VIEW_PATH;
                break;
            case Constants.MAIN_TREE_HEADER_ITEM2: //Me
                pagePath = Constants.ME_VIEW_PATH;
                break;
        }
        skipView(pagePath);
    }

    private void skipView(String pagePath) throws IOException {
        logger.info("显示界面");
        ObservableList<Node> scrollChildren = mainPaneUnderScroll.getChildren();
        scrollChildren.clear();
        scrollChildren.add(FXMLLoader.load(getClass().getResource(pagePath)));
    }
}