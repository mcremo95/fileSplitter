package graphic;

import javax.swing.*;

public class homeFrame extends JFrame{
	private homePanel hp;

	public homeFrame() {

		super("Splitter");
		hp = new homePanel();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,600);

		this.add(hp);
		this.pack();
		this.setVisible(true);
	}

	public homePanel getHp() {
		return hp;
	}
}
