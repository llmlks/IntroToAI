package followPath;

import java.util.TimerTask;

public class LightValuePollingTask extends TimerTask {

	@Override
	public void run() {
		Main.setLightValue();
		if (Main.isOnTape()) {
			Main.pilot.rotateLeft();
		} else if (Main.isOffTape()) {
			Main.pilot.rotateRight();
		} else {
			Main.pilot.steer(0);
		}
	}

}
