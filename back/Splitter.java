package back;

import java.io.*;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Splitter {
	private Element e;
	private String pathOut;
	private File f;
	private FileInputStream fin;
	private FileOutputStream fout;
	private BufferedInputStream in;
	private BufferedOutputStream out;
	private JFrame FileFrame;
	private JPanel FilePanel;
	private JProgressBar bar;
	private JTextArea log;
	private JScrollPane spl;
	private final static String nl = "\n";
	private byte[] b = new byte[1];
	private long parti = 3;
	private long resto = 0;
	private long grandezza = 0;
	private long lunghezzaTmp;
	private long percentuale;

	public Splitter() {
		this.pathOut = f.getParent();

		System.out.println("f" + f.getAbsolutePath());
		System.out.println("path out " + pathOut);

		this.parti = 3;
		this.grandezza = f.length()/parti;
		this.resto = f.length()%parti;
	}

	/**
	 * 
	 * @param path il path del file su cui lavorare
	 * @param mode se true splitta se è false unsplitta
	 * @param p le parti in cui dividere
	 */
	public Splitter(Element element, int p) {

		this.e = element;
		this.f = new File(e.getPath());
		this.pathOut = f.getParent();

		this.parti = p;
		this.resto = f.length()%parti;

		this.FileFrame = new JFrame(e.getNameFile());
		this.FilePanel = new JPanel();
		this.FilePanel.setLayout(new BoxLayout(FilePanel, BoxLayout.Y_AXIS));
		this.bar = new JProgressBar();
		this.bar.setValue(0);
		this.bar.setStringPainted(true);
		this.log = new JTextArea(10,10);
		this.log.setEditable(false);
		this.spl = new JScrollPane(log);
		this.FilePanel.add(bar);
		this.FilePanel.add(spl);
		this.FileFrame.add(FilePanel);
		this.FileFrame.setSize(300,300);
		this.FileFrame.setVisible(true);

		if(e.getMode().contentEquals("Split(Default)")) {
			parti = f.length()/(p*1024);
			resto = f.length()%parti;
			grandezza = (f.length()-resto)/parti;
			lunghezzaTmp = 0;
			percentuale = f.length()/100;
			split();
		}
		
		else if(e.getMode().contentEquals("Split(Parti)")) {
			grandezza = (f.length()-resto)/parti;
			lunghezzaTmp = 0;
			percentuale = f.length()/100;
			split();
		}
		
		else { unsplit(); }
	}

	/**
	 * @param path il path del file su cui lavorare
	 * @param mode se true splitta se è false unsplitta
	 */

	public void split() {

		try{
			fin = new FileInputStream(e.getPath());
			in = new BufferedInputStream(fin);

			print("Inizio Split" + nl);

			for(int i = 1; i <= parti; i++) {

				pathOut = f.getAbsolutePath() + ".par" + i;
				print("Splitto la parte n: " + i + " del file " + f.getName() + nl);
				fout = new FileOutputStream(pathOut);
				out = new BufferedOutputStream(fout);

				if(i == parti) { grandezza = grandezza + resto; }

				for(int j = 0; j < grandezza; j++) {
					in.read(b);
					out.write(b);
					lunghezzaTmp += 1;
					if((lunghezzaTmp%percentuale) == 0) {	bar.setValue(bar.getValue() + 1);	}
				}

				out.close();
				fout.close();
			}

			in.close();
			out.close();
			fin.close();
			fout.close();
		}

		catch(Exception e) {
			print("Qualcosa è andato storto " + e + nl);
		}

		print("Fine Split" + nl);
	}

	public void unsplit() {

		this.grandezza = f.length();

		try {

			print("Inizio unsplit" + nl);

			fout = new FileOutputStream(e.getPath().replace(".mp4.par1", "u.mp4"));
			out = new BufferedOutputStream(fout);

			String pathTmp = e.getPath().toString();
			int numeroFile = 0;
			long lunghezza = 0;

			print("Controllo il numero di file da riattaccare" + nl);

			for(int i = 1; i < i+1; i++) {
				f = new File(pathTmp);
				if(f.exists()) {
					pathTmp = pathTmp.replace(".par" + i, ".par" + (i+1));
					numeroFile++;
					lunghezza += f.length();
				}

				else {	break; }
			}

			print("Numero di f");

			resto = lunghezza%numeroFile;
			lunghezzaTmp = 0;
			percentuale = lunghezza/100;

			for(int i = 1; i <= numeroFile; i++) {
				fin = new FileInputStream(e.getPath());
				in = new BufferedInputStream(fin);

				if(i == numeroFile) { grandezza = grandezza + resto; }

				for(int j = 0; j < grandezza; j++) {
					in.read(b);
					out.write(b);
					lunghezzaTmp += 1;
					if((lunghezzaTmp%percentuale) == 0) {	bar.setValue(bar.getValue() + 1);	}
				}

				e.setPath(e.getPath().replace(".par" + i, ".par" + (i+1)));

				in.close();
				fin.close();
			}

			in.close();
			out.close();
			fin.close();
			fout.close();

			print("Fine unsplit" + nl);
		}

		catch(Exception e) {
			print("Qualcosa è andato storto " + e + nl);
		}
	}

	public void print(String str) {
		log.append(str + nl);
		log.setCaretPosition(log.getDocument().getLength());
	}
}
