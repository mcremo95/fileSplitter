package graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class setupFrame extends JFrame implements ActionListener{
	
	private JLabel jl;
	private JFileChooser fc;
	private JButton okButton, refuseButton, addButton;
	private JComboBox<String> box;
	private JPanel jp;
	
	public setupFrame() {
		this.setSize(300,300);
		
		jp = new JPanel();
		this.add(jp);
		
		box = new JComboBox<String>();
		box.addItem("Default");
		box.addItem("Parti");
		box.addItem("Zip");
		box.addItem("Cripto");
		jp.add(box);
		
		addButton = new JButton("add");
		addButton.addActionListener(this);
		jp.add(addButton);
		
		okButton = new JButton("ok");
		okButton.addActionListener(this);
		jp.add(okButton);
		refuseButton = new JButton("refuse");
		refuseButton.addActionListener(this);
		jp.add(refuseButton);

		this.pack();
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
}
