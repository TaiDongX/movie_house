package com.ws.movie_house;

import com.ws.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.22 10:38
 * @since JDK 1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test(){

        ValueOperations<String, User> redis =  redisTemplate.opsForValue();
        User user1 = redis.get("user");
        System.out.println(user1 == null);


    }
}
