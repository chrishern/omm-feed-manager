package net.chris.api.model.output;


import java.util.Map;

public class OutboundMessage {

    private OutboundMarket[] markets;
    private Map<String, Object> metadata;

    public OutboundMessage() {

    }

    public OutboundMessage(OutboundMarket[] markets, Map<String, Object> metadata) {
        this.markets = markets;
        this.metadata = metadata;
    }

    public OutboundMarket[] getMarkets() {
        return this.markets;
    }

    public Map<String, Object> getMetadata() {
        return this.metadata;
    }
}
