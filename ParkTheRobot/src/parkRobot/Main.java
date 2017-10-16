package parkRobot;

import java.util.Timer;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class Main {

	static DifferentialPilot pilot = new DifferentialPilot(5.6, 17.5f, Motor.A, Motor.B);
	static UltrasonicSensor ultra = new UltrasonicSensor(SensorPort.S2);
	static Timer timer = new Timer();
	static int distance;
	static boolean isOkSpot;

	public static void main(String[] args) {
		System.out.println("I'll be back!");
		Button.waitForAnyPress();
	}

	public static void parkTheRobot() {
		boolean spotFound = false;
		while (!spotFound) {
			findStartOfSpot();
			spotFound = isSpotBigEnough();
		}
	}

	public static void findStartOfSpot() {
		pilot.forward();
		timer.scheduleAtFixedRate(new PossibleSpotPollingTask(), 0l, 100l);
	}

	public static boolean isSpotBigEnough() {
		isOkSpot = true;
		pilot.travel(40);
		timer.scheduleAtFixedRate(new SpotCheckPollingTask(), 0l, 100l);
		return isOkSpot;
	}

	public static void parkRobotInSpot() {
		pilot.travel(-40);
		pilot.rotate(90.0);
		pilot.travel(40);
	}

	public static void setDistance() {
		distance = ultra.getDistance();
	}

	public static int getDistance() {
		return distance;
	}
}
