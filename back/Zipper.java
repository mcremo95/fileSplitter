package back;

import java.awt.BorderLayout;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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

	public Zipper(Element element, int p) {
		super(element, p);
		this.progressBarZip = new JProgressBar();
		this.progressBarZip.setValue(0);
		this.progressBarZip.setStringPainted(true);
		getContenitore().remove(getFilePanel());
		
	}

	public void split() {
		this.zipLabel = new JLabel("Zip");
		getBarPanel().add(zipLabel);
		getBarPanel().add(progressBarZip);
		getContenitore().add(getFilePanel(), BorderLayout.PAGE_END);
		
		try {
			super.split();
			print("Inizio zip");
			Thread.sleep(4000);
			progressBarZip.setValue(100);
			print("FIne zip");
		}

		catch(Exception e) {
			print("Qualcosa è andato storto " + e);
		}
	}

	public void unsplit() {

		this.zipLabel = new JLabel("Unzip");
		getBarPanel().add(zipLabel);
		getBarPanel().add(progressBarZip);
		setGrandezza(getFile().length());

		try {
			setFileOut(new FileOutputStream(getE().getPath().
					replace(".mp4.zip1", "u.mp4")));
			setBuffOut(new BufferedOutputStream(getFileOut()));

			String pathTmp = getE().getPath().toString();
			int numeroFile = 0;
			long lunghezza = 0;

			print("Controllo il numero di file da riattaccare");

			print("Grandezza: " + getGrandezza());

			for(int i = 1; i < i+1; i++) {
				print("" + i);
				setFile(new File(pathTmp));
				if(getFile().exists()) {
					pathTmp = pathTmp.replace(".zip" + i, ".zip" + (i+1));
					numeroFile++;
					lunghezza += getFile().length();
				}

				else {	break; }
			}

			setResto(lunghezza%numeroFile);
			setLunghezzaTmp(0);
			setPercentuale(lunghezza/100);

			print("numeroFile: " + numeroFile);

			for(int i = 1; i <= numeroFile; i++) {
				print(getE().getPath());
				setFileIn(new FileInputStream(getE().getPath()));
				zipIn = new ZipInputStream(getFileIn());

				if(i == numeroFile) { setGrandezza(getGrandezza() + getResto()); }

				if(getGrandezza() > 512) {	setB(new byte[512]);	}
				else {	setB(new byte[(int) getGrandezza()]); }

				print("Scomprimo la parte n: " + i + " del file " + 
						getFile().getName().
						replace(".mp4.zip" + (numeroFile + 1), "u.mp4"));

				print("Inizio for di lettura e scrittura");
				print("i:  " + i);
				for(int j = 0; j < getGrandezza(); ) {
					if((getGrandezza()-j) < 512) {	
						setB(new byte[(int) (getGrandezza()-j)]);
					}

					zipIn.read(getB());
					getBuffOut().write(getB());
					setLunghezzaTmp(getB().length + getLunghezzaTmp());
					getProgressBar().setValue(
							(int) getLunghezzaTmp()/(int) getPercentuale());
					j += getB().length;
				}

				getE().setPath(getE().getPath().replace(".zip" + i, ".zip" + (i+1)));
				print(getE().getPath());
				
				print("prima della chiusura dei file");
				zipIn.close();
				getFileIn().close();
				print("dopo chiusura dei file");
			}

			zipIn.close();
			getBuffOut().close();
			getFileIn().close();
			getFileOut().close();

			print("Fine unsplit");
		}

		catch(Exception e) {
			print("Qualcosa è andato storto " + e);
		}
	}
}
