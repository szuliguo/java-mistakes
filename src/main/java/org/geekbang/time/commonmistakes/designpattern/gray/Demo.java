package org.geekbang.time.commonmistakes.designpattern.gray;

/**
 * @author Legal
 * @date 2020/10/14
 * 测试
 */
public class Demo {
    public static void main(String[] args) {
        DarkLaunch darkLaunch = new DarkLaunch();
        darkLaunch.addProgrammedDarkFeature("user_promotion", new UserPromotionDarkRule());
        IDarkFeature darkFeature = darkLaunch.getDarkFeature("user_promotion");
        System.out.println(darkFeature.enabled());
        System.out.println(darkFeature.dark(83));
    }
}
