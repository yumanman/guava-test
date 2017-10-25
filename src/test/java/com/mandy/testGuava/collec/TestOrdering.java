package com.mandy.testGuava.collec;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @Title: TestOrdering
 * @Package com.mandy.testGuava.collec
 * @Description: study guava中的排序(用一句话描述该文件做什么)
 * @author: Mandy
 * @date: 2017/10/18
 * Ordering 是一个特殊的Comparator实例，把Comparator的静态方法包装成
 * 自己的实例方法，并且提供链式调用方法
 */
public class TestOrdering {

    @Test
    public void testStaticOrdering(){
        ArrayList<String> list = Lists.newArrayList("ac", "bd", "1a", "aa", "dd");
        assertThat(list.toString(),equalTo("[ac, bd, 1a, aa, dd]"));
        Ordering<Comparable> naturalOrdering = Ordering.natural();
        Ordering<Object> usingToStringOrdering = Ordering.usingToString();
        Ordering<Object> arbitraryOrdering = Ordering.arbitrary();
        assertThat(naturalOrdering.sortedCopy(list).toString(),equalTo("[1a, aa, ac, bd, dd]"));
        assertThat(usingToStringOrdering.sortedCopy(list).toString(),equalTo("[1a, aa, ac, bd, dd]"));
        System.out.println(arbitraryOrdering.sortedCopy(list).toString());
        assertThat(naturalOrdering.greatestOf(list,2).toString(),equalTo("[dd, bd]"));
        assertThat(naturalOrdering.leastOf(list,2).toString(),equalTo("[1a, aa]"));

        assertThat(naturalOrdering.isOrdered(list),equalTo(false));
        List<String> naturalOrderList = naturalOrdering.sortedCopy(list);
        assertThat(naturalOrdering.isOrdered(naturalOrderList),equalTo(true));
    }

    @Test
    public void testOrderingOnResultOf(){
        Ordering<TestObject> ordering=Ordering.natural().nullsFirst()
                .onResultOf(new Function<TestObject, Comparable>() {
                    @Override
                    public Comparable apply(TestObject input) {
                        return input.getLength();
                    }
                });
        int compare = ordering.compare(new TestObject("a", 1),
                new TestObject("b", 3));
        assertThat(compare,equalTo(-1));
    }

}

class TestObject{
    String name;
    int length;

    public TestObject(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}