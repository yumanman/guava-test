package com.mandy.testGuava.base;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import org.junit.Test;

/**
 * All rights Reserved, Designed By www.lagou.com
 * Title:  TestOptional
 * Package com.mandy.testGuava.base
 * Description:    Optional null处理
 * Date:   2018/6/22 10:22
 *
 * @author mandy
 * @version V1.0
 */
public class TestOptional {
    class People{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Test
    public void optional(){
        //Optional.of(T) 获得一个可能为null的对象
        Optional<Integer> numberNonNull = Optional.of(5);
        //optionalObj.isPresent() 判断是否为null
        Preconditions.checkArgument(numberNonNull.isPresent());

        // Optional.of(null) 会抛出NullPointerException;
        // Optional.fromNullable(null)不会抛出异常
        // Optional<Integer> optionalWithNull = Optional.of(null);
        Optional.fromNullable(null);

        // Optional.absent() 获得一个null引用
        Optional<People> absentObj = Optional.absent();
        Preconditions.checkArgument(!absentObj.isPresent());

        People people=null;
        // Optional.fromNullable(T) 获取一个可能为null的对象
        Optional<People> peopleOptional=Optional.fromNullable(people);
        Preconditions.checkArgument(!peopleOptional.isPresent());
        people=new People();
        peopleOptional=Optional.of(people);

        // optionalObj.orNull() .get() 返回对象
        // 如果为null时orNull返回null;get抛出IllegalStateException异常
        People p1 = peopleOptional.orNull();
        Preconditions.checkArgument(p1!=null);
    }
}
