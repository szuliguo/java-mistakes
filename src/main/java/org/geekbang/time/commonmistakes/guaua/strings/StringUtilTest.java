package org.geekbang.time.commonmistakes.guaua.strings;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Legal
 * @date 2020/12/22
 * http://bins.top/teh/2019/03/26/Google-Guava-usage.html
 */
public class StringUtilTest {

    @Test
    public void JoinerTest() throws IOException {

        /**
         * 连接器
         */

        List<String> list = Lists.newArrayList("a", null, "b");
        /**
         * result : a|b
         */
        System.out.println(Joiner.on("|").skipNulls().join(list));
        /**
         * result : a|none|b
         */
        System.out.println(Joiner.on("|").useForNull("none").join(list));
        /**
         * result : a/b/
         */
        System.out.println(Joiner.on("/").join("a", "b", ""));

        FileWriter fileWriter = new FileWriter(new File("/Users/legal/Desktop/test2"));
        Joiner.on("#").useForNull(" ").appendTo(fileWriter, list);
        fileWriter.flush();
        fileWriter.close();


        Map<String, String> map = Maps.newLinkedHashMap();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        //k1=v1&k2=v2&k3=v3
        System.out.println(Joiner.on("&").withKeyValueSeparator("=").join(map));

        ImmutableMap<String, String> map2 = ImmutableMap.of("id", "1", "name", "jack");
        //id=1&name=jack
        System.out.println(Joiner.on("&").withKeyValueSeparator("=").join(map2));
    }

    @Test
    public void splitterTest() {

        /**
         * 拆分器
         */

        Map<String, String> splitMap = Splitter.on("&").withKeyValueSeparator("=").split("id=1&name=jack");
        // {id=1, name=jack}
        System.out.println(splitMap.toString());
        //[foo, bar, , baz]
        System.out.println(Splitter.on('|').trimResults().split("foo|bar ||baz "));
        Lists.newArrayList(Splitter.on('|').split("foo|bar ||baz "));
    }

    @Test
    public void stringUtilTest() {

        //向右填充x
        System.out.println(Strings.padEnd("12345", 10, 'x'));
       //向左填充x
        System.out.println(Strings.padStart("12345", 10, 'x'));
      //判断字符串是否为空
        System.out.println(Strings.isNullOrEmpty(null));
     //生成重复字符串
        System.out.println(Strings.repeat("123", 3));
     //获取左边公共字符串
        System.out.println(Strings.commonPrefix("abc123", "abc456"));
      //获取右边公共字符串
        System.out.println(Strings.commonSuffix("123abc", "456abc"));

    }

    @Test
    public void matcherTest() {

        String string = "123control45 ";
        //移除control字符
        String noControl = CharMatcher.javaIsoControl().removeFrom(string);
        //只保留数字字符
        String theDigits = CharMatcher.digit().retainFrom(string);
        //用*号替换所有数字
        String noDigits = CharMatcher.javaDigit().replaceFrom(string, "*");
        // 只保留数字和小写字母
        String lowerAndDigit = CharMatcher.javaDigit().or(CharMatcher.javaLowerCase()).retainFrom(string);

        System.out.println(string);

    }

}
