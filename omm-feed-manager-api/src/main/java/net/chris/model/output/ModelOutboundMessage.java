package net.chris.model.output;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.google.common.base.MoreObjects;

public class ModelOutboundMessage implements Serializable {

    private List<OutboundMarket> markets;
    private Map<String, Object> metadata;

    public ModelOutboundMessage() {

    }

    public ModelOutboundMessage(List<OutboundMarket> markets, Map<String, Object> metadata) {
        this.markets = markets;
        this.metadata = metadata;
    }

    public List<OutboundMarket> getMarkets() {
        return this.markets;
    }

    public Map<String, Object> getMetadata() {
        return this.metadata;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("markets", markets)
                .add("metadata", metadata)
                .toString();
    }
}
