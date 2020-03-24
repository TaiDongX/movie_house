package com.ws.movie_house;

import com.ws.Util.AliYunOSSUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.22 11:29
 * @since JDK 1.8
 */

public class AliyunOSSTest {

    @Test
    public void test(){
        File file = new File("D:\\素材\\movies");		//获取其file对象
        File[] fs = file.listFiles();	//遍历path下的文件和目录，放在File数组中
        for(File f : fs){					//遍历File[]数组
            if(!f.isDirectory())		//若非目录(即文件)，则打印
                AliYunOSSUtil.upLoad(f,f.getName());
        }


    }
    @Test
    public void test2(){
        File file = new File("D:\\素材\\actors");		//获取其file对象
        File[] fs = file.listFiles();	//遍历path下的文件和目录，放在File数组中
        for(File f : fs){					//遍历File[]数组
            if(!f.isDirectory())		//若非目录(即文件)，则打印
                AliYunOSSUtil.upLoad(f,f.getName());
        }
    }
}
