package com.qibao.core.common.fileManage;


import com.qibao.core.common.exception.SystemException;
import com.qibao.core.common.model.ResponseImage;

public class FileManageContext {

    BaseUpload baseUpload = null;

    /**
     * 构造方法初始化文件存储策略
     *
     * @param type
     */
    public FileManageContext(String type) {
        switch (type) {
            case "localServer":
                baseUpload = new UploadLocalServer();
                break;
//            case "upYun":
//                baseUpload=new UploadUpYun();break;
//            case "qiNiu":
//                baseUpload=new UploadQiNiu();break;
            default:
                break;
        }
    }

    /**
     * 文件上传
     *
     * @param filePath
     * @param fileByte
     * @return
     */
    public ResponseImage upload(String filePath, byte[] fileByte) throws SystemException {

        return baseUpload.upload(filePath, fileByte);

    }
}