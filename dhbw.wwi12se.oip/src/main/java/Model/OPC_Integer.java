package Model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.opcfoundation.ua.builtintypes.DataValue;

import com.prosysopc.ua.client.MonitoredDataItem;

@XmlRootElement
public class OPC_Integer extends OPC {

	@XmlElement
	private int _value;

	public OPC_Integer() {
		super(null, null, null);
	}

	public OPC_Integer(String sourcesystem, MonitoredDataItem arg0,
			DataValue arg) {
		super(sourcesystem, arg0, arg);
		this._value = arg.getValue().intValue();
	}

	public int get_value() {
		return _value;
	}
}
