package InputServer;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.core.ApplicationDescription;
import org.opcfoundation.ua.core.ApplicationType;
import org.opcfoundation.ua.core.Attributes;
import org.opcfoundation.ua.core.Identifiers;
import org.opcfoundation.ua.core.MonitoringMode;
import org.opcfoundation.ua.core.ReferenceDescription;
import org.opcfoundation.ua.transport.security.SecurityMode;

import Model.OPC;
import Model.OPC_Boolean;
import Model.OPC_Double;
import Model.OPC_Float;
import Model.OPC_Integer;
import Model.OPC_String;

import com.prosysopc.ua.ApplicationIdentity;
import com.prosysopc.ua.SecureIdentityException;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.MonitoredDataItem;
import com.prosysopc.ua.client.MonitoredDataItemListener;
import com.prosysopc.ua.client.Subscription;
import com.prosysopc.ua.client.UaClient;

public class Server {
	// public static String sourcesystem =
	// "opc.tcp://localhost:53530/OPCUA/SimulationServer";
	public static String sourcesystem = "opc.tcp://192.168.0.102:49320";

	public static void main(String[] args) throws Exception {

		// Create client object
		UaClient client = new UaClient(sourcesystem);
		client.setSecurityMode(SecurityMode.NONE);

		initialize(client);
		client.connect();
		DataValue value = client
				.readValue(Identifiers.Server_ServerStatus_State);

		client.getAddressSpace().setMaxReferencesPerNode(1000);
		NodeId nid = Identifiers.RootFolder;

		List<ReferenceDescription> references = client.getAddressSpace()
				.browse(nid);

		// Example of Namespace Browsing
		/*
		 * NodeId target; ReferenceDescription r = references.get(0);
		 * 
		 * target = client.getAddressSpace().getNamespaceTable()
		 * .toNodeId(r.getNodeId()); references =
		 * 
		 * client.getAddressSpace().browse(target); r = references.get(4);
		 * target = client.getAddressSpace().getNamespaceTable()
		 * .toNodeId(r.getNodeId());
		 */
		ArrayList<NodeId> targets = new ArrayList<NodeId>();
		for (Targets targets2 : Targets.values()) {
			targets.add(new NodeId(targets2.getNameSpace(), targets2
					.getSensorName()));
		}
		generateSubscriptions(client, targets);
	}

	private static void generateSubscriptions(UaClient client,
			ArrayList<NodeId> targets) throws ServiceException, StatusException {
		Subscription subscription = new Subscription();
		ArrayList<MonitoredDataItem> items = new ArrayList<MonitoredDataItem>();
		for (NodeId nodeId : targets) {
			MonitoredDataItem dataItem = new MonitoredDataItem(nodeId,
					Attributes.Value, MonitoringMode.Reporting);
			subscription.addItem(dataItem);
			items.add(dataItem);
		}
		client.addSubscription(subscription);
		setDataChangeListeners(items);
	}

	/**
	 * @param {@link MonitoredDataItem}
	 */
	private static void setDataChangeListeners(
			ArrayList<MonitoredDataItem> items) {
		for (MonitoredDataItem monitoredDataItem : items) {
			monitoredDataItem
					.setDataChangeListener(new MonitoredDataItemListener() {

						public void onDataChange(MonitoredDataItem arg0,
								DataValue arg1, DataValue arg2) {
							ObjectToXml.convert(createOPC(arg0, arg1, arg2));
						}
					});
		}
	}

	/**
	 * Ueberprüft {@link DataValue} auf den DatenTyp. Erzeugt auf Basis dessen
	 * das zugehörige {@link OPC}-Objekt.
	 * 
	 * @param monitoredDataItem
	 * @param dataValue
	 * @param dataValue2
	 * @return {@link OPC}
	 */
	private static OPC createOPC(MonitoredDataItem monitoredDataItem,
			DataValue dataValue, DataValue dataValue2) {
		try {
			Object dataValueObject = dataValue.getValue().getValue();
			if (dataValueObject instanceof Double) {
				return new OPC_Double(sourcesystem, monitoredDataItem,
						dataValue);
			} else if (dataValueObject instanceof Integer) {
				return new OPC_Integer(sourcesystem, monitoredDataItem,
						dataValue);
			} else if (dataValueObject instanceof Float) {
				return new OPC_Float(sourcesystem, monitoredDataItem, dataValue);
			} else if (dataValueObject instanceof String) {
				return new OPC_String(sourcesystem, monitoredDataItem,
						dataValue);
			} else if (dataValueObject instanceof Boolean) {
				return new OPC_Boolean(sourcesystem, monitoredDataItem,
						dataValue);
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
			return null;
		}
		return null;
	}

	/**
	 * Initialize the client
	 * 
	 * @param client
	 * @throws SecureIdentityException
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	protected static void initialize(UaClient client)
			throws SecureIdentityException, IOException, UnknownHostException {
		// *** Application Description is sent to the server
		ApplicationDescription appDescription = new ApplicationDescription();
		appDescription.setApplicationName(new LocalizedText("DHBW Client",
				Locale.GERMAN));

		// 'localhost' (all lower case) in the URI is converted to the actual
		// host name of the computer in which the application is run
		// appDescription.setApplicationUri("urn:localhost:UA:DHBWClient");
		// appDescription.setProductUri("urn:prosysopc.com:UA:DHBWClient");
		appDescription.setApplicationUri("urn:192.168.0.102");
		appDescription.setProductUri("urn:192.168.0.102");
		appDescription.setApplicationType(ApplicationType.Client);

		final ApplicationIdentity identity = new ApplicationIdentity();
		identity.setApplicationDescription(appDescription);
		client.setApplicationIdentity(identity);
	}

}
