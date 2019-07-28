package progress_circle;

import javafx.application.*;
public class ProgressThread extends Thread {
	private RingProgressIndicator rpi;
	private int progress;
	private double maxProg;

	public ProgressThread(RingProgressIndicator rpi, double maxProg) {
		this.rpi = rpi;
		progress = 0;
		this.maxProg = maxProg;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Platform.runLater(()->{
				rpi.setProgress(progress);
			});

			if (progress >= maxProg)
				break;

			progress++;
		}

	}
}
