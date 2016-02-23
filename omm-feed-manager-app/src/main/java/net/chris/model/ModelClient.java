package net.chris.model;


import com.williamhill.rnd.football.minutemarkets.villager.dto.MinuteMarketsFootballEventDto;
import net.chris.model.output.ModelOutboundMessage;

public interface ModelClient {

    // TODO - this ideally will be the object from the model as opposed to our copy - the model object needs a default constructor
    ModelOutboundMessage sendIncidentUpdate(MinuteMarketsFootballEventDto incidentUpdate);
}
