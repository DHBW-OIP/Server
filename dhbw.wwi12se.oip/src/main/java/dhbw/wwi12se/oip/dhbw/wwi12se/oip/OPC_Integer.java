package dhbw.wwi12se.oip.dhbw.wwi12se.oip;

import org.opcfoundation.ua.builtintypes.DataValue;

public class OPC_Integer extends OPC {

	private int _value;

	public OPC_Integer(DataValue arg) {
		super(arg);
		this._value = arg.getValue().intValue();
	}

	public int get_value() {
		return _value;
	}
}
