package InputServer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InputAdapter {

	@XmlElement
	private Long _timestamp;

	public InputAdapter() {
		java.util.Date date = new java.util.Date();
		_timestamp = (Long) date.getTime();
	}

}
