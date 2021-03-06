package graphic;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;
import back.Generic;
import back.Queue;
import back.Element;

public class MyTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private String[] colName = {"FileName", "Path", "Mode", "Dimension (KB)"};
	private Vector<Element> v = null;

	/**
	 * Costruttore
	 * @param q una coda Queue che inizializza il vettore, può essere vuota
	 */
	public MyTableModel(Queue q) {
		this.v = q.getElements();
	}

	public int getColumnCount() { return colName.length; }

	public int getRowCount() { 
		if(v == null) return 1;
		else return v.size(); 
	}

	public boolean IsCellEditable(int row, int col) {
		return false;
	}

	public String getColumnName(int col) {
		return colName[col];
	}

	public Class getClassAt(int col) {
		return getValueAt(0, col).getClass();
	}

	public void setValueAt(Vector<Generic<String, Integer>> v, String mode, int dim, int row) {
		Generic<String, Integer> e = v.elementAt(row);
		e.setMode(mode);
		e.setGrandezza(dim);
		fireTableDataChanged();
	}
	/**
	 * Chiama add del Vector V e aggiunge l'elemento e in coda
	 * @param e
	 */
	public void add(Element e) {
		this.v.add(e);
		fireTableDataChanged();
	}

	public Object getValueAt(int row, int col) {

		Element e = (Element)v.elementAt(row);

		switch(col) {
		case 0:
			return e.getNameFile();
		case 1:
			return e.getPath();
		case 2:
			return e.getMode();
		case 3:
			return e.getGrandezza();
		default:
			return "";
		}
	}
}
