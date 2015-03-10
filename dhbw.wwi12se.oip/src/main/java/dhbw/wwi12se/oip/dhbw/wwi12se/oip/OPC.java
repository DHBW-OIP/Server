package dhbw.wwi12se.oip.dhbw.wwi12se.oip;

import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.DateTime;
import org.opcfoundation.ua.builtintypes.StatusCode;

public class OPC extends InputAdapter {

	// TODO: sensorName und sourceSystem --> komme nicht dran
	private String _sensorName;
	private String _sourceSystem;
	private StatusCode _dataQuality;
	private DateTime _sourceTimestamp;

	public OPC(DataValue arg) {
		super();

		this._dataQuality = arg.getStatusCode();
		this._sourceTimestamp = arg.getSourceTimestamp();
	}

	public String get_sensorName() {
		return _sensorName;
	}

	public StatusCode get_dataQuality() {
		return _dataQuality;
	}

	public DateTime get_sourceTimestamp() {
		return _sourceTimestamp;
	}
}
