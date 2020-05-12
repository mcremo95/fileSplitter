package graphic;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableModel;
import java.io.File;
import back.Splitter;

import back.Queue;
import back.Element;

public class homePanel extends JPanel implements ActionListener{
	
	private JTable jt;
	private TableModel tm;
	private JScrollPane spt,spl;
	private JPanel bp;
	private JButton addB, removeB, startB;
	private JFileChooser fc;
	private Queue q;
	private Splitter split;
	private static JTextArea log;
	private final static String nl = "\n";

	public homePanel() {

		super(new BorderLayout());

		q = new Queue();
		tm = new MyTableModel(q);
		jt = new JTable(tm);
		bp = new JPanel();
		log = new JTextArea("log" + nl, 10, 10);
		log.setEditable(false);
		
		spt = new JScrollPane(jt);
		spl = new JScrollPane(log);
		
		fc = new JFileChooser();
		
		jt.setRowSelectionAllowed(true);
		jt.setColumnSelectionAllowed(false);

		addB = new JButton("Aggiungi");
		addB.addActionListener(this);
		removeB = new JButton("Rimuovi");
		removeB.addActionListener(this);
		startB = new JButton("Start");
		startB.addActionListener(this);

		bp.add(addB);
		bp.add(removeB);
		bp.add(startB);

		this.add(spt, BorderLayout.PAGE_START);
		this.add(spl, BorderLayout.CENTER);
		this.add(bp, BorderLayout.PAGE_END);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addB) {
			int returnVal = fc.showDialog(homePanel.this, "Add");
			print("Clicked add" + nl);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				MyTableModel model = (MyTableModel)jt.getModel();
				model.add(new Element(file.getAbsolutePath(), file.getName(), "Default", 100));
			}
		}

		else if (e.getSource() == removeB) {
			int r = jt.getSelectedRow();
			print("Clicked remove" + nl);
			if(r !=- 1) {
				MyTableModel model = (MyTableModel)jt.getModel();
				q.Dequeue(r);
				model.fireTableDataChanged();
			}
			else {
				log.append("Nessun file selezionato" + nl);
			}
		}
		
		else if(e.getSource() == startB){
			Element f = null;
			for(int z = 0; z < q.getElements().size(); z++) {
				f = q.getElements().get(z);
				control(f);
			}
		}
	}
	
	public void print(String str) {
		log.append(str + nl);
		log.setCaretPosition(log.getDocument().getLength());
	}
	//chiamare i Thred qui
	private void control(Element e) {
		
		if(getExtention(e.getNameFile()).equals(".par1")) {
			//split = new Splitter(e.getPath(), false);
			log.append(e.getNameFile() + "è .par" + nl);
		}
		
		else if(getExtention(e.getNameFile()).equals(".zip")) {
			//split = new Splitter();
			log.append(e.getNameFile() + " è un file .zip" + nl);
		}
		
		else if(e.getMode().equals("Parti")) {
			//MyFrame2 f2 = new MyFrame2(f);
			log.append(e.getNameFile() + " è da dividere per parti" + nl);
		}
		
		else {
			//split = new Splitter(e.getPath(), true);
			log.append( e.getNameFile() + " è da splittare" + nl);
		}
	}
	
	private String getExtention(String n) {
		return n.substring(n.lastIndexOf('.'));
	}
}
