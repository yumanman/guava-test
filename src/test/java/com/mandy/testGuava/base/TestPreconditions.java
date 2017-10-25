package com.mandy.testGuava.base;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @link com.google.common.base.Preconditions
 * 前置条件判断
 */
public class TestPreconditions {

    //检查value是否为null ，是则抛出nullPointerException,否则直接返回value
    @Test(expected = NullPointerException.class)
    public void testCheckNotNull(){
        Preconditions.checkNotNull(null);
    }

    @Test
    public void testCheckNotNullWithMsg(){
        try{
            Preconditions.checkNotNull(null,"The list must not be null");
        }catch (Exception e){
            assertThat(e,is(NullPointerException.class));
            assertThat(e.getMessage(),equalTo("The list must not be null"));
        }
    }

    //判断表达式
    @Test
    public void testCheckArgument(){
        final String type="A";
        try{
            Preconditions.checkArgument(type.equals("B"));
        }catch (Exception e){
            assertThat(e,is(IllegalArgumentException.class));
        }
    }

    //判断状态
    @Test
    public void testCheckState(){
        final String state="A";
        try{
            Preconditions.checkState(state.equals("B"));
        }catch (Exception e){
            assertThat(e,is(IllegalStateException.class));
        }
    }

    //判断集合下标
    @Test
    public void testCheckIndex(){
        try{
            List<String> list= ImmutableList.of();
            Preconditions.checkElementIndex(10,list.size());
        }catch (Exception e){
            assertThat(e,is(IndexOutOfBoundsException.class));
        }
    }

}
