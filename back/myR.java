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

		System.out.println("element.getPath: " + element.getPath());
		System.out.println("element.getNameFile: " + element.getNameFile());
		System.out.println("element.getMode: " + element.getMode());
		
		if(element.getMode().equals("Split")) {
			s = new Splitter(element.getPath(), true, element.getGrandezza());
		}

		else if(element.getMode().equals("Unsplit")) {
			s = new Splitter(element.getPath(), false, element.getGrandezza());
		}

		else if(element.getMode().equals("Zip")) {
			
		}

		else if(element.getMode().equals("Unzip")) {

		}

		else if(element.getMode().equals("Crypt")) {

		}
		
		else if(element.getMode().equals("Uncrypt")) {
			
		}
		
		else {
			//homePanel.print("Errore la modalità non è corretta");
			System.out.println("Errore la modalità non è corretta");
		}
	}
}