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

	public void split(){
		int i = 0;
		int j = 1;
		setFile(new File(getE().getPath()));

		this.zipLabel = new JLabel("Zip");
		getBarPanel().add(zipLabel);
		getBarPanel().add(progressBarZip);
		getContenitore().add(getFilePanel(), BorderLayout.PAGE_END);

		super.split();

		print("controllo i file");
		try {
			Thread.sleep(2000);
		}
		
		catch(Exception e) {
			print("qualcosa è andato storto " + e);
		}
		
		getE().setPath(getE().getPath() + ".par1");
		
		while(getFile().exists()) {
			i++;
			j++;
			print("Il valore di i = " + i + " mentre j = " + j);
			print("prima di cambiare ");
			print("name è: " + getE().getPath());
			getE().setPath(getE().getPath().replace(".par" + i, ".par" + j));
			print("dopo il cambiamento ");
			print("name è: " + getE().getPath());
			setFile(new File(getE().getPath()));
		}

		print("in totale ci sono " + i + " file par");
		getE().setPath(getE().getPath().replace(".par" + j, ""));
		print("name è " + getE().getPath() + " j = " + j + " i = " + i);

		for(j = 1; j <= i; j++) {
			print(" j = " + j + " i = " + i);
			Zip(getE().getPath() + ".par" + j);
		}
	}

	public void unsplit() {
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
			print("Il valore di i è: " + i + " mentre j è " + j);
			print("name è: " + getE().getPath());
			getE().setPath(getE().getPath().replace(".par" + i, ".par" + j));
			f = new File(getE().getPath());
		}

		print("in totale ci sono " + i + " file par");
		getE().setPath(getE().getPath().replace(".par" + j + ".zip", ""));
		print("name è " + getE().getPath() + " j = " + j + " i = " + i);

		for(j = 1; j <= i; j++) {
			print(" j = " + j + " i = " + i);
			Unzip(getE().getPath() + ".par" + j + ".zip");
			getE().setPath(getE().getPath().replace(".par" + j + ".zip", ".par" + i + ".zip"));
		}
		getE().setPath(getE().getPath() + ".par1");
		super.unsplit();
	}
}
