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

public class homePanel extends JPanel implements ActionListener, Window{

	private static final long serialVersionUID = 1L;
	protected static JTable myTable;
	private TableModel myTableModel;
	private JScrollPane scrollPaneTable,scrollPaneLog;
	private JPanel buttonPanel;
	private JButton addButton, removeButton, startButton, modifyButton;
	private Queue q = new Queue();
	private JTextArea log;
	private final static String nl = "\n";
	private setupFrame setUpFrame;
	private Thread myThread;

	public homePanel() {
		super(new BorderLayout());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			print("Clicked add");

			setUpFrame = new setupFrame();
			setUpFrame.showGUI();
		}

		else if (e.getSource() == removeButton) {
			int r = myTable.getSelectedRow();
			print("Clicked remove");

			if(r !=- 1) {
				MyTableModel model = (MyTableModel)myTable.getModel();
				q.Dequeue(r);
				model.fireTableDataChanged();
			}

			else {
				print("Nessun file selezionato");
			}
		}

		else if(e.getSource() == startButton){
			Element f = null;

			for(int z = 0; z < q.getElements().size(); z++) {
				f = q.getElements().get(z);
				myThread = new Thread(new myR(f));
				myThread.start();
			}
		}
	}

	public void print(String str) {
		log.append(str + nl);
		log.setCaretPosition(log.getDocument().getLength());
	}

	public void showGUI() {
		myTableModel = new MyTableModel(q);
		myTable = new JTable(myTableModel);
		buttonPanel = new JPanel();
		log = new JTextArea(10, 10);
		log.setEditable(false);
		print("log");

		scrollPaneTable = new JScrollPane(myTable);
		scrollPaneLog = new JScrollPane(log);

		myTable.setRowSelectionAllowed(true);
		myTable.setColumnSelectionAllowed(false);

		addButton = new JButton("Aggiungi");
		addButton.addActionListener(this);
		removeButton = new JButton("Rimuovi");
		removeButton.addActionListener(this);
		startButton = new JButton("Start");
		startButton.addActionListener(this);
		modifyButton = new JButton("Modifica");
		modifyButton.addActionListener(this);

		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
		buttonPanel.add(modifyButton);
		buttonPanel.add(startButton);

		this.add(scrollPaneTable, BorderLayout.PAGE_START);
		this.add(scrollPaneLog, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.PAGE_END);
	}
}
