package followPath;

import java.util.Timer;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;

public class Main {
	static final int MIN_LIGHT_VALUE = 45;
	static final int MAX_LIGHT_VALUE = 55;
	static final double DEF_ROTATING_ANGLE = 5;
	static final double MAX_ROTATING_ANGLE = 180;

	static DifferentialPilot pilot = new DifferentialPilot(5.6, 17.5f, Motor.A, Motor.B);
	static LightSensor light = new LightSensor(SensorPort.S1);
	static int lightValue;
	static Timer timer = new Timer();

	public static void main(String[] args) {
		System.out.println(light.readValue());
		Button.waitForAnyPress();
		followThePath();
	}

	public static void followThePath() {
		setLightValue();
		pilot.forward();
		timer.scheduleAtFixedRate(new LightValuePollingTask(), 0l, 100l);
		pilot.stop();
	}

	public static boolean isOnTape() {
		return lightValue > MAX_LIGHT_VALUE;
	}

	public static boolean isOffTape() {
		return lightValue < MIN_LIGHT_VALUE;
	}

	public static void setLightValue() {
		lightValue = light.readValue();
	}

	public static int getLightValue() {
		return lightValue;
	}
}
