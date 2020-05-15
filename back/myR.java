package back;

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
		
		//if o switch per tutte le modalità interessate dal programma
		if(element.getMode() == "") {
			
		}
		
	}
}