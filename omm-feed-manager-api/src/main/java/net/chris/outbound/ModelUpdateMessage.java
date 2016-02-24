package net.chris.outbound;

import com.google.common.base.MoreObjects;
import net.chris.model.output.ModelOutboundMessage;

public class ModelUpdateMessage implements OutboundMessage {

    private final String caerusId;
    private final String openBetId;
    private final ModelOutboundMessage modelData;

    public ModelUpdateMessage(final Builder builder) {
        this.modelData = builder.modelData;
        this.caerusId = builder.caerusId;
        this.openBetId = builder.openBetId;
    }

    public String getCaerusId() {
        return caerusId;
    }

    public String getOpenBetId() {
        return openBetId;
    }

    public ModelOutboundMessage getModelData() {
        return modelData;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("messageType", OutboundMessage.MODEL_UPDATE)
                .add("caerusId", caerusId)
                .add("openBetId", openBetId)
                .add("modelData", modelData)
                .toString();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {

        private String caerusId;
        private String openBetId;
        private ModelOutboundMessage modelData;

        public Builder withCaerusId(String caerusId) {
            this.caerusId = caerusId;
            return this;
        }

        public Builder withOpenBetId(String openBetId) {
            this.openBetId = openBetId;
            return this;
        }

        public Builder withModelData(ModelOutboundMessage modelData) {
            this.modelData = modelData;
            return this;
        }

        public ModelUpdateMessage build() {
            return new ModelUpdateMessage(this);
        }
    }
}
