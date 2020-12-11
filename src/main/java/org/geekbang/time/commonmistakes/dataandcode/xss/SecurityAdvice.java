package org.geekbang.time.commonmistakes.dataandcode.xss;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.util.HtmlUtils;

import java.beans.PropertyEditorSupport;

/**
 * controllerAdvice 最常使用的功能就是 全局异常处理。
 * 就是对于controller层中没有捕获到的异常，那么就会被
 * 由controllerAdvice注解标注的类进行捕获到，并且进行处理。
 */
@ControllerAdvice
public class SecurityAdvice {

    /**
     * InitBinder 用于在 @controller 中标注于方法中，表示为当前控制器注册一个属性编辑器或者是其它。
     * 只对当前的controller是有效的。
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public String getAsText() {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }

            @Override
            public void setAsText(String text) {
                setValue(text == null ? null : HtmlUtils.htmlEscape(text));
            }
        });
    }
}
