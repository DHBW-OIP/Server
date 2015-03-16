package dhbw.wwi12se.oip.dhbw.wwi12se.oip;

import javax.xml.bind.annotation.XmlElement;

import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.DateTime;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;

public class OPC extends InputAdapter {

	// TODO: sensorName und sourceSystem --> komme nicht dran
	@XmlElement
	private String _sensorName;
	@XmlElement
	private String _sourceSystem;
	@XmlElement
	private org.opcfoundation.ua.builtintypes.UnsignedInteger _dataQuality;
	@XmlElement
	private DateTime _sourceTimestamp;

	public OPC(DataValue arg) {
		super();

		this._dataQuality = arg.getStatusCode().getValue();
		this._sourceTimestamp = arg.getSourceTimestamp();
	}

	public String get_sensorName() {
		return _sensorName;
	}

	public UnsignedInteger get_dataQuality() {
		return _dataQuality;
	}

	public DateTime get_sourceTimestamp() {
		return _sourceTimestamp;
	}
}
