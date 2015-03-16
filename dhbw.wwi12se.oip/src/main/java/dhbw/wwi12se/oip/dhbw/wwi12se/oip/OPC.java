package dhbw.wwi12se.oip.dhbw.wwi12se.oip;

import javax.xml.bind.annotation.XmlElement;

import org.opcfoundation.ua.builtintypes.DataValue;

import com.prosysopc.ua.client.MonitoredDataItem;

public class OPC extends InputAdapter {

	@XmlElement
	private String _sensorName;
	@XmlElement
	private String _sourceSystem;
	@XmlElement
	private String _dataQuality;
	@XmlElement
	private Long _sourceTimestamp;

	public OPC(String sourcesystem, MonitoredDataItem arg0, DataValue arg) {
		super();
		this._sensorName = arg0.getNodeId().getValue().toString();
		this._sourceSystem = sourcesystem;
		this._dataQuality = arg.getStatusCode().toString();
		this._sourceTimestamp = arg.getSourceTimestamp().getMilliSeconds();
	}

	public String get_sensorName() {
		return _sensorName;
	}

	public String get_dataQuality() {
		return _dataQuality;
	}

	public Long get_sourceTimestamp() {
		return _sourceTimestamp;
	}
}
