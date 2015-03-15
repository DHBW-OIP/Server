package dhbw.wwi12se.oip.dhbw.wwi12se.oip;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.opcfoundation.ua.builtintypes.DataValue;

@XmlRootElement
public class OPC_Integer extends OPC {

	@XmlElement
	private int _value;

	public OPC_Integer() {
		super(null);
	}

	public OPC_Integer(DataValue arg) {
		super(arg);
		this._value = arg.getValue().intValue();
	}

	public int get_value() {
		return _value;
	}
}
