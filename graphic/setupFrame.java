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
	private JFileChooser fileChooser;
	private JButton okButton, refuseButton, addButton;
	private JComboBox<String> box;
	private JPanel panelPrincipale, pannelloLog, pannelloCentrale, panelloSuperiore;
	private JLabel labelModalita, labelFile, labelGrandezza;
	private JTextField textPath, textGrandezza;
	private File f;
	private int returnValue;

	public setupFrame() {
		returnValue = 0;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addButton) {

			returnValue = fileChooser.showDialog(this, "Aggiungi");

			if(returnValue == JFileChooser.APPROVE_OPTION) {
				f = fileChooser.getSelectedFile();
				textPath.setText(f.getPath());
			}
		}

		if(e.getSource() == okButton) {
			MyTableModel model = (MyTableModel)homePanel.jt.getModel();

			if(returnValue == JFileChooser.APPROVE_OPTION) {
				int temp = Integer.parseInt(textGrandezza.getText());
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

		panelPrincipale = new JPanel(new BorderLayout());
		this.add(panelPrincipale);

		pannelloLog = new JPanel();
		pannelloCentrale = new JPanel();
		panelloSuperiore = new JPanel();

		fileChooser = new JFileChooser();

		labelFile = new JLabel("Path del file");
		pannelloCentrale.add(labelFile);
		textPath = new JTextField(25);
		textPath.setEditable(false);
		textPath.setText("Clicca su aggiungi per scegliere il file");
		pannelloCentrale.add(textPath);

		addButton = new JButton("aggiungi");
		addButton.addActionListener(this);
		pannelloCentrale.add(addButton);

		pannelloCentrale.add(new JLabel("                "));

		labelGrandezza = new JLabel("Grandezza (KB)");
		pannelloCentrale.add(labelGrandezza);
		textGrandezza = new JTextField("500");
		textPath.setEditable(true);
		pannelloCentrale.add(textGrandezza);


		labelModalita = new JLabel("modalità");
		panelloSuperiore.add(labelModalita);
		box = new JComboBox<String>();
		box.addItem("Split(Default)");
		box.addItem("Split(Parti)");
		box.addItem("Zip");
		box.addItem("Crypto");
		box.addItem("Unsplit");
		box.addItem("Unzip");
		box.addItem("Uncrypto");
		panelloSuperiore.add(box);

		okButton = new JButton("conferma");
		okButton.addActionListener(this);
		pannelloLog.add(okButton);
		refuseButton = new JButton("annulla");
		refuseButton.addActionListener(this);
		pannelloLog.add(refuseButton);

		panelPrincipale.add(panelloSuperiore, BorderLayout.PAGE_START);
		panelPrincipale.add(pannelloCentrale, BorderLayout.CENTER);
		panelPrincipale.add(pannelloLog,  BorderLayout.PAGE_END);
		this.setVisible(true);
	}
}
