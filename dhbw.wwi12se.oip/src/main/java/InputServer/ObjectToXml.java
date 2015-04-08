package InputServer;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import Stuff.AppSetting;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ObjectToXml
{

	public static void main(String[] args) throws Exception
	{
		convert(null);
	}

	public static void convert(InputAdapter obj)
	{

		try
		{

			JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// jaxbMarshaller.marshal(obj, System.out);
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(obj, sw);

			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(AppSetting.host);
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			// System.out.println("Create QUEUE");
			channel.queueDeclare(AppSetting.queueName, false, false, false, null);
			System.out.println("Send Data:");
			channel.basicPublish("", AppSetting.queueName, null, sw.toString().getBytes());
			System.out.println("Sent '" + sw.toString() + "'");

			sw.close();
			channel.close();
			connection.close();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}
