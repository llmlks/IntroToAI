package parkRobot;

import java.util.TimerTask;

public class SpotCheckPollingTask extends TimerTask {

	@Override
	public void run() {
		if (Main.ultra.getDistance() < 40) {
			Main.isOkSpot = false;
		}
	}

}
