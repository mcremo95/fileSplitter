package back;

public class myR implements Runnable{

	private Element element;
	private Splitter s;
	private String extension;

	/**
	 * Costruttore che inizializza un oggetto myR
	 * @param e Un elemento e che contiene le informazione sul file
	 */
	public myR(Element e) {
		this.element = e;
		this.extension = e.getNameFile().substring(e.getNameFile().lastIndexOf("."));
	}

	public void run() {

		if(element.getMode().contentEquals("Split(Default)")
				|| element.getMode().contentEquals("Split(Parti)")) {
			s = new Splitter(element, element.getGrandezza());
			s.Split();
		}

		else if(element.getMode().contentEquals("Unsplit")){
			
			if(extension.contentEquals(".par1")) {
				s = new Splitter(element, element.getGrandezza());
				s.Unsplit();
			}
			
			else if(extension.contentEquals(".zip")) {
				s = new Zipper(element, element.getGrandezza());
				s.Unsplit();
			}
			
			else {
				System.out.println("Impossibile leggere il file");
			}
		}

		else if(element.getMode().contentEquals("Zip")){
			s = new Zipper(element, element.getGrandezza());
			s.Split();
		}
	}
}