package com.mandy.testGuava.base;

import com.google.common.base.Splitter;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * test the com.google.common.base.Splitter
 * 解析字符串
 */
public class TestSplitter{

    //字符串分隔
    @Test
    public void testSplitterOn(){
        List<String> list=Splitter.on(",").splitToList("a,b");
        assertThat(list,notNullValue());
        assertThat(list.size(),equalTo(2));
        assertThat(list.get(0),equalTo("a"));
        assertThat(list.get(1),equalTo("b"));
    }

    //字符串分隔忽略空串和trim
    @Test
    public void testSplit_OmitEmpty_trimResult(){
        List<String> list=Splitter.on("|").omitEmptyStrings().splitToList("a| b||");
        assertThat(list,notNullValue());
        assertThat(list.size(),equalTo(2));
        assertThat(list.get(0),equalTo("a"));
        assertThat(list.get(1),equalTo(" b"));
        list=Splitter.on("|").trimResults().omitEmptyStrings().splitToList("a| b||");
        assertThat(list,notNullValue());
        assertThat(list.size(),equalTo(2));
        assertThat(list.get(0),equalTo("a"));
        assertThat(list.get(1),equalTo("b"));
    }

    //字符串按照固定长度节段分隔
    //0000 1111 1234 1233
    @Test
    public void testSplit_fixLength_limit(){
        List<String> list=Splitter.fixedLength(4).splitToList("aaaabbbb");
        assertThat(list,notNullValue());
        assertThat(list.size(),equalTo(2));
        assertThat(list.get(0),equalTo("aaaa"));
        assertThat(list.get(1),equalTo("bbbb"));
        list=Splitter.fixedLength(4).limit(3).splitToList("aaaabbbbccccdddd");
        assertThat(list,notNullValue());
        assertThat(list.size(),equalTo(3));
        assertThat(list.get(0),equalTo("aaaa"));
        assertThat(list.get(1),equalTo("bbbb"));
        assertThat(list.get(2),equalTo("ccccdddd"));
    }

    //字符串按照正则进行分隔
    @Test
    public void testSplitOnPattern(){
        List<String> list=Splitter.onPattern("\\|").trimResults().splitToList("a|b");
        assertThat(list,notNullValue());
        assertThat(list.size(),equalTo(2));
        assertThat(list.get(0),equalTo("a"));
        assertThat(list.get(1),equalTo("b"));
    }

    //字符串解析成map
    @Test
    public void testSplitToMap(){
        Map<String,String> map=Splitter.on("|").withKeyValueSeparator("=").split("a=A|b=B");
        assertThat(map, notNullValue());
        assertThat(map.size(),equalTo(2));
        assertThat(map.get("a"),equalTo("A"));
        assertThat(map.get("b"),equalTo("B"));
    }
}
