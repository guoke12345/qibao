package com.qibao.core.common.model;

import java.io.Serializable;

/**
 * Created by Jemry.Liu on 2015/7/29.
 */
public class ResponseImage implements Serializable {

    /**
     * 图片路径
     */
    private String filePath;

    /**
     * 图片高度
     */
    private Integer height;

    /**
     * 图片宽度
     */
    private Integer width;

    /**
     * 图片类型
     */
    private String picType;

    /**
     * 构造ResponseImage对象
     *
     * @param filePath 图片路径
     * @param height 图片高度
     * @param width 图片宽度
     * @param picType 图片类型
     */
    public ResponseImage(String filePath, Integer height, Integer width, String picType) {
        this.filePath = filePath;
        this.height = height;
        this.width = width;
        this.picType = picType;
    }

    /**
     * 构造ResponseImage对象
     */
    public ResponseImage() {
    }

    /**
     * 获取 图片路径
     *
     * @return 图片路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 设置 图片路径
     *
     * @param filePath 图片路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 获取 图片高度
     *
     * @return 图片高度
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * 设置 图片高度
     *
     * @param height 图片高度
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * 获取 图片宽度
     *
     * @return 图片宽度
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * 设置 图片宽度
     *
     * @param width 图片宽度
     */
    public void setWidth(Integer width) {
        this.width = width;
    }


    /**
     * 获取 图片类型
     *
     * @return 图片类型
     */
    public String getPicType() {
        return picType;
    }

    /**
     * 设置 图片类型
     *
     * @param picType 图片类型
     */
    public void setPicType(String picType) {
        this.picType = picType;
    }
}
