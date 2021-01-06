package com.qibao.core.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class PostHelper{


    private static final String ENCODING_UTF_8 = "UTF-8";
    private static final String X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";


    public static String postStringEntity(String url, String entityString) throws IOException {
        StringEntity entityRequest = new StringEntity(entityString,"utf-8");
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entityRequest);
        httpPost.setHeader("Content-Type", X_WWW_FORM_URLENCODED);//; charset=utf-8
        HttpClient httpClient = HttpClients.createDefault();
        // httpPost.setHeader("Content-Type", "text/xml;charset=GBK");

        HttpResponse response = httpClient.execute(httpPost);

        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            throw new RuntimeException("请求失败");
        }
        HttpEntity resEntity = response.getEntity();
        return (resEntity == null) ? null : EntityUtils.toString(resEntity, "UTF-8");
    }



    /**
     * old Post
     */
    public  static void httpUrlConnection(String pathUrl) {
        try {
            // 建立连接
            URL url = new URL(pathUrl);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            // //设置连接属性
            httpConn.setDoOutput(true);// 使用 URL 连接进行输出
            httpConn.setDoInput(true);// 使用 URL 连接进行输入
            httpConn.setUseCaches(false);// 忽略缓存
            httpConn.setRequestMethod("POST");// 设置URL请求方法
            String requestString = "客服端要以以流方式发送到服务端的数据...";
            // 设置请求属性
            // 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致

            byte[] requestStringBytes = requestString.getBytes(ENCODING_UTF_8);
            httpConn.setRequestProperty("Content-length", "" + requestStringBytes.length);
            httpConn.setRequestProperty("Content-Type", X_WWW_FORM_URLENCODED);
            httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            httpConn.setRequestProperty("Charset", ENCODING_UTF_8);

            String name = URLEncoder.encode("黄武艺", ENCODING_UTF_8);
            System.out.println(name);
            httpConn.setRequestProperty("NAME", name);
            // 建立输出流，并写入数据
            OutputStream outputStream = httpConn.getOutputStream();
            outputStream.write(requestStringBytes);
            outputStream.close();
            // 获得响应状态
            int responseCode = httpConn.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responseCode) {// 连接成功
                // 当正确响应时处理数据
                StringBuffer sb = new StringBuffer();
                String readLine;
                BufferedReader responseReader;
                // 处理响应流，必须与服务器响应流输出的编码一致
                responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), ENCODING_UTF_8));
                while ((readLine = responseReader.readLine()) != null) {
                    sb.append(readLine).append("\n");
                }
                responseReader.close();
                //tv.setText(sb.toString());
                System.out.println(sb.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}