package back;

import java.io.*;

public class test {
	private String pathIn = "//home//matteo//test//bhh.mp4";
	private String path = "//home//matteo//test//";
	private String pathOut;
	private File f = new File("//home//matteo//test//bhh.mp4");
	private FileInputStream fin;
	private FileOutputStream fout;
	private BufferedInputStream in;
	private BufferedOutputStream out;
	private byte[] b = new byte[1024];
	private int parti = 3;
	private long resto = 0;
	private long grandezza = 0;

	public void z() {

		try{
			fin = new FileInputStream(pathIn);
			in = new BufferedInputStream(fin);

			System.out.println("Inizio zip");

			grandezza = f.length()/parti;
			resto = f.length()%parti;

			System.out.println("Grandezza " + grandezza);
			System.out.println("Resto " + resto);

			for(int i = 1; i <= parti; i++) {

				pathOut = path + f.getName() + "." + i + ".par";
				fout = new FileOutputStream(pathOut);
				out = new BufferedOutputStream(fout);
				
				if(i == parti) {
					long temp = 1024 + resto;
					b = new byte[(int) temp];
				}

				for(int j = 0; j < grandezza;) {
					in.read(b);
					out.write(b);
					j += 1024;
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

		System.out.println("Fine zip");
	}

	public void uz() {

		try {

			System.out.println("Inizio unzip");

			grandezza = f.length()/parti;
			resto = f.length()%parti;
			

			fout = new FileOutputStream(path + "bhhu.mp4");
			out = new BufferedOutputStream(fout);

			for(int i = 1; i <= parti; i++) {

				pathIn = path + f.getName() + "." + i + ".par";
				fin = new FileInputStream(pathIn);
				in = new BufferedInputStream(fin);
				
				if(i == parti) {
					long temp = 1024 + resto;
					b = new byte[(int) temp];
				}

				for(int j = 0; j < grandezza;) {
					in.read(b);
					out.write(b);
					j += 1024; 
				}

				in.close();
				fin.close();
			}

			in.close();
			out.close();
			fin.close();
			fout.close();

			System.out.println("Fine unzip");
		}

		catch(Exception e) {
			System.out.println("Qualcosa è andato storto " + e);
		}
	}
	
	public void organize(String pathInput) {
		
	}
}
