package org.geekbang.time.commonmistakes.json.jsonpath;

import com.alibaba.fastjson.JSONPath;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.function.Function;

/**
 * @author Lega
 * @date 2021/9/11
 */
public class JsonPathTest {

    @Test
    public void jsonPathTest() {
        SubDevice subDevice = new SubDevice("sub");
        Device object = new Device("abc", subDevice);
        Function<Object, Object> function = compileJsonPath("subDevice.sub");
        System.out.println(function.apply(object));
    }

    /**
     * 编译jsonpath为function对象。
     * @param jsonPath
     * @return
     */
    private  Function<Object, Object> compileJsonPath(String jsonPath) {
        if (StringUtils.isEmpty(jsonPath)) {
            return null;
        }
        JSONPath jsonPathObject = JSONPath.compile(jsonPath);
        return jsonPathObject::eval;
    }

}
