
package org.geekbang.time.commonmistakes.jupiter;

import java.util.List;

public class Condition {

    private Long condType;
    private String entityId;
    private String entitySubIds;
    private Long entityType;
    private List<List<String>> expr;

    public Long getCondType() {
        return condType;
    }

    public void setCondType(Long condType) {
        this.condType = condType;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getEntitySubIds() {
        return entitySubIds;
    }

    public void setEntitySubIds(String entitySubIds) {
        this.entitySubIds = entitySubIds;
    }

    public Long getEntityType() {
        return entityType;
    }

    public void setEntityType(Long entityType) {
        this.entityType = entityType;
    }

    public List<List<String>> getExpr() {
        return expr;
    }

    public void setExpr(List<List<String>> expr) {
        this.expr = expr;
    }

}
