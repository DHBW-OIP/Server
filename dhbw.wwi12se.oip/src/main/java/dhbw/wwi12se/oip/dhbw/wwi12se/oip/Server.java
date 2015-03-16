package dhbw.wwi12se.oip.dhbw.wwi12se.oip;

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

import com.prosysopc.ua.ApplicationIdentity;
import com.prosysopc.ua.SecureIdentityException;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.MonitoredDataItem;
import com.prosysopc.ua.client.MonitoredDataItemListener;
import com.prosysopc.ua.client.Subscription;
import com.prosysopc.ua.client.UaClient;

public class Server {

	public static void main(String[] args) throws Exception {

		// Create client object
		UaClient client = new UaClient(
				"opc.tcp://localhost:53530/OPCUA/SimulationServer");
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
		NodeId target;
		ReferenceDescription r = references.get(0);

		target = client.getAddressSpace().getNamespaceTable()
				.toNodeId(r.getNodeId());
		references = client.getAddressSpace().browse(target);
		r = references.get(4);
		target = client.getAddressSpace().getNamespaceTable()
				.toNodeId(r.getNodeId());

		ArrayList<NodeId> targets = new ArrayList<NodeId>();
		targets.add(new NodeId(5, "Random1"));
		targets.add(new NodeId(5, "Counter1"));
		targets.add(new NodeId(5, "Expression1"));
		targets.add(new NodeId(5, "Square1"));

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

		items.get(0).setDataChangeListener(new MonitoredDataItemListener() {

			public void onDataChange(MonitoredDataItem arg0, DataValue arg1,
					DataValue arg2) {
				OPC_Double newdata = new OPC_Double(arg1);

				System.out.println(newdata.get_value());
				ObjectToXml.convert(newdata);
			}
		});
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
		appDescription.setApplicationUri("urn:localhost:UA:DHBWClient");
		appDescription.setProductUri("urn:prosysopc.com:UA:DHBWClient");
		appDescription.setApplicationType(ApplicationType.Client);

		final ApplicationIdentity identity = new ApplicationIdentity();
		identity.setApplicationDescription(appDescription);
		client.setApplicationIdentity(identity);
	}

}
