package back;

import java.io.*;

public class Splitter {
	private String pathIn;
	private String pathOut;
	private File f;
	private FileInputStream fin;
	private FileOutputStream fout;
	private BufferedInputStream in;
	private BufferedOutputStream out;
	private byte[] b = new byte[1];
	private int parti = 3;
	private long resto = 0;
	private long grandezza = 0;

	public Splitter() {
		this.pathIn = "";
		this.f = new File(pathIn);
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
	public Splitter(String path, boolean mode, int p) {

		this.pathIn = path;
		this.f = new File(pathIn);
		this.pathOut = f.getParent();

		System.out.println("f" + f.getAbsolutePath());
		System.out.println("path out" + pathOut);

		this.parti = p;
		this.resto = f.length()%parti;

		System.out.println(f.length() + "/" + parti);
		System.out.println("Resto " + resto);

		if(mode) { split(); }
		else { unsplit(); }
	}

	/**
	 * @param path il path del file su cui lavorare
	 * @param mode se true splitta se è false unsplitta
	 */
	public Splitter(String path, boolean mode) {

		this.pathIn = path;
		this.f = new File(pathIn);
		this.pathOut = f.getParent();

		System.out.println("f" + f.getAbsolutePath());
		System.out.println("path out" + pathOut);

		this.parti = 3;
		this.resto = f.length()%parti;

		System.out.println(f.length() + "/" + parti);
		System.out.println("Resto " + resto);

		if(mode) { split(); }
		else { unsplit(); }
	}

	public void split() {

		this.grandezza = (f.length()-resto)/parti;

		try{
			fin = new FileInputStream(pathIn);
			in = new BufferedInputStream(fin);

			System.out.println("Inizio Split");

			for(int i = 1; i <= parti; i++) {

				pathOut = f.getAbsolutePath() + ".par" + i;
				System.out.println("Splitto la parte n: " + i + " del file " + f.getName());
				fout = new FileOutputStream(pathOut);
				out = new BufferedOutputStream(fout);

				if(i == parti) { grandezza = grandezza + resto; }

				for(int j = 0; j < grandezza; j++) {
					in.read(b);
					out.write(b);
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
			System.out.println("Qualcosa è andato storto " + e);
		}

		System.out.println("Fine Split");
	}

	public void unsplit() {

		this.grandezza = f.length();

		try {

			System.out.println("Inizio unsplit");

			fout = new FileOutputStream(f.getParent() + "//unsplit.txt");
			out = new BufferedOutputStream(fout);

			System.out.println("File out " + f.getParent() + "//unsplit.txt");

			String pathTmp = pathIn.toString();
			int numeroFile = 0;
			long lunghezza = 0;

			for(int i = 1; i < i+1; i++) {
				f = new File(pathTmp);
				if(f.exists()) {
					System.out.println("pathTmp: " + 	pathTmp.toString());
					pathTmp = pathTmp.replace(".par" + i, ".par" + (i+1));
					numeroFile++;
					lunghezza += f.length();
				}

				else {	break; }
			}
			
			resto = lunghezza%numeroFile;
			
			for(int i = 1; i <= numeroFile; i++) {
				System.out.println("pathIn " + pathIn);
				fin = new FileInputStream(pathIn);
				in = new BufferedInputStream(fin);

				if(i == numeroFile) { grandezza = grandezza + resto; }

				for(int j = 0; j < grandezza; j++) {
					in.read(b);
					out.write(b); 
				}

				pathIn = pathIn.replace(".par" + i, ".par" + (i+1));

				in.close();
				fin.close();
			}

			in.close();
			out.close();
			fin.close();
			fout.close();

			System.out.println("Fine unsplit");
		}

		catch(Exception e) {
			System.out.println("Qualcosa è andato storto " + e);
		}
	}
}
