package graphic;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
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
	private JPanel pannelloPrincipale, pannelloBottoni, pannelloCentrale, pannelloSuperiore;
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
			MyTableModel model = (MyTableModel)homePanel.myTable.getModel();

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
		this.setSize(600,200);

		pannelloPrincipale = new JPanel();
		pannelloPrincipale.setLayout(new BoxLayout(pannelloPrincipale, BoxLayout.Y_AXIS));
		this.add(pannelloPrincipale);

		pannelloBottoni = new JPanel();
		pannelloCentrale = new JPanel();
		pannelloSuperiore = new JPanel();

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
		textGrandezza = new JTextField(5);
		textGrandezza.setText("512");
		textGrandezza.setEditable(true);
		pannelloCentrale.add(textGrandezza);


		labelModalita = new JLabel("modalità");
		pannelloSuperiore.add(labelModalita);
		box = new JComboBox<String>();
		box.addItem("Split(Default)");
		box.addItem("Split(Parti)");
		box.addItem("Zip");
		box.addItem("Crypto");
		box.addItem("Unsplit");
		pannelloSuperiore.add(box);

		okButton = new JButton("conferma");
		okButton.addActionListener(this);
		pannelloBottoni.add(okButton);
		refuseButton = new JButton("annulla");
		refuseButton.addActionListener(this);
		pannelloBottoni.add(refuseButton);

		pannelloPrincipale.add(pannelloSuperiore, BorderLayout.PAGE_START);
		pannelloPrincipale.add(pannelloCentrale, BorderLayout.CENTER);
		pannelloPrincipale.add(pannelloBottoni,  BorderLayout.PAGE_END);
		this.setVisible(true);
	}
}
