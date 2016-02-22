package net.chris.api.model.output;


import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.williamhill.trading.rnd.frameworks.model.api.market.Market;
import com.williamhill.trading.rnd.frameworks.model.api.market.enums.DisplayStatus;
import com.williamhill.trading.rnd.frameworks.model.api.market.enums.MarketStatus;
import com.williamhill.trading.rnd.frameworks.model.api.market.enums.PublishingStatus;
import com.williamhill.trading.rnd.frameworks.model.api.market.values.HandicapResult;
import com.williamhill.trading.rnd.frameworks.model.domain.template.market.MarketTemplate;
import com.williamhill.trading.rnd.frameworks.model.domain.template.market.properties.GtpClass;

public class OutboundMarket {

    public static OutboundMarket from(Market m, MarketTemplate t) {
        return new OutboundMarket( //
                m.getName(), //
                t.getGtpClass(), //
                m.isInplay(), //
                t.isCashoutAvailable(), //
                m.getStatus(), //
                m.getPublishingStatus(), //
                m.getDisplayStatus(), //
                m.getHandicapValue(), //
                m.getHandicapResult(), //
                OutboundSelections.from(m.getSelections(), t.getSelectionTemplates()), //
                t.getMetadata());
    }

    private String name;
    private GtpClass gtpClass;
    private boolean inPlay;
    private boolean cashOutAvailable;
    private MarketStatus status;
    private PublishingStatus publishingStatus;
    private DisplayStatus displayStatus;
    private Double handicapValue;
    private HandicapResult handicapResult;
    private OutboundSelection[] selections;
    private Map<String, Object> metadata;

    public OutboundMarket() {

    }

    private OutboundMarket( //
                            String name, //
                            GtpClass gtpClass, //
                            boolean inplay, //
                            boolean cashOutAvailable, //
                            MarketStatus status, //
                            PublishingStatus publishingStatus, //
                            DisplayStatus displayStatus, //
                            Double handicapValue, //
                            HandicapResult handicapResult, //
                            OutboundSelection[] selections, //
                            Map<String, Object> metadata) {
        this.name = name;
        this.gtpClass = gtpClass;
        this.inPlay = inplay;
        this.cashOutAvailable = cashOutAvailable;
        this.status = status;
        this.publishingStatus = publishingStatus;
        this.displayStatus = displayStatus;
        this.handicapValue = handicapValue;
        this.handicapResult = handicapResult;
        this.selections = selections;
        this.metadata = Collections.unmodifiableMap(metadata);
    }

    public OutboundMarket copyWithNewNameAndUpdatedSelections(String newName, List<OutboundSelection> updatedSelections) {
        return new OutboundMarket( //
                newName, //
                this.gtpClass, //
                this.inPlay, //
                this.cashOutAvailable, //
                this.status, //
                this.publishingStatus, //
                this.displayStatus, //
                this.handicapValue, //
                this.handicapResult, //
                updatedSelections.toArray(new OutboundSelection[updatedSelections.size()]), //
                this.metadata //
        );
    }

    public String getName() {
        return this.name;
    }

    public GtpClass getGtpClass() {
        return this.gtpClass;
    }

    public boolean isInPlay() {
        return this.inPlay;
    }

    public boolean isCashOutAvailable() {
        return this.cashOutAvailable;
    }

    public MarketStatus getStatus() {
        return this.status;
    }

    public PublishingStatus getPublishingStatus() {
        return this.publishingStatus;
    }

    public DisplayStatus getDisplayStatus() {
        return this.displayStatus;
    }

    public Double getHandicapValue() {
        return this.handicapValue;
    }

    public HandicapResult getHandicapResult() {
        return this.handicapResult;
    }

    public OutboundSelection[] getSelections() {
        return this.selections;
    }

    public Map<String, Object> getMetadata() {
        return this.metadata;
    }
}
