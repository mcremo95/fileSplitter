package back;

import graphic.homePanel;

public class myR implements Runnable{

	private int id;
	private Element element;
	private Splitter s;

	public myR(int i, Element e) {
		this.id = i;
		this.element = e;
	}

	public void run() {
		String str = "-Thread n:" + this.id;
		homePanel.print(str);
		s = new Splitter();

		if(element.getMode().equals(".par1")) {

		}

		else if(element.getMode().equals(".zip")) {

		}

		else if(element.getMode().equals(".cry")) {

		}

		else if(element.getMode().equals(".par1.zip")){

		}

		else if(element.getMode().equals(".par1.cry")){

		}
		
		else {
			
		}
	}
}