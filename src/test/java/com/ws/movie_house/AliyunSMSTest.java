package com.ws.movie_house;

import com.aliyuncs.exceptions.ClientException;
import com.ws.Util.AliyunSMSUtil;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.23 10:12
 * @since JDK 1.8
 */
public class AliyunSMSTest {

    /**
     * 测试短信功能能否正常使用
     * @throws ClientException
     */
    @Test
    public void test() throws ClientException {
        AliyunSMSUtil.sendSms("15631106706", "abingiloveyou");
    }

    /**
     * 使用随机数生成验证码
     */
    @Test
    public void test2(){
        Random rand = new Random();
        int n = 1000;
        while(n > 0){
            System.out.println(rand.nextInt(899999) + 100000);
            n--;
        }

    }

}
