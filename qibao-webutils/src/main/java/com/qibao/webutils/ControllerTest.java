package com.qibao.webutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 启动测试类
 * @version 1.0.0
 * @Description:
 * @author: weichao 2019/2/21 17 25
 */
@RestController
public class ControllerTest {
    Logger logger = LoggerFactory.getLogger(ControllerTest.class);

    @RequestMapping("/test")
    public String test(){
        logger.info("test Controller");
        return  "index12";
    }
}
