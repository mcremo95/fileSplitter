package back;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class Zipper extends Splitter{
	private ZipEntry entry;
	private ZipOutputStream zipOut;
	private ZipInputStream zipIn;
	private JLabel zipLabel;
	private JProgressBar progressBarZip;

	/**
	 * 
	 * @param element l'elemento di file che si vuole splittare o unplittare
	 * @param p il numero di parti o la grandezza delle parti che si vogliono splittare
	 */
	public Zipper(Element element, int p) {
		super(element, p);
		this.progressBarZip = new JProgressBar();
		this.progressBarZip.setValue(0);
		this.progressBarZip.setStringPainted(true);
		getContenitore().remove(getFilePanel());

	}

	/**
	 * Scomprime un file
	 * @param name il nome del file sul quale si vuole lavorare
	 */
	public void Unzip(String name) {

		print("Inizio a scomprimere il file " + name);

		try {
			zipIn = new ZipInputStream(new FileInputStream(name));
			entry = zipIn.getNextEntry();
			while (entry != null) {
				setFile(new File(name.replace(".zip", "")));
				setFileOut(new FileOutputStream(name.replace(".zip", "")));
				int lunghezza = 0;

				while ((lunghezza = zipIn.read(getB())) > 0) {
					getFileOut().write(getB(), 0, lunghezza);
					setLunghezzaTmp(lunghezza + getLunghezzaTmp());
					progressBarZip.setValue((int) getLunghezzaTmp()/(int) getPercentuale());
				}
				getFileOut().close();
				entry = zipIn.getNextEntry();
			}
			zipIn.closeEntry();
			zipIn.close();

			print("Ho finito di scomprimere il file " + name);
		}

		catch(Exception e) {
			print("qualcosa è andato storto " + e);
		}

		print("Ho finito di comprimere il file " + name);
	}

	/**
	 * Comprime un file
	 * @param name il nome del file sul quale si vuole lavorare
	 */
	public void Zip(String name){

		print("Inizio a comprimere il file " + name);

		try{
			setFileOut(new FileOutputStream(name + ".zip"));
			zipOut = new ZipOutputStream(getFileOut());
			File fileToZip = new File(name);
			setFileIn(new FileInputStream(fileToZip));
			entry = new ZipEntry(fileToZip.getName());
			zipOut.putNextEntry(entry);

			int lunghezza = 0;

			while((lunghezza = getFileIn().read(getB())) >= 0) {
				zipOut.write(getB(), 0, lunghezza);
				setLunghezzaTmp(lunghezza + getLunghezzaTmp());
				progressBarZip.setValue((int) getLunghezzaTmp()/(int) getPercentuale());
			}

			zipOut.close();
			getFileIn().close();
			getFileOut().close();

		}
		catch(Exception e) {
			print("qualcosa è andato storto " + e);
		}

		print("Ho finito di comprimere il file " + name);
	}

	/**
	 * Aggiunge elementi all'interfaccia grafica,
	 * chiama la funzione Split() della classe padre e poi
	 * controlla tutti i file .par da comprimere e poi chiama la funzione zip
	 * per ognuno di questi file infine elimina i file .par di scarto 
	 */
	public synchronized void Split(){
		int i = 0;
		int j = 1;
		setFile(new File(getE().getPath()));

		this.zipLabel = new JLabel("Zip");
		getBarPanel().add(zipLabel);
		getBarPanel().add(progressBarZip);
		getContenitore().add(getFilePanel(), BorderLayout.PAGE_END);

		super.Split();

		print("controllo i file");
		
		getE().setPath(getE().getPath() + ".par1");
		
		while(getFile().exists()) {
			i++;
			j++;
			getE().setPath(getE().getPath().replace(".par" + i, ".par" + j));
			setGrandezza(getFile().length() + getGrandezza());
			setFile(new File(getE().getPath()));
		}
		
		setPercentuale(getGrandezza()/100);

		print("in totale ci sono " + i + " file par");
		getE().setPath(getE().getPath().replace(".par" + j, ""));

		for(j = 1; j <= i; j++) {
			Zip(getE().getPath() + ".par" + j);
		}

		getE().setPath(getE().getPath() + ".par1");
		setFile(new File(getE().getPath()));
		j = 1;
		
		while(getFile().exists()) {
			getFile().delete();
			getE().setPath(getE().getPath().replace(".par" + j, ".par" + ++j));
			setFile(new File(getE().getPath()));
		}
		
		getE().setPath(getE().getPath().replace(".par" + j, ""));
	}

	/**
	 * Aggiunge elementi all'interfaccia grafica
	 * controlla tutti i file .zip da scomprimere e poi chiama la funzione unzip
	 * per ognuno di questi file, poi elimina i file .par di scarto 
	 */
	public synchronized void Unsplit() {
		getBarPanel().remove(getSplitLabel());
		getBarPanel().remove(getProgressBar());
		setSplitLabel(new JLabel("Unsplit"));
		getBarPanel().add(getSplitLabel());
		getBarPanel().add(getProgressBar());
		
		this.zipLabel = new JLabel("Unzip");
		getBarPanel().add(zipLabel);
		getBarPanel().add(progressBarZip);
		getContenitore().add(getFilePanel(), BorderLayout.PAGE_END);

		File f = new File(getE().getPath());
		int i = 0;
		int j = i + 1;
		
		while(f.exists()) {
			i++;
			j++;
			getE().setPath(getE().getPath().replace(".par" + i, ".par" + j));
			setGrandezza(getFile().length() + getGrandezza());
			f = new File(getE().getPath());
		}

		setPercentuale(getGrandezza()/100);
		
		print("in totale ci sono " + i + " file par");
		getE().setPath(getE().getPath().replace(".par" + j + ".zip", ""));
		
		for(j = 1; j <= i; j++) {
			Unzip(getE().getPath() + ".par" + j + ".zip");
			getE().setPath(getE().getPath().replace(".par" + j + ".zip", ".par" + i + ".zip"));
		}
		getE().setPath(getE().getPath() + ".par1");
		super.Unsplit();
		
		getE().setPath(getE().getPath().replace(".par" + ++i, ".par1"));
		print(getE().getPath());
		
		f = new File(getE().getPath());
		j = 1;
		
		while(f.exists()) {
			f.delete();
			getE().setPath(getE().getPath().replace(".par" + j, ".par" + ++j));
			f = new File(getE().getPath());
		}
		
		getE().setPath(getE().getPath().replace(".par" + j, ".par1"));
		getE().setPath(getE().getPath() + ".zip");
		
	}
}
