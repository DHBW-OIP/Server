package InputServer;

public enum Targets {
	No1(2, "SPSData.S7-1200.Inputs.Phototransistor conveyer belt swap"), No2(2,
			"SPSData.S7-1200.Inputs.Phototransistor drilling machine"), No3(2,
			"SPSData.S7-1200.Inputs.Phototransistor loading station"), No4(2,
			"SPSData.S7-1200.Inputs.Phototransistor milling machine"), No5(2,
			"SPSData.S7-1200.Inputs.Phototransistor slider 1"), No6(2,
			"SPSData.S7-1200.Inputs.Push-button slider 1 front"), No7(2,
			"SPSData.S7-1200.Inputs.Push-button slider 1 rear"), No8(2,
			"SPSData.S7-1200.Inputs.Push-button slider 2 front"), No9(2,
			"SPSData.S7-1200.Inputs.Push-button slider 2 rear"), No10(2,
			"SPSData.S7-1200.Outputs.motor conveyor belt drilling machine"), No11(
			2, "SPSData.S7-1200.Outputs.motor conveyor belt feed"), No12(2,
			"SPSData.S7-1200.Outputs.motor conveyor belt swap"), No13(2,
			"SPSData.S7-1200.Outputs.motor drilling machine"), No14(2,
			"SPSData.S7-1200.Outputs.motor milling machine"), No15(2,
			"SPSData.S7-1200.Outputs.motor slider 1 backward"), No16(2,
			"SPSData.S7-1200.Outputs.motor slider 1 forward"), No17(2,
			"SPSData.S7-1200.Outputs.motor slider 2 backward"), No18(2,
			"SPSData.S7-1200.Outputs.motor slider 2 forward"), No19(2,
			"SPSData.S7-1200.Timer.Timer Fraesen");

	private int nameSpace;
	private String sensorName;

	Targets(int nameSpace, String sensorName) {
		this.nameSpace = nameSpace;
		this.sensorName = sensorName;
	}

	public int getNameSpace() {
		return nameSpace;
	}

	public String getSensorName() {
		return sensorName;
	}
}
