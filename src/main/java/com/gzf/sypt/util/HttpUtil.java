package com.gzf.sypt.util;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author guozifan
 * @Description: 第三方接口工具类
 * @date 2018/7/29 14:41
 */
public class HttpUtil {

    /**
     * 通过接口URL地址，和参数向接口请求结果
     * @param params 参数，通过write（）直接发送出去，需要处理好才可以
     * @return 返回json数据
     * @throws IOException
     */
    public static JSONObject getPostResult(String apiUrl, String params) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setRequestMethod("POST");
        urlConnection.setConnectTimeout(5 * 1000);
        urlConnection.setReadTimeout(10 * 1000);
        urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        OutputStreamWriter outputStreamWriter =
                new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8");
        outputStreamWriter.write(params);
        outputStreamWriter.flush();

        InputStreamReader inputStreamReader =
                new InputStreamReader(urlConnection.getInputStream(), "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuffer result = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }

        if (outputStreamWriter != null) {
            outputStreamWriter.close();
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        return (JSONObject) JSONObject.parse(result.toString());
    }
}
