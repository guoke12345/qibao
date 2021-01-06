package com.qibao.test; /**
 * BBD Service Inc
 * All Rights Reserved @2018
 */

import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 用于加载测试环境的 SpringBootApplication
 * just for test
 *
 * @author leckie
 * @version $Id: TestApplication.java, v 0.1 2018年2月9日 下午3:45:55 leckie Exp $
 */
@SpringBootApplication
public class TestApplication {
    @Test
    public void yesy() {
        System.out.println("OK");
    }

}
