package graphic;

import javax.swing.*;

public class homeFrame{
	private JFrame jf;
	private homePanel hp;

	public homeFrame() {

		jf = new JFrame("Splitter");
		hp = new homePanel();

		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(300,300);

		jf.add(hp);

		jf.pack();
		jf.setVisible(true);
	}

}
