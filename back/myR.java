package back;

public class myR implements Runnable{

	private Element element;
	private Splitter s;
	private String extension;

	public myR(Element e) {
		this.element = e;
		this.extension = e.getNameFile().substring(e.getNameFile().lastIndexOf("."));
		System.out.println("extension: " + extension);
	}

	public void run() {

		if(element.getMode().contentEquals("Split(Default)")
				|| element.getMode().contentEquals("Split(Parti)")) {
			s = new Splitter(element, element.getGrandezza());
			s.split();
		}

		else if(element.getMode().contentEquals("Unsplit")){
			
			if(extension.contentEquals(".par1")) {
				s = new Splitter(element, element.getGrandezza());
				s.unsplit();
			}
			
			else if(extension.contentEquals(".zip1")) {
				s = new Zipper(element, element.getGrandezza());
				s.unsplit();
			}
			
			else {
				System.out.println("Male");
			}
		}

		else if(element.getMode().contentEquals("Zip")){
			s = new Zipper(element, element.getGrandezza());
			s.split();
		}
	}
}