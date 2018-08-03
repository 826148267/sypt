package com.gzf.sypt.util;

import com.gzf.sypt.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author guozifan
 * @Description: ${todo}
 * @date 2018/6/13 0:07
 */
public class UserIdBuilderTest extends BaseTest {
    @Autowired
    private UserIdBuilder userIdBuilder;

    @Test
    public void testCreateUserId(){
        long now = System.currentTimeMillis();
        HashSet hashSet = new HashSet();
        for (int i=0;i<2000;i++) {
            try {
                long id = userIdBuilder.createUserId();
                hashSet.add(id);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        System.out.println("set的长度" + hashSet.size());
        Iterator iterator = hashSet.iterator();
        int i = 0;
        while (iterator.hasNext()){
            i++;
            System.out.println("这是第" + i + "个:" + iterator.next());
        }
        System.out.println(System.currentTimeMillis() - now);
    }
}
