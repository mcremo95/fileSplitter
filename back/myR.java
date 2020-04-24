package back;

import graphic.homePanel;

public class myR implements Runnable{

	private int id;
	private homePanel lf;

	public myR(int i, homePanel mf) {
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