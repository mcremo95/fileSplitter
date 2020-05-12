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
		this.pathIn = "//home//matteo//test//bhh.mp4";
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
				System.out.println("splitto la parte n:" + 1 + "del file" + f.getName());
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
			
			fout = new FileOutputStream(f.getParent() + "//bhhu.mp4");
			out = new BufferedOutputStream(fout);
			
			System.out.println("File out " + f.getParent() + "//bhhu.mp4");

			for(int i = 1; i <= parti; i++) {
				System.out.println("pathIn " + pathIn);
				fin = new FileInputStream(pathIn);
				in = new BufferedInputStream(fin);

				if(i == parti) { grandezza = grandezza + resto; }
				
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
	
	public void organize(String pathInput) {
		
	}
}
