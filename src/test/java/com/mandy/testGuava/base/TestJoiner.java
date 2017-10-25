package com.mandy.testGuava.base;

import com.google.common.base.Joiner;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * test com.google.common.base.Joiner
 * 合并字符串
 */
public class TestJoiner {

    //合并集合为字符串
    @Test
    public void testJoinerOnJoin(){
        String a=Joiner.on(",").join(Arrays.asList("a","b","c"));
        Assert.assertTrue("a,b,c".equals(a));
    }

    //合并时跳过null
    @Test
    public void testJoinSkipNulls(){
        String a=Joiner.on(",").skipNulls().join(Arrays.asList("a",null,"c"));
        Assert.assertTrue("a,c".equals(a));
    }

    //将集合合并输出到appendable对象中，比如Stringbuffer，FileStream。。。
    @Test
    public void testJoinOnAppendTo(){
        try {
            StringBuffer sb1=new StringBuffer("c");
            StringBuffer sb2=Joiner.on(",").appendTo(sb1,new String[]{"a","b"});
            Assert.assertTrue(sb1.equals(sb2));
            Assert.assertTrue(sb1.toString().equals("ca,b"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //合并map
    @Test
    public void testJoinerOnMap(){
        Map<String,String> map=new HashMap<String,String>();
        map.put("a","A");
        map.put("b",null);
        map.put("c","C");
        //String a=Joiner.on(",").withKeyValueSeparator("=").join(map);
        // will throw NullPointerException
        // and skipNulls can not use on map
        String a=Joiner.on(",").useForNull("m").withKeyValueSeparator("=").join(map);
        Assert.assertTrue("a=A,b=m,c=C".equals(a));
    }


}
