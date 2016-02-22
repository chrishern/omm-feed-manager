package net.chris.feed;

/**
 * Strongly typed representation of variable data received from the Caerus Feed.
 */
public class FeedVariable {

    private String name;
    private String value;
    private String period;
    private int gameSeconds;
    private String supplierName;
    private Long updateTime;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getPeriod() {
        return period;
    }

    public int getGameSeconds() {
        return gameSeconds;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    protected FeedVariable() {
    }

    public FeedVariable(String name, String value, String period, int gameSeconds, String supplierName, Long updateTime) {
        this.name = name;
        this.value = value;
        this.period = period;
        this.gameSeconds = gameSeconds;
        this.supplierName = supplierName;
        this.updateTime = updateTime;
    }
}


