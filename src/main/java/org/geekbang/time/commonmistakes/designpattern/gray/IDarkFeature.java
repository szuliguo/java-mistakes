package org.geekbang.time.commonmistakes.designpattern.gray;

/**
 * @author Legal
 * @date 2020/10/14
 * 灰度规则接口
 */
public interface IDarkFeature {

    boolean enabled();

    boolean dark(long darkTarget);

    boolean dark(String darkTarget);
}
