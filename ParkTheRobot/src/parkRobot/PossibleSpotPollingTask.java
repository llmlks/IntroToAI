package parkRobot;

import java.util.TimerTask;

public class PossibleSpotPollingTask extends TimerTask {

	@Override
	public void run() {
		Main.setDistance();

		int distance = Main.getDistance();

		if (distance >= 40) {
			Main.pilot.stop();
			this.cancel();
		}
	}
}