package graphic;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import back.Element;
import java.io.File;

public class setupFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JFileChooser fc;
	private JButton okButton, refuseButton, addButton;
	private JComboBox<String> box;
	private JPanel jp, lp, cp, up;
	private JLabel lm, lf, lg;
	private JTextField tf, tg;
	private File f;
	private int rv;

	public setupFrame() {
		rv = 0;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addButton) {

			rv = fc.showDialog(this, "Aggiungi");

			if(rv == JFileChooser.APPROVE_OPTION) {
				f = fc.getSelectedFile();
				tf.setText(f.getPath());
			}
		}

		if(e.getSource() == okButton) {
			MyTableModel model = (MyTableModel)homePanel.jt.getModel();

			if(rv == JFileChooser.APPROVE_OPTION) {
				int temp = Integer.parseInt(tg.getText());
				model.add(new Element(f.getAbsolutePath(), f.getName(),
						box.getSelectedItem().toString(), temp));
				this.dispose();
			}
		}
		
		if(e.getSource() == refuseButton) {
			this.dispose();
		}
	}

	public void showGUI() {
		this.setSize(600,150);

		jp = new JPanel(new BorderLayout());
		this.add(jp);

		lp = new JPanel();
		cp = new JPanel();
		up = new JPanel();

		fc = new JFileChooser();

		lf = new JLabel("Path del file");
		cp.add(lf);
		tf = new JTextField(25);
		tf.setEditable(false);
		tf.setText("Clicca su aggiungi per scegliere il file");
		cp.add(tf);

		addButton = new JButton("aggiungi");
		addButton.addActionListener(this);
		cp.add(addButton);

		cp.add(new JLabel("                "));

		lg = new JLabel("Grandezza (KB)");
		cp.add(lg);
		tg = new JTextField("500");
		tf.setEditable(true);
		cp.add(tg);


		lm = new JLabel("modalità");
		up.add(lm);
		box = new JComboBox<String>();
		box.addItem("Split");
		box.addItem("Zip");
		box.addItem("Crypto");
		box.addItem("Unsplit");
		box.addItem("Unzip");
		box.addItem("Uncrypto");
		up.add(box);

		okButton = new JButton("conferma");
		okButton.addActionListener(this);
		lp.add(okButton);
		refuseButton = new JButton("annulla");
		refuseButton.addActionListener(this);
		lp.add(refuseButton);

		jp.add(up, BorderLayout.PAGE_START);
		jp.add(cp, BorderLayout.CENTER);
		jp.add(lp,  BorderLayout.PAGE_END);
		this.setVisible(true);
	}
}
