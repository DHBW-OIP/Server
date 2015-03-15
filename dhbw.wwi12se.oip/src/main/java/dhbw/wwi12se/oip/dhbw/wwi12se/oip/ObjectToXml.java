package dhbw.wwi12se.oip.dhbw.wwi12se.oip;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class ObjectToXml {

	public static void main(String[] args) throws Exception {
		convert(null);
	}

	public static void convert(InputAdapter obj) {

		// OPC_String obj2 = new OPC_String((new DataValue()));
		// obj2._value = "Test";
		//
		// obj = obj2;

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// jaxbMarshaller.marshal(customer, file);
			jaxbMarshaller.marshal(obj, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
}
