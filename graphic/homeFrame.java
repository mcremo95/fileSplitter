package graphic;

import javax.swing.*;

public class homeFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private homePanel hp;

	public homeFrame() {
		super("Splitter");
		hp = new homePanel();
		hp.showPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void showGUI() {
		this.setSize(600,600);
		this.add(hp);
		this.pack();
		this.setVisible(true);
	}
}
