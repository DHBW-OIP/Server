package dhbw.wwi12se.oip.dhbw.wwi12se.oip;

import org.opcfoundation.ua.builtintypes.DataValue;

public class OPC_String extends OPC {

	public String _value;

	public OPC_String(DataValue arg) {
		super(arg);

		this._value = arg.getValue().toString();
	}

	public String get_value() {
		return _value;
	}
}
