package net.chris.model.output;


import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

import com.google.common.base.MoreObjects;
import com.williamhill.trading.rnd.frameworks.model.api.market.Selection;
import com.williamhill.trading.rnd.frameworks.model.api.market.enums.DisplayStatus;
import com.williamhill.trading.rnd.frameworks.model.api.market.enums.PublishingStatus;
import com.williamhill.trading.rnd.frameworks.model.api.market.enums.SelectionResult;
import com.williamhill.trading.rnd.frameworks.model.api.market.enums.SelectionType;
import com.williamhill.trading.rnd.frameworks.model.domain.template.market.SelectionTemplate;

public class OutboundSelection implements Serializable {

    public static OutboundSelection from(Selection s, SelectionTemplate t) {
        return new OutboundSelection( //
                s.getName(), //
                s.getType(), //
                s.getPublishingStatus(), //
                s.getDisplayStatus(), //
                s.hasProbability() ? s.getProbability().value() : null, //
                s.getOverroundedDecimalPrice() != null ? s.getOverroundedDecimalPrice().value() : null, //
                s.hasFractionalPrice() ? s.getFractional().getValue() : null, //
                s.getResult(), //
                t.getMetadata() //
        );
    }

    private String name;
    private SelectionType type;
    private PublishingStatus publishingStatus;
    private DisplayStatus displayStatus;
    private Double probability;
    private Double overroundedDecimalPrice;
    private String fractional;
    private SelectionResult result;
    private Map<String, Object> metadata;

    public OutboundSelection() {

    }

    private OutboundSelection( //
                               String name, //
                               SelectionType type, //
                               PublishingStatus publishingStatus, //
                               DisplayStatus displayStatus, //
                               Double probability, //
                               Double overroundedDecimalPrice, //
                               String fractional, //
                               SelectionResult result, //
                               Map<String, Object> metadata //
    ) {
        this.name = name;
        this.type = type;
        this.publishingStatus = publishingStatus;
        this.displayStatus = displayStatus;
        this.probability = probability;
        this.overroundedDecimalPrice = overroundedDecimalPrice;
        this.fractional = fractional;
        this.result = result;
        this.metadata = Collections.unmodifiableMap(metadata);
    }

    public OutboundSelection copyWithNewName(String newName) {
        return new OutboundSelection(newName, this.type, this.publishingStatus, this.displayStatus, this.probability, this.overroundedDecimalPrice, this.fractional, this.result, this.metadata);
    }

    public String getName() {
        return this.name;
    }

    public SelectionType getType() {
        return this.type;
    }

    public PublishingStatus getPublishingStatus() {
        return this.publishingStatus;
    }

    public DisplayStatus getDisplayStatus() {
        return this.displayStatus;
    }

    public Double getProbability() {
        return this.probability;
    }

    public Double getOverroundedDecimalPrice() {
        return this.overroundedDecimalPrice;
    }

    public String getFractional() {
        return this.fractional;
    }

    public SelectionResult getResult() {
        return this.result;
    }

    public Map<String, Object> getMetadata() {
        return this.metadata;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("type", type)
                .add("publishingStatus", publishingStatus)
                .add("displayStatus", displayStatus)
                .add("probability", probability)
                .add("publishingStatus", publishingStatus)
                .add("overroundedDecimalPrice", overroundedDecimalPrice)
                .add("fractional", fractional)
                .add("result", result)
                .add("metadata", metadata)
                .toString();
    }
}
