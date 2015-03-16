package dhbw.wwi12se.oip.dhbw.wwi12se.oip;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.opcfoundation.ua.builtintypes.DataValue;

import com.prosysopc.ua.client.MonitoredDataItem;

@XmlRootElement
public class OPC_Double extends OPC {

	@XmlElement
	private double _value;

	public OPC_Double() {
		super(null, null, null);
	}

	public OPC_Double(String sourcesystem, MonitoredDataItem arg0, DataValue arg) {
		super(sourcesystem, arg0, arg);
		this._value = arg.getValue().doubleValue();
	}

	public double get_value() {
		return _value;
	}
}
