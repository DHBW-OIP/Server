package Model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.opcfoundation.ua.builtintypes.DataValue;

import com.prosysopc.ua.client.MonitoredDataItem;

@XmlRootElement
public class OPC_Boolean extends OPC {

	@XmlElement
	private boolean _value;

	public OPC_Boolean() {
	}

	public OPC_Boolean(String sourcesystem, MonitoredDataItem arg0,
			DataValue arg) {
		super(sourcesystem, arg0, arg);
		this._value = arg.getValue().booleanValue();
	}

	public boolean get_value() {
		return _value;
	}
}
