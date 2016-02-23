package net.chris.integrationtest;

import javax.jms.TextMessage;

import net.chris.configuration.Application;
import net.chris.controller.EventController;
import net.chris.domain.EventDetails;
import net.chris.test.common.ActiveMqPublishSubscribeTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class EndToEndIT extends ActiveMqPublishSubscribeTest {

	@Autowired
    private EventController eventController;

	@Test
	public void test() throws Exception {
        // arrange
        eventController.createEvent(createEventDetails());

		final TextMessage message = session.createTextMessage();
		message.setText(SAMPLE_MESSAGE);

		// act
		producer.send(message);
		
		// assert
	}

    private EventDetails createEventDetails() {

        return EventDetails.newBuilder()
                .withCaerusId("259407")
                .withOpenBetId("12121")
                .withAwayTeamName("Away Team")
                .withHomeTeamName("Home Team")
                .withInPlayGoalsExpectedRemaining(19.0)
                .withPreMatchAwayOverRoundedPrice(3.3)
                .withPreMatchBookingsPointsExpectancy(40.0)
                .withPreMatchCornersExpectancy(11.0)
                .withPreMatchDrawOverRoundedPrice(3.1)
                .withPreMatchFreeKickExpectancy(40.0)
                .withPreMatchGoalKickExpectancy(19.0)
                .withPreMatchGoalsExpectancy(2.0)
                .withPreMatchHomeOverRoundedPrice(2.15)
                .withPreMatchShotOnTargetExpectancy(8.0)
                .withPreMatchThrowInExpectancy(40.0)
                .withPreMatchWoodworkExpectancy(1.0)
                .build();
    }
}
