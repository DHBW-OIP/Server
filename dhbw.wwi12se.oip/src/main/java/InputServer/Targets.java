package InputServer;

public enum Targets {
	Random1(5, "Random1"), Counter1(5, "Counter1"), Expression1(5,
			"Expression1"), Square1(5, "Square1");

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
