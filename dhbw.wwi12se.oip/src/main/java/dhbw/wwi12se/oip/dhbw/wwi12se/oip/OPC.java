package dhbw.wwi12se.oip.dhbw.wwi12se.oip;

import javax.xml.bind.annotation.XmlElement;

import org.opcfoundation.ua.builtintypes.DataValue;

public class OPC extends InputAdapter {

	// TODO: sensorName und sourceSystem --> komme nicht dran
	@XmlElement
	private String _sensorName;
	@XmlElement
	private String _sourceSystem;
	@XmlElement
	private Integer _dataQuality;
	@XmlElement
	private Long _sourceTimestamp;

	public OPC(DataValue arg) {
		super();

		// this._dataQuality = (Integer) arg.getStatusCode();
		this._sourceTimestamp = arg.getSourceTimestamp().getMilliSeconds();
	}

	public String get_sensorName() {
		return _sensorName;
	}

	public Integer get_dataQuality() {
		return _dataQuality;
	}

	public Long get_sourceTimestamp() {
		return _sourceTimestamp;
	}
}
