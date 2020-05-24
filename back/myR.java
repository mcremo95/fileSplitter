package back;

public class myR implements Runnable{

	private Element element;
	private Splitter s;

	public myR(Element e) {
		this.element = e;
	}

	public void run() {
		s = new Splitter(element, element.getGrandezza());
	}
}