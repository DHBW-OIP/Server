package WorkerServer;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class NRTA {

	public static void startNRTA() {
		EPServiceProvider epService = EPServiceProviderManager
				.getDefaultProvider();
		MyListener listener = new MyListener();

		// Avg. Double
		String expression = "select avg(_value) from Model.OPC_Double.win:time(30 sec)";
		EPStatement statement = epService.getEPAdministrator().createEPL(
				expression);
		statement.addListener(listener);

		// Avg. Integer
		expression = "select avg(_value) from Model.OPC_Integer.win:time(30 sec)";
		statement = epService.getEPAdministrator().createEPL(expression);
		statement.addListener(listener);
	}

	public static class MyListener implements UpdateListener {
		public void update(EventBean[] newEvents, EventBean[] oldEvents) {
			EventBean event = newEvents[0];
			System.out.println("avg=" + event.get("avg(_value)"));
		}
	}

}
