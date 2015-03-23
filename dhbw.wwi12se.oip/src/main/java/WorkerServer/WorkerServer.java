package WorkerServer;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import Model.OPC_Double;
import Model.OPC_Integer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class WorkerServer {

	private final static String QUEUE_NAME = "QUEUE2";

	public static void main(String[] argv) throws java.io.IOException,
			java.lang.InterruptedException {

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
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();
				String message = new String(delivery.getBody());
				// System.out.println(" [x] Received '" + message + "'");

				if (message.contains("opcDouble")) {
					JAXBContext jaxbContext = JAXBContext
							.newInstance(OPC_Double.class);

					Unmarshaller jaxbUnmarshaller = jaxbContext
							.createUnmarshaller();
					OPC_Double opcdouble = (OPC_Double) jaxbUnmarshaller
							.unmarshal(new StringReader(message));
					System.out.println("OPC_Double erstellt");
					System.out.println(opcdouble.get_value());
				} else if (message.contains("opcInteger")) {
					JAXBContext jaxbContext = JAXBContext
							.newInstance(OPC_Integer.class);

					Unmarshaller jaxbUnmarshaller = jaxbContext
							.createUnmarshaller();
					OPC_Integer opcint = (OPC_Integer) jaxbUnmarshaller
							.unmarshal(new StringReader(message));
					System.out.println("OPC_Integer erstellt");
					System.out.println(opcint.get_value());
				} else {
					System.out.println("Type unknown");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
