package org.geekbang.time.commonmistakes.designpattern.gray;

import java.util.List;

/**
 * @author Legal
 * @date 2020/10/14
 */
public class DarkRuleConfig {

    private List<DarkFeatureConfig> features;

    public DarkRuleConfig(List<DarkFeatureConfig> features) {
        this.features = features;
    }

    public List<DarkFeatureConfig> getFeatures() {
        return features;
    }

    public void setFeatures(List<DarkFeatureConfig> features) {
        this.features = features;
    }

    public static class DarkFeatureConfig {

        private String key;
        private boolean enabled;
        private String rule;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getRule() {
            return rule;
        }

        public void setRule(String rule) {
            this.rule = rule;
        }
    }
}
