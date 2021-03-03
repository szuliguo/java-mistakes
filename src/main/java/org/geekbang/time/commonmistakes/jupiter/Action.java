
package org.geekbang.time.commonmistakes.jupiter;

public class Action {

    private String actionExecutor;
    private String actionStrategy;
    private String entityId;
    private ExecutorProperty executorProperty;

    public String getActionExecutor() {
        return actionExecutor;
    }

    public void setActionExecutor(String actionExecutor) {
        this.actionExecutor = actionExecutor;
    }

    public String getActionStrategy() {
        return actionStrategy;
    }

    public void setActionStrategy(String actionStrategy) {
        this.actionStrategy = actionStrategy;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public ExecutorProperty getExecutorProperty() {
        return executorProperty;
    }

    public void setExecutorProperty(ExecutorProperty executorProperty) {
        this.executorProperty = executorProperty;
    }

}
