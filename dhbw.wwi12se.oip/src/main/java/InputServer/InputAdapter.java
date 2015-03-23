package InputServer;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InputAdapter {

	@XmlElement
	private Timestamp _timestamp;

	public InputAdapter() {
		java.util.Date date = new java.util.Date();
		_timestamp = new Timestamp(date.getTime());
	}

}
