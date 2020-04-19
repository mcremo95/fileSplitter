package back;

import java.io.*;

public class Splitter {
	private String file, path;
	private int parti, resto;
	private File f;

	public Splitter() {
		this.path = "//home//matteo//test//bhh.mp4";
		test();
	}
	
	public Splitter(String path) {
		this.path = path;
		test();
	}

	public void test() {
		f = new File(path);
		file = f.getName();
		
		System.out.println("il path è: " + path);
		System.out.println("il nome è: " + file);
	}
	
	public void split() {
		
	}
	
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getParti() {
		return parti;
	}

	public void setParti(int parti) {
		this.parti = parti;
	}

	public int getResto() {
		return resto;
	}

	public void setResto(int resto) {
		this.resto = resto;
	}

	public File getF() {
		return f;
	}

	public void setF(File f) {
		this.f = f;
	}
}
