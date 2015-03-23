package Model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.opcfoundation.ua.builtintypes.DataValue;

import com.prosysopc.ua.client.MonitoredDataItem;

@XmlRootElement
public class OPC_String extends OPC {

	@XmlElement
	public String _value = null;

	public OPC_String() {
		super(null, null, null);
	}

	public OPC_String(String sourcesystem, MonitoredDataItem arg0, DataValue arg) {
		super(sourcesystem, arg0, arg);

		this._value = arg.getValue().toString();
	}

	public String get_value() {
		return _value;
	}
}
