package anningtex.controller;

import anningtex.manger.api.BaseResponse;
import anningtex.manger.api.Constants;
import anningtex.manger.http.HttpUtils;
import com.google.gson.Gson;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author Song
 * @Desc:上传图片 文件的例子
 * @Date：2021-10-30
 */
public class PicUploadTestController {

    private static void uploadFlowerFinishedPic(String pos, String filePath, String flowerNo, String orderNo) {
        File file = new File(filePath);
        byte[] data;
        try {
            data = Files.readAllBytes(Paths.get(file.getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String fieldNameKey = "file";
        String strPath = Constants.BASE_URL + "";
        HttpUtils.doPostFile(strPath, fieldNameKey, file.getName(), data, paramResponse -> {
            System.out.println("上传1paramResponse: " + paramResponse);
            BaseResponse baseResponse = new Gson().fromJson(paramResponse, BaseResponse.class);
            if (baseResponse.getCode() == 1) {
                new Alert(Alert.AlertType.INFORMATION, baseResponse.getMsg()).showAndWait();
            } else {
                new Alert(Alert.AlertType.ERROR, baseResponse.getMsg()).showAndWait();
            }
        });
    }
}