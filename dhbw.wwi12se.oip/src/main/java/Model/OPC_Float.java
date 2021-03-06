package Model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.opcfoundation.ua.builtintypes.DataValue;

import com.prosysopc.ua.client.MonitoredDataItem;

@XmlRootElement
public class OPC_Float extends OPC {

	@XmlElement
	private float _value;

	public OPC_Float() {
	}

	public OPC_Float(String sourcesystem, MonitoredDataItem arg0, DataValue arg) {
		super(sourcesystem, arg0, arg);
		this._value = arg.getValue().floatValue();
	}

	public float get_value() {
		return _value;
	}
}
