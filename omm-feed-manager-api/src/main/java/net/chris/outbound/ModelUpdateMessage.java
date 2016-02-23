package net.chris.outbound;

import com.google.common.base.MoreObjects;

public class ModelUpdateMessage implements OutboundMessage {

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("messageType", OutboundMessage.MODEL_UPDATE)
                .toString();
    }
}
