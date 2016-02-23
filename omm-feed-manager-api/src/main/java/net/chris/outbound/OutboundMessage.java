package net.chris.outbound;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "messageType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = EventCreatedMessage.class, name = OutboundMessage.EVENT_CREATED),
        @JsonSubTypes.Type(value = ModelUpdateMessage.class, name = OutboundMessage.MODEL_UPDATE)
})
public interface OutboundMessage extends Serializable {

    String EVENT_CREATED = "EVENT_CREATED";
    String MODEL_UPDATE = "MODEL_UPDATE";
}
