package com.qibao.webutils.txt2voice;

/**
 * @author yezm
 * @createDate 创建时间：2017-8-2 上午10:12:05
 * 类说明
 */
public class ConfigCons {

//	public static final String VICEO_SAVE_PATH = "/t2v/";  //语音文件保存相对路径

    //#*****************************百度语音api配置**********************
    //#app_id，api_key，secret_key 从百度云后台控制台获取
    public static final String APP_ID     = "10266015";
    public static final String API_KEY    = "ifpKnYhKbxTH1pVP9GPMx05a";
    public static final String SECRET_KEY = "uL1Lo2Gk7rWG3dlqvEn7lXSw317lGsSk";

    //#语速，取值0-9，默认为5中语速
    public static final String BAIDU_API_SPD = "4"; //4

    //#音调，取值0-9，默认为5中语调
    public static final String BAIDU_API_PIT = "5"; //5

    //#发音人选择, 0为普通女声，1为普通男生，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女声
    public static final String BAIDU_API_PER = "1"; //0
}
