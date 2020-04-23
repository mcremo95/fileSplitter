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

import back.Queue;
import back.Element;

public class homePanel extends JPanel implements ActionListener{
	private JTable jt;
	private TableModel tm;
	private JScrollPane spt,spl;
	private JPanel bp;
	private JButton addB, removeB;
	private JFileChooser fc;
	private Queue q;
	private JTextArea log;
	private final static String nl = "\n";

	public homePanel() {

		super(new BorderLayout());

		q = new Queue();
		tm = new MyTableModel(q);
		jt = new JTable(tm);
		bp = new JPanel();
		log = new JTextArea("log", 20, 20);
		log.setEditable(false);
		spt = new JScrollPane(jt);
		spl = new JScrollPane(log);
		
		fc = new JFileChooser();
		
		jt.setRowSelectionAllowed(true);
		jt.setColumnSelectionAllowed(false);

		jt.setRowSelectionAllowed(true);
		jt.setColumnSelectionAllowed(false);

		addB = new JButton("Aggiungi");
		addB.addActionListener(this);
		removeB = new JButton("Rimuovi");
		removeB.addActionListener(this);

		bp.add(addB);
		bp.add(removeB);

		this.add(spt, BorderLayout.PAGE_START);
		this.add(spl, BorderLayout.PAGE_END);
		this.add(bp, BorderLayout.PAGE_END);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addB) {
			int returnVal = fc.showDialog(homePanel.this, "Add");
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				MyTableModel model = (MyTableModel)jt.getModel();
				model.add(new Element(file.getAbsolutePath(), file.getName(), "Default", 100));
			}
		}

		else if (e.getSource() == removeB) {
			int r = jt.getSelectedRow();
			if(r !=- 1) {
				MyTableModel model = (MyTableModel)jt.getModel();
				q.Dequeue(r);
				model.fireTableDataChanged();
			}
			else {
				log.append("Nessun file selezionato" + nl);
			}
		}
	}
}
