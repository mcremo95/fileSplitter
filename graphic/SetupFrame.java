package graphic;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

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

public class SetupFrame extends JFrame implements ActionListener, Window{

	private static final long serialVersionUID = 1L;
	private JFileChooser fileChooser;
	private JButton okButton, refuseButton, addButton;
	private JComboBox<String> box;
	private JPanel pannelloPrincipale, pannelloBottoni, pannelloCentrale, pannelloSuperiore;
	private JLabel labelModalita, labelFile, labelGrandezza;
	private JTextField textPath, textGrandezza;
	private File f;
	private String path, modalita; 
	private int returnValue, gradenzza, row;
	private boolean modify;

	public SetupFrame() {
		returnValue = -1;
		modify = false;
	}

	public SetupFrame(String path, String mode, int grandezza, int row) {
		returnValue = -1;
		this.path = path;
		this.modalita = mode;
		this.gradenzza = grandezza;
		this.row = row;
		modify = true;
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
			int temp = Integer.parseInt(textGrandezza.getText());
			
			if(returnValue == JFileChooser.APPROVE_OPTION) {
				if(modify) {
					homePanel.getQ().Dequeue(row);
					model.add(new Element(f.getAbsolutePath(), f.getName(),
							box.getSelectedItem().toString(), temp));
				}
				else {
					model.add(new Element(f.getAbsolutePath(), f.getName(),
							box.getSelectedItem().toString(), temp));
				}
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			}

			else if(modify && returnValue == -1) {
				f = new File(path);
				homePanel.getQ().Dequeue(row);
				model.add(new Element(f.getAbsolutePath(), f.getName(),
						box.getSelectedItem().toString(), temp));
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			}
		}

		if(e.getSource() == refuseButton) {
			this.dispose();
		}
	}

	/**
	 * Mostra l'interfaccia
	 */
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

		if(modify) {
			textPath.setText(path);
		}

		else {
			textPath.setText("Clicca su aggiungi per scegliere il file");
		}
		pannelloCentrale.add(textPath);

		addButton = new JButton("aggiungi");
		addButton.addActionListener(this);
		pannelloCentrale.add(addButton);

		pannelloCentrale.add(new JLabel("                "));

		labelGrandezza = new JLabel("Parti/Grandezza (KB)");
		pannelloCentrale.add(labelGrandezza);
		textGrandezza = new JTextField(5);
		if(modify) {
			textGrandezza.setText(Integer.toString(gradenzza));
		}
		else {
			textGrandezza.setText("512");
		}
		textGrandezza.setEditable(true);
		pannelloCentrale.add(textGrandezza);


		labelModalita = new JLabel("modalità");
		pannelloSuperiore.add(labelModalita);
		box = new JComboBox<String>();
		box.addItem("Split(Default)");
		box.addItem("Split(Parti)");
		box.addItem("Zip");
		//box.addItem("Crypto");
		box.addItem("Unsplit");
		if(modify) {
			switch(modalita) {
			case "Split(Default)":
				box.setSelectedIndex(0);
			case "Split(Parti)":
				box.setSelectedIndex(1);
			case "Zip":
				box.setSelectedIndex(2);
			case "Unsplit":
				box.setSelectedIndex(3);
			}
		}
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
