package com.qibao.core.common.fileManage;


import com.qibao.core.common.exception.SystemException;
import com.qibao.core.common.model.ResponseImage;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class UploadLocalServer implements BaseUpload{
    private static Logger logger = LoggerFactory.getLogger(UploadLocalServer.class);

    /**
     * 文件上传
     * @param filePath
     * @param fileByte
     * @return
     */
    @Override
    public ResponseImage upload(String filePath, byte[] fileByte) throws SystemException {
        //将字节数组存入本地
        try {
            FileUtils.writeByteArrayToFile(new File(filePath), fileByte);
        } catch (IOException e) {
            logger.error(e.toString());
            throw new SystemException(e);
        }
        return null;
    }
}