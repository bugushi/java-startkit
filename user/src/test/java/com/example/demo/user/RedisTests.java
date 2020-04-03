package com.example.demo.user;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void testSet() {
        stringRedisTemplate.opsForValue().set("test-string-value", "Hello Redis");
    }

    @Test
    public void testGet() {
        String value = stringRedisTemplate.opsForValue().get("test-string-value");
        System.out.println(value);
    }

    @Test
    public void testSetTimeOut() {
        stringRedisTemplate.opsForValue().set("test-string-key-time-out", "Hello Redis", 3, TimeUnit.HOURS);
    }

    @Test
    public void testDeleted() {
        stringRedisTemplate.delete("test-string-value");
    }

    @Test
    public void testLeftPush() {
        redisTemplate.opsForList().leftPush("TestList", "TestLeftPush");
    }

    @Test
    public void testRightPush() {
        redisTemplate.opsForList().rightPush("TestList", "TestRightPush");
    }

    @Test
    public void testLeftPop() {
        Object leftFirstElement = redisTemplate.opsForList().leftPop("TestList");
        System.out.println(leftFirstElement);
    }

    @Test
    public void testRightPop() {
        Object rightFirstElement = redisTemplate.opsForList().rightPop("TestList");
        System.out.println(rightFirstElement);
    }

    @Test
    public void testPutHash() {
        redisTemplate.opsForHash().put("TestHash", "FirstElement", "Hello,Redis hash.");
        Assert.assertTrue(redisTemplate.opsForHash().hasKey("TestHash", "FirstElement"));
    }

    @Test
    public void testGetHash() {
        Object element = redisTemplate.opsForHash().get("TestHash", "FirstElement");
        Assert.assertEquals("Hello,Redis hash.", element);
    }

    @Test
    public void testDelHash() {
        redisTemplate.opsForHash().delete("TestHash", "FirstElement");
        Assert.assertFalse(redisTemplate.opsForHash().hasKey("TestHash", "FirstElement"));
    }

    @Test
    public void testAddSet() {
        redisTemplate.opsForSet().add("TestSet", "e1", "e2", "e3");
        long size = redisTemplate.opsForSet().size("TestSet");
        Assert.assertEquals(3L, size);
    }

    @Test
    public void testGetSet() {
        Set<String> testSet = redisTemplate.opsForSet().members("TestSet");
        System.out.println(testSet);
    }

    @Test
    public void testRemoveSet() {
        redisTemplate.opsForSet().remove("TestSet", "e1", "e2");
        Set testSet = redisTemplate.opsForSet().members("TestSet");
        Assert.assertEquals("e3", testSet.toArray()[0]);
    }
}
