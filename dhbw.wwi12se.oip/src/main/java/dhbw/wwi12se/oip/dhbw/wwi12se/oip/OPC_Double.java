package dhbw.wwi12se.oip.dhbw.wwi12se.oip;

import org.opcfoundation.ua.builtintypes.DataValue;

public class OPC_Double extends OPC {

	private double _value;

	public OPC_Double(DataValue arg) {
		super(arg);
		this._value = arg.getValue().doubleValue();
	}

	public double get_value() {
		return _value;
	}
}
