package com.mandy.testGuava.collec;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;


/**
 * @Title: TestList
 * @Package com.mandy.testGuava.collec
 * @Description: study guava里的list
 * @author: Mandy
 * @date: 2017/10/18
 */
public class TestList {
    //guava内部使用静态工厂方法代替构造器

    @Test
    public void testArrayList(){
        //构造一个可变的、空的ArrayList实例。
        ArrayList<String> list= Lists.newArrayList();
        assertThat(list,notNullValue());
        assertThat(list.size(),equalTo(0));
        //构造一个可变的、初始化元素的ArrayList实例
        list=Lists.newArrayList("a","b","c");
        assertThat(list,notNullValue());
        assertThat(list.size(),equalTo(3));
        assertThat(list.get(0),equalTo("a"));
        assertThat(list.get(1),equalTo("b"));
        assertThat(list.get(2),equalTo("c"));
        //new ArrayList<E>(initialArraySize);
        ArrayList<String> listWithCapacity=Lists.newArrayListWithCapacity(1);
        listWithCapacity.addAll(list);
        assertThat(listWithCapacity.size(),equalTo(3));
        //new ArrayList<E>(computeArrayListCapacity(estimatedSize))
        ArrayList<String> listWithExpectedSize=Lists.newArrayListWithExpectedSize(2);
        listWithExpectedSize.addAll(list);
        assertThat(listWithExpectedSize.size(),equalTo(3));
    }

    @Test
    public void testLinkedList(){
        //构造一个可变的、空的LinkedList实例。
        LinkedList<String> linkedList=Lists.newLinkedList();
        assertThat(linkedList,notNullValue());
        assertThat(linkedList.size(),equalTo(0));
        //构造一个可变的、初始化元素的LinkedList实例
        ArrayList<String> list=Lists.newArrayList("a","b","c");
        linkedList=Lists.newLinkedList(list);
        assertThat(linkedList,notNullValue());
        assertThat(linkedList.size(),equalTo(3));
        assertThat(linkedList.get(0),equalTo("a"));
        assertThat(linkedList.get(1),equalTo("b"));
        assertThat(linkedList.get(2),equalTo("c"));
    }

    @Test
    public void testAsList(){
        String str = "abc";
        String[] strs = {"a", "b","c"};
        /**
         * asList：返回一个不可变的List
         * 其中包含指定的第一个元素和附加的元素数组组成
         * 修改这个数组将反映到返回的List上
         */
        List<String> list = Lists.asList(str, strs);
        assertThat(Joiner.on(",").join(list),equalTo("abc,a,b,c"));
        strs[1]="d";
        assertThat(Joiner.on(",").join(list),equalTo("abc,a,d,c"));

    }

    @Test
    public void testPartition(){
        String[] strs="abc".split("");
        List<String> list = Arrays.asList(strs);
        assertThat(Joiner.on(",").join(list),equalTo("a,b,c"));
        /**
         * partition：根据size传入的List进行切割，切割成符合要求的小的List
         * 并将这些小的List存入一个新的List对象中返回
         */
        List<List<String>> lists = Lists.partition(list, 1);
        assertThat(lists.toString(),equalTo("[[a], [b], [c]]"));
    }

    @Test
    public void testTransform(){
        /**
         * transform：根据传进来的function对fromList进行相应的处理
         * 并将处理得到的结果存入到新的list对象中返回
                */
        List<String> list=Lists.newArrayList("a","b","c");;
        List<String> newList = Lists.transform(list, new Function<String, String>() {
            @Override
            public String apply(String input) {
                //这里简单的对集合中的元素转换为大写
                return input.toUpperCase();
            }
        });
        assertThat(Joiner.on(",").join(newList),equalTo("A,B,C"));
    }

    @Test
    public void testCharactersOf(){
        /**
         * charactersOf：将传进来的String或者CharSequence分割为单个的字符
         * 并存入到一个ImmutableList对象中返回
         * ImmutableList：一个高性能、不可变的、随机访问列表的实现
         */
        ImmutableList<Character> characters = Lists.charactersOf("abc");
        assertThat(characters.toString(),equalTo("[a, b, c]"));
    }

    @Test
    public void testReverse(){
        //返回一个传入List内元素倒序后的List
        String[] strs={"a","b","c"};
        List<String> list = Lists.newArrayList(strs);
        List<String> reverse = Lists.reverse(list);
        assertThat(Joiner.on(",").join(reverse),equalTo("c,b,a"));
    }
}
