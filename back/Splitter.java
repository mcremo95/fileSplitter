package back;

import java.awt.BorderLayout;
import java.io.*;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Splitter {
	private Element e;
	private String pathOut;
	private File file;
	private FileInputStream fileIn;
	private FileOutputStream fileOut;
	private BufferedInputStream buffIn;
	private BufferedOutputStream buffOut;
	private JFrame FileFrame;
	private JLabel splitLabel;
	private JPanel FilePanel, BarPanel, Contenitore;
	private JProgressBar progressBar;
	private JTextArea log;
	private JScrollPane scrollPaneLog;
	private final static String newLine = "\n";
	private byte[] b = new byte[1];
	private long parti = 3;
	private long resto = 0;
	private long grandezza = 0;
	private long lunghezzaTmp;
	private long percentuale;

	public Splitter() {
		this.pathOut = file.getParent();

		System.out.println("f" + file.getAbsolutePath());
		System.out.println("path out " + pathOut);

		this.parti = 3;
		this.grandezza = file.length()/parti;
		this.resto = file.length()%parti;
	}

	public Splitter(Element element, int p) {

		this.e = element;
		this.file = new File(e.getPath());
		this.pathOut = file.getParent();

		this.parti = p;
		this.resto = file.length()%parti;

		this.FileFrame = new JFrame(e.getNameFile());
		this.BarPanel = new JPanel();
		this.Contenitore = new JPanel(new BorderLayout());
		this.BarPanel.setLayout(new BoxLayout(BarPanel, BoxLayout.Y_AXIS));
		this.FilePanel = new JPanel();
		this.progressBar = new JProgressBar();
		this.progressBar.setValue(0);
		this.progressBar.setStringPainted(true);
		this.log = new JTextArea(15,35);
		this.log.setEditable(false);
		this.scrollPaneLog = new JScrollPane(log);
		this.splitLabel = new JLabel("Split");
		this.BarPanel.add(splitLabel);
		this.BarPanel.add(progressBar);
		this.FilePanel.add(scrollPaneLog);
		this.Contenitore.add(BarPanel, BorderLayout.CENTER);
		this.Contenitore.add(FilePanel, BorderLayout.PAGE_END);
		this.FileFrame.add(Contenitore);
		this.FileFrame.setSize(450,450);
		this.FileFrame.setVisible(true);

		if(e.getMode().contentEquals("Split(Default)")
				|| e.getMode().contentEquals("Zip")) {
			this.parti = file.length()/(p*1024);
			this.resto = file.length()%parti;
			this.grandezza = (file.length()-resto)/parti;
			this.lunghezzaTmp = 0;
			this.percentuale = file.length()/100;
		}
		
		else if(e.getMode().contentEquals("Split(Parti)")) {
			this.grandezza = (file.length()-resto)/parti;
			this.lunghezzaTmp = 0;
			this.percentuale = file.length()/100;
		}
	}

	public void split() {

		try{
			fileIn = new FileInputStream(e.getPath());
			buffIn = new BufferedInputStream(fileIn);

			print("Inizio Split");

			for(int i = 1; i <= parti; i++) {

				pathOut = file.getAbsolutePath() + ".par" + i;
				print("Splitto la parte n: " + i + " del file " + file.getName());
				fileOut = new FileOutputStream(pathOut);
				buffOut = new BufferedOutputStream(fileOut);

				if(i == parti) { grandezza = grandezza + resto; }
				
				if(grandezza > 512) {	b = new byte[512];	}
				else {	b = new byte[(int) grandezza]; }
				
				for(int j = 0; j < grandezza; ) {
					if((grandezza-j) < 512) {	b = new byte[(int) (grandezza-j)];	}
					buffIn.read(b);
					buffOut.write(b);
					lunghezzaTmp += b.length;
					progressBar.setValue((int) lunghezzaTmp/(int) percentuale);
					j += b.length;
				}

				buffOut.close();
				fileOut.close();
			}

			buffIn.close();
			buffOut.close();
			fileIn.close();
			fileOut.close();
		}

		catch(Exception e) {
			print("Qualcosa è andato storto " + e);
		}

		print("Fine Split");
	}

	public void unsplit() {
		print(e.getPath());
		this.file = new File(e.getPath());
		this.grandezza = file.length();

		try {

			print("Inizio unsplit");

			fileOut = new FileOutputStream(e.getPath().replace(".mp4.par1", "u.mp4"));
			buffOut = new BufferedOutputStream(fileOut);

			String pathTmp = e.getPath().toString();
			int numeroFile = 0;
			long lunghezza = 0;

			print("Controllo il numero di file da riattaccare" + newLine);

			print("Grandezza :" + grandezza);
			for(int i = 1; i < i+1; i++) {
				file = new File(pathTmp);
				if(file.exists()) {
					pathTmp = pathTmp.replace(".par" + i, ".par" + (i+1));
					numeroFile++;
					lunghezza += file.length();
				}

				else {	break; }
			}
			
			resto = lunghezza%numeroFile;
			lunghezzaTmp = 0;
			percentuale = lunghezza/100;
			
			print("numeroFile: " + numeroFile);

			for(int i = 1; i <= numeroFile; i++) {
				fileIn = new FileInputStream(e.getPath());
				buffIn = new BufferedInputStream(fileIn);

				if(i == numeroFile) { grandezza = grandezza + resto; }
				
				if(grandezza > 512) {	b = new byte[512];	}
				else {	b = new byte[(int) grandezza]; }
				
				print("Unsplitto la parte n: " + i + " del file " + 
						file.getName().replace(".mp4.par" + (numeroFile + 1), "u.mp4"));

				for(int j = 0; j < grandezza; ) {
					if((grandezza-j) < 512) {	b = new byte[(int) (grandezza-j)];	}
					buffIn.read(b);
					buffOut.write(b);
					lunghezzaTmp += b.length;
					progressBar.setValue((int) lunghezzaTmp/(int) percentuale);
					j += b.length;
				}

				e.setPath(e.getPath().replace(".par" + i, ".par" + (i+1)));

				buffIn.close();
				fileIn.close();
			}

			buffIn.close();
			buffOut.close();
			fileIn.close();
			fileOut.close();

			print("Fine unsplit");
		}

		catch(Exception e) {
			print("Qualcosa è andato storto " + e);
		}
	}

	protected void print(String str) {
		log.append(str + newLine);
		log.setCaretPosition(log.getDocument().getLength());
	}

	public Element getE() {
		return e;
	}

	public void setE(Element e) {
		this.e = e;
	}

	public String getPathOut() {
		return pathOut;
	}

	public void setPathOut(String pathOut) {
		this.pathOut = pathOut;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public FileInputStream getFileIn() {
		return fileIn;
	}

	public void setFileIn(FileInputStream fileIn) {
		this.fileIn = fileIn;
	}

	public FileOutputStream getFileOut() {
		return fileOut;
	}

	public void setFileOut(FileOutputStream fileOut) {
		this.fileOut = fileOut;
	}

	public BufferedInputStream getBuffIn() {
		return buffIn;
	}

	public void setBuffIn(BufferedInputStream buffIn) {
		this.buffIn = buffIn;
	}

	public BufferedOutputStream getBuffOut() {
		return buffOut;
	}

	public void setBuffOut(BufferedOutputStream buffOut) {
		this.buffOut = buffOut;
	}

	public JFrame getFileFrame() {
		return FileFrame;
	}

	public void setFileFrame(JFrame fileFrame) {
		FileFrame = fileFrame;
	}

	public JPanel getFilePanel() {
		return FilePanel;
	}

	public void setFilePanel(JPanel filePanel) {
		FilePanel = filePanel;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public JTextArea getLog() {
		return log;
	}

	public void setLog(JTextArea log) {
		this.log = log;
	}

	public JScrollPane getScrollPaneLog() {
		return scrollPaneLog;
	}

	public void setScrollPaneLog(JScrollPane scrollPaneLog) {
		this.scrollPaneLog = scrollPaneLog;
	}

	public byte[] getB() {
		return b;
	}

	public void setB(byte[] b) {
		this.b = b;
	}

	public long getParti() {
		return parti;
	}

	public void setParti(long parti) {
		this.parti = parti;
	}

	public long getResto() {
		return resto;
	}

	public void setResto(long resto) {
		this.resto = resto;
	}

	public long getGrandezza() {
		return grandezza;
	}

	public void setGrandezza(long grandezza) {
		this.grandezza = grandezza;
	}

	public long getLunghezzaTmp() {
		return lunghezzaTmp;
	}

	public void setLunghezzaTmp(long lunghezzaTmp) {
		this.lunghezzaTmp = lunghezzaTmp;
	}

	public long getPercentuale() {
		return percentuale;
	}

	public void setPercentuale(long percentuale) {
		this.percentuale = percentuale;
	}

	public JLabel getSplitLabel() {
		return splitLabel;
	}

	public void setSplitLabel(JLabel splitLabel) {
		this.splitLabel = splitLabel;
	}

	public JPanel getBarPanel() {
		return BarPanel;
	}

	public void setBarPanel(JPanel barPanel) {
		BarPanel = barPanel;
	}

	public JPanel getContenitore() {
		return Contenitore;
	}

	public void setContenitore(JPanel contenitore) {
		Contenitore = contenitore;
	}

}
