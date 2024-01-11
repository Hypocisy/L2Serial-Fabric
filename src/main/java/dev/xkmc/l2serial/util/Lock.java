package dev.xkmc.l2serial.util;

public class Lock {

	private boolean recursive = false;

	public void execute(Runnable runnable) {
		if (recursive) return;
		recursive = true;
		runnable.run();
		recursive = false;
	}

}
