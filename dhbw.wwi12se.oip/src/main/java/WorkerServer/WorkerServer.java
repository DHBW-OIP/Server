package WorkerServer;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import Model.OPC_Boolean;
import Model.OPC_Integer;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class WorkerServer {

	private final static String QUEUE_NAME = "QUEUE2";

	public static void main(String[] argv) throws java.io.IOException,
			java.lang.InterruptedException {

		// Reg. EsperService
		NRTA.startNRTA();

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, true, consumer);
		try {
			while (true) {
				EPServiceProvider epService = EPServiceProviderManager
						.getDefaultProvider();

				QueueingConsumer.Delivery delivery = consumer.nextDelivery();
				String message = new String(delivery.getBody());
				// System.out.println(" [x] Received '" + message + "'");

				if (message.contains("opcBoolean")) {
					JAXBContext jaxbContext = JAXBContext
							.newInstance(OPC_Boolean.class);

					Unmarshaller jaxbUnmarshaller = jaxbContext
							.createUnmarshaller();
					OPC_Boolean opcdouble = (OPC_Boolean) jaxbUnmarshaller
							.unmarshal(new StringReader(message));
					System.out.println("OPC_Boolean erstellt");
					System.out.println(opcdouble.get_value());
					epService.getEPRuntime().sendEvent(opcdouble);
				} else if (message.contains("opcInteger")) {
					JAXBContext jaxbContext = JAXBContext
							.newInstance(OPC_Integer.class);

					Unmarshaller jaxbUnmarshaller = jaxbContext
							.createUnmarshaller();
					OPC_Integer opcint = (OPC_Integer) jaxbUnmarshaller
							.unmarshal(new StringReader(message));
					System.out.println("OPC_Integer erstellt");
					System.out.println(opcint.get_value());
					epService.getEPRuntime().sendEvent(opcint);
				} else {
					System.out.println("Type unknown");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
