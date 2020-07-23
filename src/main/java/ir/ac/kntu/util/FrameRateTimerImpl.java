package ir.ac.kntu.util;

public class FrameRateTimerImpl implements FrameRateTimer {

	private long time= 0;
	private final long	paintWindow;

	public FrameRateTimerImpl(int frames) {
		super();
		paintWindow = 1000 / frames;
	}

	public void start() {
		time = System.currentTimeMillis();

	}

	public long stop() {
		long tmp = System.currentTimeMillis() - time;
		tmp = paintWindow - tmp;
		return tmp > 0 ? tmp : 0;
	}

}
