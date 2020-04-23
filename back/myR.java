package back;

import graphic.logFrame;

public class myR implements Runnable{

	private int id;
	private logFrame lf;

	public myR(int i, logFrame mf) {
		this.id = i;
		this.lf = mf;
	}

	public void run() {
		String str = "-Thread n:" + this.id;

		synchronized(lf) {
			lf.print(str);
		}
	}
}