package dhbw.wwi12se.oip.dhbw.wwi12se.oip;

import org.opcfoundation.ua.builtintypes.DataValue;

public class OPC_Float extends OPC {

	private float _value;

	public OPC_Float(DataValue arg) {
		super(arg);
		this._value = arg.getValue().floatValue();
	}

	public float get_value() {
		return _value;
	}
}
