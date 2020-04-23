package back;

import java.util.Vector;

public class Queue {

	private Vector<Element> elements;

	/**
	 * Costruttore
	 */
	public Queue(){
		elements = new Vector<Element>();
	}

	/**
	 * Inserisce un elemento in coda
	 * @param e un ElementFile inserito in coda
	 */
	public void Enqueue(Element e) {
		elements.add(e);
	}

	/**
	 * Rimuove un elemento dalla coda passando un ElementFile
	 * @param e ElementFile da rimuovere
	 */
	public void Dequeue(Element e) {
		System.out.println("Denque avviata");
		for(int i = 0; i < elements.size(); i++) {
			if(e.getPath().equals(elements.get(i).getPath())){
				elements.remove(i);
				System.out.println("Rimuovo " + e.toString());
			}
		}
		System.out.println("Denque finita");
	}

	/**
	 * Rimuove un elemento dalla coda passando l'indice
	 * @param index l'indice del file da rimuovere
	 */
	public void Dequeue(int index) {
		elements.remove(index);
	}

	/**
	 * Stampa gli elementi della coda
	 */
	public void print() {
		for(int i = 0; i < elements.size(); i++) {
			System.out.println("l'elemento " + i + "-esimo è = " + elements.get(i).toString());
		}
	}

	/**
	 * @return the elements
	 */
	public Vector<Element> getElements() {
		return elements;
	}

	/**
	 * @param elements the elements to set
	 */
	public void setElements(Vector<Element> elements) {
		this.elements = elements;
	}
}	
