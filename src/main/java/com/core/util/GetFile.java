package com.core.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.web.util.UriUtils;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetFile {

    static BASE64Decoder decoder = new BASE64Decoder();

    /**
     * @throws
     * @Title:buFile
     * @Description:根据防伪码获取文件,将pdf转成base64字符串
     * @param: @param typeData
     * @param: @param codeData
     * @param: @return
     * @return:StringBuffer
     */

    public static String buFile(String typeData, String codeData) {
        InputStream is = null;
        ByteArrayOutputStream os = null;
        String resultCode = "00";
        String dUrlData = "";
// StringBuffer res_xml = new StringBuffer();

//pdf源路径
        String urlStr = " ";
        byte[] buff = new byte[1024];
        int len = 0;
        try {
            URL url = new URL(UriUtils.encodePath(urlStr, "UTF-8"));
// URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "plain/text;charset=" + "UTF-8");
            conn.setRequestProperty("charset", "UTF-8");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setReadTimeout(30000);
            conn.connect();
            is = conn.getInputStream();
            os = new ByteArrayOutputStream();
            while ((len = is.read(buff)) != -1) {
                os.write(buff, 0, len);
            }

//刷新此输出流并强制写出所有缓冲的输出字节，必须这行代码，否则有可能有问题
            os.flush();
            os.toByteArray();
            dUrlData = Base64.encode(os.toByteArray());
// 二进制数据流转BASE64格式字符串

        } catch (IOException e) {
            resultCode = "01";
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    resultCode = "02";
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    resultCode = "03";
                }
            }
        }
        System.out.println(dUrlData);
        return dUrlData;
    }


    /**
     * @throws
     * @Title:base64StringToPDF
     * @Description: 1.使用BASE64Decoder对编码的字符串解码成字节数组
     * 2.使用底层输入流ByteArrayInputStream对象从字节数组中获取数据；
     * 3.建立从底层输入流中读取数据的BufferedInputStream缓冲输出流对象；
     * 4.使用BufferedOutputStream和FileOutputSteam输出数据到指定的文件中
     * @param: @param base64sString
     * @param: @param filePath
     * @return:void
     */
    public static StringBuffer BasetoPdffile(String typeData, String codeData, String filepath) {
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        String pdfBase64Str = null;
        String resultCode = "00";
        StringBuffer res_xml = new StringBuffer();
        try {

//将pdf转为base64编码的字符串
            pdfBase64Str = buFile(typeData, codeData);

//将base64编码的字符串解码成字节数组
            byte[] bytes = Base64.decode(pdfBase64Str);

//apache公司的API
//byte[] bytes = Base64.decodeBase64(pdfBase64Str);

//创建一个将bytes作为其缓冲区的ByteArrayInputStream对象
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

//创建从底层输入流中读取数据的缓冲输入流对象
            bis = new BufferedInputStream(byteArrayInputStream);

//指定输出的文件
            File file = new File(filepath);
            File path = file.getParentFile();
            if (!path.exists()) {
                path.mkdirs();
            }

//创建到指定文件的输出流
            fos = new FileOutputStream(file);

//为文件输出流对接缓冲输出流对象
            bos = new BufferedOutputStream(fos);
            byte[] buffer = new byte[1024];
            int length = bis.read(buffer);
            while (length != -1) {
                bos.write(buffer, 0, length);
                length = bis.read(buffer);
            }

//刷新此输出流并强制写出所有缓冲的输出字节，必须这行代码，否则有可能有问题
            bos.flush();

        } catch (Exception e) {
            resultCode = "01";
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                bos.close();
                fos.close();
            } catch (IOException e) {
                resultCode = "02";
                e.printStackTrace();
            }
        }

        res_xml.append("{");
//        res_xml.append("\"" + Contant.XML_RESULTCODE + "\":\"" + resultCode + "\",");
//        res_xml.append("\"" + Contant.XML_RESULTDATA + "\":\"" + filepath + "\"");
        res_xml.append("}");
        System.out.println("filepath=" + filepath);
        return res_xml;
    }

    /**
     * @throws
     * @Title:inStFile
     * @Description:pdf按行读取
     * @param: @param typeData
     * @param: @param codeData
     * @param: @return
     * @return:StringBuffer
     */
    public static StringBuffer inStFile(String typeData, String codeData) {
        InputStream is = null;
        String resultCode = "00";
        StringBuffer res_xml = new StringBuffer();
        StringBuffer sb = new StringBuffer();

//源路径
        String urlStr = "";
        try {
            URL url = new URL(urlStr);
// URL url = new URL(UriUtils.encodePath(urlStr, "UTF-8"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "plain/text;charset=" + "UTF-8");
            conn.setRequestProperty("charset", "UTF-8");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setReadTimeout(30000);
            conn.connect();

            InputStreamReader brr = new InputStreamReader(conn.getInputStream(), "UTF-8");
            BufferedReader br = new BufferedReader(brr);
            String message = null;
            while ((message = br.readLine()) != null) {
                sb.append(message);
            }
        } catch (IOException e) {
            resultCode = "01";
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    resultCode = "02";
                }
            }
        }
        res_xml.append("{");
//        res_xml.append("\"" + Contant.XML_RESULTCODE + "\":\"" + resultCode + "\",");
//        res_xml.append("\"" + Contant.XML_RESULTDATA + "\":\"" + sb + "\"");
        res_xml.append("}");
        System.out.println(res_xml);
        return res_xml;
    }


}