package dhbw.wwi12se.oip.dhbw.wwi12se.oip;

import java.sql.Timestamp;

public class InputAdapter {

	private Timestamp _timestamp;

	public InputAdapter() {
		java.util.Date date = new java.util.Date();
		_timestamp = new Timestamp(date.getTime());
	}

}
