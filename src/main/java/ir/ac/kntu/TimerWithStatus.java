package ir.ac.kntu;

import javafx.animation.AnimationTimer;

public abstract class TimerWithStatus extends AnimationTimer {

	public enum Status {
		RUNNING, STOPPED,
	}

	private Status	status	= Status.STOPPED;

	@Override
	public void start() {
		super.start();
		status = Status.RUNNING;
	}

	@Override
	public void stop() {
		super.stop();
		status = Status.STOPPED;
	}

	public Status getStatus() {
		return status;
	}

}
