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
 * All rights Reserved, Designed By www.lagou.com
 * Title:  TestOrdering
 * Package com.mandy.testGuava.collec
 * Description:    排序
 * Date:   2018/6/22 10:42
 *
 * @author mandy
 * @version V1.0
 */
public class TestOrdering {
    @Test
    public void testStaticOrdering(){
        ArrayList<String> list = Lists.newArrayList("ac", "bd", "1a", "aa", "dd");
        assertThat(list.toString(),equalTo("[ac, bd, 1a, aa, dd]"));
        // Ordering.natural() 自然顺序排序
        // Ordering.usingToString() 用对象的字符串形式排序
        // Ordering.arbitrary() 乱序
        Ordering<Comparable> naturalOrdering = Ordering.natural();
        Ordering<Object> usingToStringOrdering = Ordering.usingToString();
        Ordering<Object> arbitraryOrdering = Ordering.arbitrary();
        // orderingObj.sortedCopy(iteratorObj) 对集合排序
        assertThat(naturalOrdering.sortedCopy(list).toString(),equalTo("[1a, aa, ac, bd, dd]"));
        assertThat(usingToStringOrdering.sortedCopy(list).toString(),equalTo("[1a, aa, ac, bd, dd]"));
        System.out.println(arbitraryOrdering.sortedCopy(list).toString());
        // orderingObj.greatestOf(iteratorObj,n) 对集合排序并取最前面的n个
        assertThat(naturalOrdering.greatestOf(list,2).toString(),equalTo("[dd, bd]"));
        // orderingObj.leastOf(iteratorObj,n) 对集合排序并取最后面的n个
        assertThat(naturalOrdering.leastOf(list,2).toString(),equalTo("[1a, aa]"));
        // orderingObj.isOrdered(iteratorObj) 判断集合是否已排序
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
}
