package dhbw.wwi12se.oip.dhbw.wwi12se.oip;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.opcfoundation.ua.builtintypes.DataValue;

@XmlRootElement
public class OPC_Double extends OPC {

	@XmlElement
	private double _value;

	public OPC_Double() {
		super(null);
	}

	public OPC_Double(DataValue arg) {
		super(arg);
		this._value = arg.getValue().doubleValue();
	}

	public double get_value() {
		return _value;
	}
}
