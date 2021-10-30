package anningtex.manger.http;

import anningtex.manger.api.Constants;
import anningtex.manger.api.UrlManger;
import javafx.application.Platform;

import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpUtils {
    private static String cookie;

    public static void login(String userName, String password, ResponseCallback callback) {
        new Thread(() -> {
            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(Constants.BASE_URL + UrlManger.LOGIN).openConnection();
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);
                conn.setRequestMethod("GET");
                conn.setDoOutput(true);
                OutputStream outputStream = conn.getOutputStream();
                String parameter = "username=" + userName + "&password=" + stringToMD5(password);
                outputStream.write((parameter).getBytes());
                conn.connect();
                int responseCode = conn.getResponseCode();
                System.out.println("responseCode: " + responseCode);
                if (responseCode == 200) {
                    InputStream inputStream = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String str;
                    while ((str = reader.readLine()) != null) {
                        stringBuilder.append(str);
                    }
                    Map<String, List<String>> headerFields = conn.getHeaderFields();
                    Set<Map.Entry<String, List<String>>> entries = headerFields.entrySet();
                    entries.forEach(e -> {
                        if ("Set-Cookie".equals(e.getKey())) {
                            cookie = e.getValue().get(0);
                        }
                    });
                    Platform.runLater(() -> {
                        callback.call(stringBuilder.toString());
                    });
                }
                conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static String stringToMD5(String plainText) {
        byte[] secretBytes;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    public static void doGet(String url, ResponseCallback callback) {
        new Thread(() -> {
            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(35000);
                conn.setRequestProperty("Cookie", cookie);
                conn.setRequestMethod("GET");
                conn.connect();
                int responseCode = conn.getResponseCode();
                System.out.println("responseCode: " + responseCode);
                if (responseCode == 200) {
                    responseCodeOK(callback, conn);
                }
                conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void doPost(String url, String requestBody, ResponseCallback callback) {
        new Thread(() -> {
            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(35000);
                conn.setRequestProperty("Cookie", cookie);
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write((requestBody).getBytes());
                conn.connect();
                int responseCode = conn.getResponseCode();
                System.out.println("responseCode: " + responseCode);
                if (responseCode == 200) {
                    responseCodeOK(callback, conn);
                }
                conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void responseCodeOK(ResponseCallback callback, HttpURLConnection conn) throws IOException {
        InputStream inputStream = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String str;
        while ((str = reader.readLine()) != null) {
            stringBuilder.append(str);
        }
        //Response
        Platform.runLater(() -> {
            callback.call(stringBuilder.toString());
        });
    }

    /**
     * 上传file文件
     *
     * @param strUrl    接口地址
     * @param fieldName 键值key
     * @param fileName  文件名
     * @param data      读取文件中的路径
     * @param callback  回调信息
     */
    public static void doPostFile(String strUrl, String fieldName, String fileName, byte[] data, ResponseCallback callback) {
        new Thread(() -> {
            String attachmentName = fieldName;
            String attachmentFileName = fileName;
            String crlf = "\r\n";
            String twoHyphens = "--";
            String boundary = "*****";
            try {
                HttpURLConnection httpUrlConnection;
                URL url = new URL(strUrl);
                httpUrlConnection = (HttpURLConnection) url.openConnection();
                httpUrlConnection.setUseCaches(false);
                httpUrlConnection.setDoOutput(true);
                httpUrlConnection.setRequestProperty("Cookie", cookie);
                httpUrlConnection.setRequestMethod("POST");
                httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");
                httpUrlConnection.setRequestProperty("Cache-Control", "no-cache");
                httpUrlConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                DataOutputStream request = new DataOutputStream(httpUrlConnection.getOutputStream());
                request.writeBytes(twoHyphens + boundary + crlf);
                request.writeBytes("Content-Disposition: form-data; name=\"" +
                        attachmentName + "\";filename=\"" +
                        attachmentFileName + "\"" + crlf);
                request.writeBytes(crlf);
                request.write(data);
                request.writeBytes(crlf);
                request.writeBytes(twoHyphens + boundary + twoHyphens + crlf);
                request.flush();
                request.close();
                int code = httpUrlConnection.getResponseCode();
                InputStream is;
                if (code == 200) {
                    is = httpUrlConnection.getInputStream();
                } else {
                    System.out.println("code=" + code);
                    is = httpUrlConnection.getErrorStream();
                }
                InputStream responseStream = new BufferedInputStream(is);
                BufferedReader responseStreamReader = new BufferedReader(new InputStreamReader(responseStream));
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = responseStreamReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                responseStreamReader.close();
                String response = stringBuilder.toString();
                responseStream.close();
                httpUrlConnection.disconnect();
                callback.call(response);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static void main(String[] args) throws Exception {

    }
}