package com.mandy.testGuava.io;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @Title: TestFiles
 * @Package com.mandy.testGuava.io
 * @Description: study gauva 里的File操作(用一句话描述该文件做什么)
 * @author: Mandy
 * @date: 2017/10/18
 */
public class TestFiles {
    private static final String PATH_FROM="D:\\workspace\\guava-test\\src\\test\\resources\\com.mandy.testGuava.io\\TestFilesFrom.txt";
    private static final String PATH_TARGET="D:\\workspace\\guava-test\\src\\test\\resources\\com.mandy.testGuava.io\\TestFilesTarget.txt";
    private static final String TXT_MSG="Hello world!";

    @Test
    public void test() throws IOException {
        File fileFrom=new File(PATH_FROM);
        //删除文件
        fileFrom.delete();
        //新建文件
        Files.touch(fileFrom);
        assertThat(fileFrom.exists(),equalTo(true));
        //写入文件
        Files.write(TXT_MSG,fileFrom, Charsets.UTF_8);
        //按行读文件
        List<String> stringList = Files.readLines(fileFrom, Charsets.UTF_8);
        assertThat(stringList.toString(),equalTo("[Hello world!]"));
        File fileTarget=new File(PATH_TARGET);
        fileTarget.delete();
        assertThat(fileTarget.exists(),equalTo(false));
        //拷贝文件
        Files.copy(fileFrom,fileTarget);
        assertThat(fileTarget.exists(),equalTo(true));
        //对文件生成hashCode
        HashCode hashCode=Files.hash(fileFrom, Hashing.md5());
        HashCode hashCodeTaget=Files.hash(fileTarget,Hashing.md5());
        assertThat(hashCodeTaget.equals(hashCode),equalTo(true));
        assertThat(hashCode.toString(),equalTo("86fb269d190d2c85f6e0468ceca42a20"));

    }


    public void destory(){
        File fileFrom=new File(PATH_FROM);
        File fileTarget=new File(PATH_TARGET);
        //在程序退出的时候删除文件
        fileFrom.deleteOnExit();
        fileTarget.deleteOnExit();
    }
}
