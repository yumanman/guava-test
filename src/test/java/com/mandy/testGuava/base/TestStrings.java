package com.mandy.testGuava.base;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import org.junit.Test;
import java.nio.charset.Charset;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * test the com.google.common.base.Strings
 * Strings
 * CharSet
 * CharMatch
 */
public class TestStrings {

    @Test
    public void testStringsMethod(){
        //空串改null
        assertThat(Strings.emptyToNull(""),nullValue());
        //null改空串
        assertThat(Strings.nullToEmpty(null),equalTo(""));
        assertThat(Strings.nullToEmpty("abc"),equalTo("abc"));
        //取两个字符串公共前缀
        assertThat(Strings.commonPrefix("hello","hi"),equalTo("h"));
        //取两个字符串公共后缀
        assertThat(Strings.commonSuffix("hello","po"),equalTo("o"));
        //判断是null或空
        assertThat(Strings.isNullOrEmpty(""),equalTo(true));
        //获得重复字符串结果
        assertThat(Strings.repeat("a",3),equalTo("aaa"));
        //将字符串最少长度右边补齐
        assertThat(Strings.padEnd("Mandy",3,'a'),equalTo("Mandy"));
        assertThat(Strings.padEnd("a",3,'0'),equalTo("a00"));
        //将字符串最少长度左边补齐
        assertThat(Strings.padStart("a",3,'0'),equalTo("00a"));
    }

    @Test
    public void testCharSet(){
        //直接枚举获得字符集
        Charset charset=Charset.forName("UTF-8");
        assertThat(charset.equals(Charsets.UTF_8),equalTo(true));
    }

    @Test
    public void testCharMatch(){
        //数字格式匹配判断
        assertThat(CharMatcher.javaDigit().matches('5'),equalTo(true));
        assertThat(CharMatcher.javaDigit().matches('a'),equalTo(false));
        //字符串内字符统计
        assertThat(CharMatcher.is('A').countIn("Mandy A"),equalTo(1));
        //匹配字符串中的空格，并替换
        assertThat(CharMatcher.breakingWhitespace().collapseFrom("a b",'*'),equalTo("a*b"));
        assertThat(CharMatcher.javaDigit().or(CharMatcher.whitespace()).collapseFrom("  abc12",'*'),equalTo("*abc*"));
        assertThat(CharMatcher.javaDigit().or(CharMatcher.whitespace()).trimAndCollapseFrom(" a bc12",'*'),equalTo("a*bc"));
    }
}
