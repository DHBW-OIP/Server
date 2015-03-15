package dhbw.wwi12se.oip.dhbw.wwi12se.oip;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.opcfoundation.ua.builtintypes.DataValue;

@XmlRootElement
public class OPC_Float extends OPC {

	@XmlElement
	private float _value;

	public OPC_Float() {
		super(null);
	}

	public OPC_Float(DataValue arg) {
		super(arg);
		this._value = arg.getValue().floatValue();
	}

	public float get_value() {
		return _value;
	}
}
