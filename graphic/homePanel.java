package graphic;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableModel;
import back.myR;
import back.Queue;
import back.Element;

public class homePanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	protected static JTable jt;
	private TableModel tm;
	private JScrollPane spt,spl;
	private JPanel bp;
	private JButton addB, removeB, startB;
	private Queue q = new Queue();
	private static JTextArea log;
	private final static String nl = "\n";
	private setupFrame suf;
	private Thread t;

	public homePanel() {
		super(new BorderLayout());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addB) {
			print("Clicked add");

			suf = new setupFrame();
			suf.showGUI();
		}

		else if (e.getSource() == removeB) {
			int r = jt.getSelectedRow();
			print("Clicked remove");

			if(r !=- 1) {
				MyTableModel model = (MyTableModel)jt.getModel();
				q.Dequeue(r);
				model.fireTableDataChanged();
			}

			else {
				print("Nessun file selezionato");
			}
		}

		else if(e.getSource() == startB){
			Element f = null;

			for(int z = 0; z < q.getElements().size(); z++) {
				System.out.println("z è: " + z);
				f = q.getElements().get(z);
				System.out.println("f.getPath: " + f.getPath());
				System.out.println("f.getNameFile: " + f.getNameFile());
				System.out.println("f.getMode: " + f.getMode());
				t = new Thread(new myR(z, f));
				t.start();
			}
		}
	}

	public static void print(String str) {
		log.append(str + nl);
		log.setCaretPosition(log.getDocument().getLength());
	}

	public void showPanel() {
		tm = new MyTableModel(q);
		jt = new JTable(tm);
		bp = new JPanel();
		log = new JTextArea(10, 10);
		log.setEditable(false);
		print("log");

		spt = new JScrollPane(jt);
		spl = new JScrollPane(log);

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
}
