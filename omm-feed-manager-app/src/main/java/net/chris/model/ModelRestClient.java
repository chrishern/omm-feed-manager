package net.chris.model;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.chris.api.model.input.ModelInput;
import net.chris.api.model.output.OutboundMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ModelRestClient implements ModelClient {

    private String modelPriceUrl;
    private RestTemplate restTemplate;

    public ModelRestClient(final String modelServerUrl, final RestTemplate restTemplate) {
        this.modelPriceUrl = modelServerUrl + "/price";
        this.restTemplate = restTemplate;
    }

    @Override
    public OutboundMessage sendIncidentUpdate(final ModelInput incidentUpdate) {

        // TODO - don't need this
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            System.out.println("CALLING MODEL WITH: " + objectMapper.writeValueAsString(incidentUpdate));

            final ResponseEntity<OutboundMessage> modelResponse = restTemplate.postForEntity(modelPriceUrl, incidentUpdate, OutboundMessage.class);

            final OutboundMessage modelResponseBody = modelResponse.getBody();

            System.out.println("RECEIVED RESPONSE FROM MODEL: " + objectMapper.writeValueAsString(modelResponseBody));

            return modelResponseBody;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
