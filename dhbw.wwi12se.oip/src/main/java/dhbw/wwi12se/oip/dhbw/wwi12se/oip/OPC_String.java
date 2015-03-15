package dhbw.wwi12se.oip.dhbw.wwi12se.oip;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.opcfoundation.ua.builtintypes.DataValue;

@XmlRootElement
public class OPC_String extends OPC {

	@XmlElement
	public String _value = null;

	public OPC_String() {
		super(null);
	}

	public OPC_String(DataValue arg) {
		super(arg);

		this._value = arg.getValue().toString();
	}

	public String get_value() {
		return _value;
	}
}
