
package org.geekbang.time.commonmistakes.jupiter;

import com.alibaba.fastjson.JSON;
import org.thymeleaf.util.MapUtils;

import java.util.*;

public class DpForward {

    private List<Action> actions;
    private List<Condition> conditions;
    private Boolean enabled;
    private Long matchType;
    private String name;
    private String requestSource;
    private Long ruleType;
    private String uid;

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getMatchType() {
        return matchType;
    }

    public void setMatchType(Long matchType) {
        this.matchType = matchType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequestSource() {
        return requestSource;
    }

    public void setRequestSource(String requestSource) {
        this.requestSource = requestSource;
    }

    public Long getRuleType() {
        return ruleType;
    }

    public void setRuleType(Long ruleType) {
        this.ruleType = ruleType;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    @Override
    public String toString() {
        return "DpForward{" +
                "actions=" + actions +
                ", conditions=" + conditions +
                ", enabled=" + enabled +
                ", matchType=" + matchType +
                ", name='" + name + '\'' +
                ", requestSource='" + requestSource + '\'' +
                ", ruleType=" + ruleType +
                ", uid='" + uid + '\'' +
                '}';
    }

    public static void main(String[] args) {
        String dp = "{\"name\":\"danfoss温控阀业务dp点转发\",\"requestSource\":\"mage\",\"uid\":\"mage\",\"matchType\":1,\"enabled\":true,\"ruleType\":5,\"conditions\":[{\"entityType\":1,\"condType\":2,\"entityId\":\"den6kzk0\",\"entitySubIds\":\"123\",\"expr\":[[\"$dp123\"]]}],\"actions\":[{\"actionStrategy\":\"repeat\",\"entityId\":\"jupiter_device_danfoss_data\",\"actionExecutor\":\"dpForward\",\"executorProperty\":{\"bizCode\":\"preHeatStatusChange\",\"isString\":true}}]}";
        DpForward ruleVO = JSON.parseObject(dp, DpForward.class);
        System.out.println(ruleVO.toString());


        List<Integer> list = new ArrayList<>();
        list.stream()
                .filter(a -> a > 2)
                .iterator();

        Map<String, Object> map = new HashMap<>();
        map.put("preHotTask", true);
        boolean isPreHeat =  (boolean) Optional.ofNullable(map.get("preHotTask")).orElse(false);
        System.out.println(isPreHeat);


    }

}
