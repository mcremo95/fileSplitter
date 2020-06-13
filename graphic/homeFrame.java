package graphic;

import javax.swing.*;

public class homeFrame extends JFrame implements Window{

	private static final long serialVersionUID = 1L;
	private homePanel hp;

	/**
	 * Costruttore
	 */
	public homeFrame() {
		super("Splitter");
		hp = new homePanel();
		hp.showGUI();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Mostra l'interfaccia
	 */
	public void showGUI() {
		this.setSize(600,600);
		this.add(hp);
		this.pack();
		this.setVisible(true);
	}
}
