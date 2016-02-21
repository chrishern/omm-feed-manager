# omm-feed-manager

To run the application, use the Maven Spring Boot plugin:

	mvn spring-boot:run

By default, the application uses an in-memory ActiveMQ broker.  To connect to an external broker, pass in the spring.activemq.broker-url property when running the app, giving the URL of the external broker:

	mvn spring-boot:run -Dspring.activemq.broker-url=tcp://localhost:61616/