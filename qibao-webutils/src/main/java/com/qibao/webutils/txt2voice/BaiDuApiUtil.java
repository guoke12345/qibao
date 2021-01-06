package com.qibao.webutils.txt2voice;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 百度文字转语音，工具类
 *
 * @author 张伟超
 * @create 2017/10/21 0021
 **/
public class BaiDuApiUtil {

    private static int RES_TEXT_LENGTH = 340; // 每一段合成中文长度，
    // 百度api限制每次合成字节不超过1024，一个中文一般是3个字节

    public static String text2mp3(String content, String filePath, String fileName) {
        AipSpeech client = new AipSpeech(ConfigCons.APP_ID, ConfigCons.API_KEY,
                ConfigCons.SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 设置可选参数
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("spd", ConfigCons.BAIDU_API_SPD);
        options.put("pit", ConfigCons.BAIDU_API_PIT);
        options.put("per", ConfigCons.BAIDU_API_PER);

//		content = TextUtil.removeHtmlTag(content);

        // 内容按RES_TEXT_LENGTH长度切割成N份
        List<byte[]> bytesLs   = new ArrayList<byte[]>();
        List<String> contentLs = getStrList(content, RES_TEXT_LENGTH);

        if (contentLs != null && contentLs.size() > 0) {
            for (String c : contentLs) {
                TtsResponse res = client.synthesis(c, "zh", 1, options);
                if (res.getErrorCode() != 0) {
                    return res.getErrorMsg();
                }
                bytesLs.add(res.getData());
            }
        }

        createFile(bytesLs, filePath, fileName);

        return null;
    }

    private static void createFile(List<byte[]> bfileLs, String filePath,
                                   String fileName) {
        BufferedOutputStream bos  = null;
        FileOutputStream     fos  = null;
        File                 file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists()) {// 判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "/" + fileName);
            System.out.println(filePath + "/" + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            for (byte[] bfile : bfileLs)
                bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 把原始字符串分割成指定长度的字符串列表
     *
     * @param inputString 原始字符串
     * @param length      指定长度
     * @return
     */
    private static List<String> getStrList(String inputString, int length) {
        int size = (int) Math.ceil((double) inputString.length() / (double) length);
        return getStrList(inputString, length, size);
    }

    /**
     * 把原始字符串分割成指定长度的字符串列表
     *
     * @param inputString 原始字符串
     * @param length      指定长度
     * @param size        指定列表大小
     * @return
     */
    private static List<String> getStrList(String inputString, int length,
                                           int size) {
        List<String> list = new ArrayList<String>();
        for (int index = 0; index < size; index++) {
            String childStr = substring(inputString, index * length,
                    (index + 1) * length);
            list.add(childStr);
        }
        return list;
    }

    /**
     * 分割字符串，如果开始位置大于字符串长度，返回空
     *
     * @param str 原始字符串
     * @param f   开始位置
     * @param t   结束位置
     * @return
     */
    private static String substring(String str, int f, int t) {
        if (f > str.length())
            return null;
        if (t > str.length()) {
            return str.substring(f, str.length());
        } else {
            return str.substring(f, t);
        }
    }
}
